package vadebike.equipamentos.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.repository.IBicicletaRepository;

@Service
public class BicicletaService extends BaseServiceImpl<BicicletaDTO, Bicicleta, IBicicletaRepository>
	implements IBicicletaService{
	
	@Override
	@Transactional
	public BicicletaDTO create(Bicicleta newEntity) {
		Random random = new Random();
		
		Integer numero = random.nextInt();
		
		while(baseRepository.findByNumero(numero) != null) {
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
		
		if(!originalEntity.getStatus().equals(updated.getStatus()) || originalEntity.getNumero() != updated.getNumero()) {
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
		} else if(opEntity.get().getTranca() != null || !opEntity.get().getStatus().equals(StatusBicicleta.APOSENTADA.getStatus())) {
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
}