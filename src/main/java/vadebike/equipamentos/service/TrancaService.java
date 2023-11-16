package vadebike.equipamentos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.enums.StatusTranca;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.ITrancaRepository;

@Service
public class TrancaService extends BaseServiceImpl<TrancaDTO, Tranca, ITrancaRepository> implements ITrancaService {

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

		if (!originalEntity.getStatus().equals(updated.getStatus()) || originalEntity.getNumero() != updated.getNumero()) {
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
}
