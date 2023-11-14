package vadebike.equipamentos.service;

import org.springframework.stereotype.Service;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.repository.IBicicletaRepository;

@Service
public class BicicletaService extends BaseServiceImpl<BicicletaDTO, Bicicleta, IBicicletaRepository>
	implements IBicicletaService{

}
