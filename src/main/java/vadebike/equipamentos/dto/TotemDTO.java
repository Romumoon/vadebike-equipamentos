package vadebike.equipamentos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vadebike.equipamentos.model.Totem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotemDTO extends BaseDTO<Totem>{
	
	private Integer id;
	
	private String localizacao;
	
	private String descricao;
	
	@Override
	public Totem convertToEntity(Integer id) {
		if(id != null) {
			this.id = id;
		}
		
		return Totem.builder()
				.id(this.id)
				.descricao(descricao)
				.localizacao(localizacao)
				.build();
	}

}
