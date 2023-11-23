package vadebike.equipamentos.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vadebike.equipamentos.dto.TotemDTO;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.ITotemRepository;

@Service
public class TotemService extends BaseServiceImpl<TotemDTO, Totem, ITotemRepository>
implements ITotemService{

	public List<Tranca> listTrancas(Integer id) {
		return baseRepository.findById(id).get().getTrancas();
	}

	public List<Bicicleta> listBicicletas(Integer id) {
	        return baseRepository.findById(id).get().getTrancas().stream()
	                .map(Tranca::getBicicleta)
	                .filter(Objects::nonNull)
	                .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<Totem> opEntity = baseRepository.findById(id);
		if (!opEntity.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		} else if(opEntity.get().getTrancas() != null) {
			throw new BusinessException("Totem com trancas ou bicicletas");
		}
		baseRepository.delete(opEntity.get());
	}
}
