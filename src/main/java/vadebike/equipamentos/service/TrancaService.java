package vadebike.equipamentos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.EnviarEmailDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.enums.StatusTranca;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.IBicicletaRepository;
import vadebike.equipamentos.repository.ITotemRepository;
import vadebike.equipamentos.repository.ITrancaRepository;

@Service
public class TrancaService extends BaseServiceImpl<TrancaDTO, Tranca, ITrancaRepository> implements ITrancaService {

	@Autowired
	ITotemRepository totemRepository;
	
	@Autowired
	IBicicletaRepository bicicletaRepository;
	
	private final String urlGetFuncionario = "https://microservice-aluguel-hbkxpua2za-uc.a.run.app/funcionario";
	
	private final String urlEnviarEmail = "https://microservice-externo-hbkxpua2za-uc.a.run.app/enviarEmail";

	public BicicletaDTO findBicicletaByTrancaId(Integer id) {
		return baseRepository.findById(id).get().getBicicleta().convertToDto();
	}

	@Override
	@Transactional
	public TrancaDTO create(Tranca newEntity) {
		newEntity.setStatus(StatusTranca.NOVA.getStatus());
		Tranca updatedEntity = baseRepository.save(newEntity);
		return updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public TrancaDTO update(Tranca updated) {
		Tranca originalEntity = baseRepository.findById(updated.getId()).get();

		if (!originalEntity.getStatus().equals(updated.getStatus())
				|| originalEntity.getNumero() != updated.getNumero()) {
			throw new BusinessException("Status e Numero nao podem ser editados.");
		}

		Tranca updatedEntity = baseRepository.save(updated);

		return updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<Tranca> opEntity = baseRepository.findById(id);
		if (!opEntity.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		} else if (opEntity.get().getBicicleta() != null) {
			throw new BusinessException("Tranca com bicicleta");
		}
		baseRepository.delete(opEntity.get());
	}

	@Transactional
	public void integrarNaRede(Integer idTotem, Integer idTranca, Integer idFuncionario) {
		Tranca tranca = baseRepository.findById(idTranca).get();
		Totem totem = totemRepository.findById(idTotem).get();
		
		if(totem.getTrancas() == null) {
			totem.setTrancas(new ArrayList<>());
		}
		
		totem.getTrancas().add(tranca);
		
		tranca.setStatus(StatusTranca.LIVRE.getStatus());
		tranca.setTotem(totem);
		
		sendEmail(idFuncionario, urlGetFuncionario, urlEnviarEmail, tranca);
		
		baseRepository.save(tranca);
	}

	@Transactional
	public void retirarDaRede(Integer idTotem, Integer idTranca, Integer idFuncionario, String statusAcaoReparador) {

		Tranca tranca = baseRepository.findById(idTranca).get();
		Totem totem = totemRepository.findById(idTotem).get();
		
		tranca.setStatus(StatusTranca.valueOf(statusAcaoReparador).getStatus());
		
		tranca.setTotem(null);
		totem.getTrancas().remove(tranca);
		
		sendEmail(idFuncionario, urlGetFuncionario, urlEnviarEmail, tranca);
		
		baseRepository.save(tranca);
		totemRepository.save(totem);
	}
	
	@Transactional
	public Tranca trancar(Integer idTranca, Integer idBicicleta) {
		
		Bicicleta bicicleta = null;
		
		Tranca tranca = baseRepository.findById(idTranca).get();
		tranca.setStatus(StatusTranca.OCUPADA.getStatus());
		
		if(idBicicleta != 0) {
			bicicleta = bicicletaRepository.findById(idBicicleta).get(); 
			bicicleta.setStatus(StatusBicicleta.DISPONIVEL.getStatus());
			tranca.setBicicleta(bicicleta);
			bicicleta.setTranca(tranca);
			bicicletaRepository.save(bicicleta);
		}
		
		return baseRepository.save(tranca);
	}
	
	public Tranca destrancar(Integer idTranca, Integer idBicicleta) {
		
		Bicicleta bicicleta = null;
		
		Tranca tranca = baseRepository.findById(idTranca).get();
		tranca.setStatus(StatusTranca.LIVRE.getStatus());
		
		if(idBicicleta != 0) {
			bicicleta = bicicletaRepository.findById(idBicicleta).get(); 
			bicicleta.setStatus(StatusBicicleta.EM_USO.getStatus());
			bicicleta.setTranca(tranca);
			bicicletaRepository.save(bicicleta);
		}
		
		return baseRepository.save(tranca);
	}
	
    public void sendEmail(Integer idFuncionario, String urlGetFuncionario, String urlEnviarEmail, Tranca tranca) {
    	
        String requestUrl = urlGetFuncionario + "/" + idFuncionario.toString();

        HttpResponse<JsonNode> responseFuncionario = Unirest.get(requestUrl).asJson();

        if (responseFuncionario.getStatus() == 200) {
            JsonNode jsonNode = responseFuncionario.getBody();

            String emailValue = jsonNode.getObject().getString("email");
            String matricula = jsonNode.getObject().getString("documento");
            
            String corpoEmail = new Date().toString()
                    + "\nBicicleta:"
                    + tranca.getNumero()
                    + "\nMatricula:"
                    + matricula;
            
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
