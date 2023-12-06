package vadebike.equipamentos.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.EnviarEmailDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.enums.StatusTranca;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.IBicicletaRepository;
import vadebike.equipamentos.repository.ITrancaRepository;

@Service
public class BicicletaService extends BaseServiceImpl<BicicletaDTO, Bicicleta, IBicicletaRepository>
		implements IBicicletaService {

	@Autowired
	ITrancaRepository trancaRepository;

	private Random random = new Random();

	private final String urlGetFuncionario = "https://microservice-aluguel-hbkxpua2za-uc.a.run.app/funcionario";
	
	private final String urlEnviarEmail = "https://microservice-externo-hbkxpua2za-uc.a.run.app/enviarEmail";

	@Override
	@Transactional
	public BicicletaDTO create(Bicicleta newEntity) {

		Integer numero = random.nextInt();

		while (baseRepository.findByNumero(numero) != null) {
			numero = random.nextInt();
		}

		newEntity.setStatus(StatusBicicleta.NOVA.getStatus());
		newEntity.setNumero(numero);

		Bicicleta updatedEntity = baseRepository.save(newEntity);
		return updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public BicicletaDTO update(Bicicleta updated) {
		Bicicleta originalEntity = baseRepository.findById(updated.getId()).get();

		if (!originalEntity.getStatus().equals(updated.getStatus())
				|| originalEntity.getNumero() != updated.getNumero()) {
			throw new BusinessException("Status e Numero nao podem ser editados.");
		}

		Bicicleta updatedEntity = baseRepository.save(updated);

		return updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<Bicicleta> opEntity = baseRepository.findById(id);
		if (!opEntity.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		} else if (opEntity.get().getTranca() != null
				|| !opEntity.get().getStatus().equals(StatusBicicleta.APOSENTADA.getStatus())) {
			throw new BusinessException("Bicicleta nao aposentado ou em tranca.");
		}
		baseRepository.delete(opEntity.get());
	}

	@Transactional
	public BicicletaDTO updateStatus(Integer id, String acao) {

		Bicicleta bicicleta = baseRepository.findById(id).get();
		bicicleta.setStatus(StatusBicicleta.valueOf(acao.toUpperCase()).getStatus());
		return baseRepository.save(bicicleta).convertToDto();
	}

	@Transactional
	public void integrarNaRede(Integer idBicicleta, Integer idTranca, Integer idFuncionario) {
		Bicicleta bicicleta = baseRepository.findById(idBicicleta).get();
		Tranca tranca = trancaRepository.findById(idTranca).get();
		
		sendEmail(idFuncionario, urlGetFuncionario, urlEnviarEmail, bicicleta, tranca);
		
		if (tranca.getBicicleta() != null) {
			throw new BusinessException("Tranca Ocupada");
		}

		bicicleta.setStatus(StatusBicicleta.DISPONIVEL.getStatus());
		tranca.setBicicleta(bicicleta);
		tranca.setStatus(StatusTranca.OCUPADA.getStatus());
		bicicleta.setTranca(tranca);
		baseRepository.save(bicicleta);
	}

	@Transactional
	public void retirarDaRede(Integer idBicicleta, Integer idTranca, Integer idFuncionario,
			String statusAcaoReparador) {

		Bicicleta bicicleta = baseRepository.findById(idBicicleta).get();
		Tranca tranca = trancaRepository.findById(idTranca).get();
		
		sendEmail(idFuncionario, urlGetFuncionario, urlEnviarEmail, bicicleta, tranca);
		
		if (tranca.getBicicleta() == null || bicicleta.getStatus().equals("DISPONÍVEL")
				|| tranca.getStatus().equals("LIVRE")) {
			throw new BusinessException("Tranca vazia ou bicicleta disponivel");
		}

		bicicleta.setStatus(StatusBicicleta.valueOf(statusAcaoReparador).getStatus());
		bicicleta.setTranca(null);
		tranca.setBicicleta(null);
		tranca.setStatus(StatusTranca.LIVRE.getStatus());
		baseRepository.save(bicicleta);
		trancaRepository.save(tranca);
	}
	
    public void sendEmail(Integer idFuncionario, String urlGetFuncionario, String urlEnviarEmail, Bicicleta bicicleta, Tranca tranca) {
        String corpoEmail = new Date().toString()
                + "\nBicicleta:"
                + bicicleta.getNumero()
                + "\nTranca:"
                + tranca.getNumero();

        String requestUrl = urlGetFuncionario + "/" + idFuncionario.toString();

        HttpResponse<JsonNode> responseFuncionario = Unirest.get(requestUrl).asJson();

        if (responseFuncionario.getStatus() == 200) {
            JsonNode jsonNode = responseFuncionario.getBody();

            String emailValue = jsonNode.getObject().getString("email");

            if (emailValue != null) {
                EnviarEmailDTO emailDTO = new EnviarEmailDTO();
                emailDTO.setDestinatario(emailValue);
                emailDTO.setAssunto("Dados integração");
                emailDTO.setMensagem(corpoEmail);

                HttpResponse<JsonNode> responseEmail = Unirest.post(urlEnviarEmail)
                        .header("Content-Type", "application/json")
                        .body(emailDTO)
                        .asJson();

                if (responseEmail.getStatus() != 200) {
                    throw new BusinessException("Erro ao enviar email");
                }

            } else {
                throw new BusinessException("Email não encontrado");
            }
        } else {
            throw new BusinessException("Erro na camada da API Externa");
        }
    }
}
