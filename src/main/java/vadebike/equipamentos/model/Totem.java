package vadebike.equipamentos.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.TotemDTO;

@Entity(name = "totem")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Totem extends BaseEntity{
	
	private String localizacao;
	
	private String descricao;

	@Override
	public TotemDTO convertToDto() {
		
		return TotemDTO.builder()
				.id(id)
				.localizacao(localizacao)
				.descricao(descricao)
				.build();
	}
}
