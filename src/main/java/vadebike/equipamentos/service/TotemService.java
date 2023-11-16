package vadebike.equipamentos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TotemDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.repository.ITotemRepository;

@Service
public class TotemService extends BaseServiceImpl<TotemDTO, Totem, ITotemRepository>
implements ITotemService{

	public List<TrancaDTO> listTrancas(Integer id) {
		return baseRepository.findById(id).get().getTrancasList().stream().map(n -> n.convertToDto()).collect(Collectors.toList());
	}

	public List<BicicletaDTO> listBicicletas(Integer id) {
		return baseRepository.findById(id).get().getBicicletasList().stream().map(n -> n.convertToDto()).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<Totem> opEntity = baseRepository.findById(id);
		if (!opEntity.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		} else if(!opEntity.get().getBicicletasList().isEmpty() || opEntity.get().getBicicletasList() != null
				|| !opEntity.get().getTrancasList().isEmpty() || opEntity.get().getTrancasList() != null) {
			throw new BusinessException("Totem com trancas ou bicicletas");
		}
		baseRepository.delete(opEntity.get());
	}
}
