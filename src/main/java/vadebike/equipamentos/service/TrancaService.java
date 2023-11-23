package vadebike.equipamentos.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vadebike.equipamentos.dto.BicicletaDTO;
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
		
		if(tranca.getStatus().equals(StatusTranca.EM_REPARO.getStatus())) {
			
		}
		
		if(totem.getTrancas() == null) {
			totem.setTrancas(new ArrayList<>());
		}
		
		totem.getTrancas().add(tranca);
		
		tranca.setStatus(StatusTranca.LIVRE.getStatus());
		tranca.setTotem(totem);
		
		baseRepository.save(tranca);
	}

	@Transactional
	public void retirarDaRede(Integer idTotem, Integer idTranca, Integer idFuncionario, String statusAcaoReparador) {

		Tranca tranca = baseRepository.findById(idTranca).get();
		Totem totem = totemRepository.findById(idTotem).get();
		
		tranca.setStatus(StatusTranca.valueOf(statusAcaoReparador).getStatus());
		
		tranca.setTotem(null);
		totem.getTrancas().remove(tranca);
		
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
}
