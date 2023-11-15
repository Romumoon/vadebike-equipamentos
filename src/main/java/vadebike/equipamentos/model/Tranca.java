package vadebike.equipamentos.model;

import java.util.Date;

import jakarta.persistence.Column;
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
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private Date anoDeFabricacao;
	
	@Column(nullable = false)
	private Integer numero;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
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
