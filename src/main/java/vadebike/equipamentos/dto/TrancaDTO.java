package vadebike.equipamentos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vadebike.equipamentos.model.Tranca;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrancaDTO extends BaseDTO<Tranca>{
	
	private Integer id;
	
	private String localizacao;
	
	private String modelo;
	
	private Date anoDeFabricacao;
	
	private Integer numero;
	
	private String status;
	
	@Override
	public Tranca convertToEntity(Integer id) {
		if(id != null) {
			this.id = id;
		}
		
		return Tranca.builder()
			.id(this.id)
			.anoDeFabricacao(anoDeFabricacao)
			.localizacao(localizacao)
			.modelo(modelo)
			.status(status)
			.numero(numero)
			.build();
	}

}
