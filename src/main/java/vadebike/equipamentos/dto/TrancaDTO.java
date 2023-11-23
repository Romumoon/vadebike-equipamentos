package vadebike.equipamentos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;

@Getter
@Setter
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
	
	private Integer bicicletaId;
	
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
			.bicicleta(Bicicleta.builder().id(bicicletaId).build())
			.status(status)
			.numero(numero)
			.build();
	}

}
