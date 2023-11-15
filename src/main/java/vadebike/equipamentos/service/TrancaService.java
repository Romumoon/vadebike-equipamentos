package vadebike.equipamentos.service;

import org.springframework.stereotype.Service;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.ITrancaRepository;

@Service
public class TrancaService extends BaseServiceImpl<TrancaDTO, Tranca, ITrancaRepository>
	implements ITrancaService{

	public BicicletaDTO findBicicletaByTrancaId(Integer id) {
		return baseRepository.findById(id).get().getBicicleta().convertToDto();
	}
	
}
