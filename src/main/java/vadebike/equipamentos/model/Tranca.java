package vadebike.equipamentos.model;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.TrancaDTO;

@Entity(name = "tranca")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tranca extends BaseEntity{
	
	private String localizacao;
	
	private String modelo;
	
	private Date anoDeFabricacao;
	
	private Integer numero;
	
	private String status;
	
	private Bicicleta bicicleta;
	
	@Override
	public TrancaDTO convertToDto() {

		return TrancaDTO.builder()
				.id(id)
				.localizacao(localizacao)
				.modelo(modelo)
				.anoDeFabricacao(anoDeFabricacao)
				.numero(numero)
				.status(status)
				.build();
	}

}
