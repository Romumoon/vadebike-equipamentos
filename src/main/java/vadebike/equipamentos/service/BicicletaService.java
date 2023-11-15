package vadebike.equipamentos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.repository.IBicicletaRepository;

@Service
public class BicicletaService extends BaseServiceImpl<BicicletaDTO, Bicicleta, IBicicletaRepository>
	implements IBicicletaService{
	
	@Override
	@Transactional
	public BicicletaDTO create(Bicicleta newEntity) {
		
		newEntity.setStatus(StatusBicicleta.NOVA.getStatus());
		
		Bicicleta updatedEntity = baseRepository.save(newEntity);
		return updatedEntity.convertToDto();
	}
}
