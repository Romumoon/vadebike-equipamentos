package vadebike.equipamentos.service;

import org.springframework.stereotype.Service;

import vadebike.equipamentos.dto.TotemDTO;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.repository.ITotemRepository;

@Service
public class TotemService extends BaseServiceImpl<TotemDTO, Totem, ITotemRepository>
implements ITotemService{

}
