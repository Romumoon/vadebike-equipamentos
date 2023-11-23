package vadebike.equipamentos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vadebike.equipamentos.model.Totem;

@Getter
@Setter
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
