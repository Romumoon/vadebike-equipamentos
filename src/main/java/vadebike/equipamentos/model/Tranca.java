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
	
	private String marca;
	
	private String modelo;
	
	private Date ano;
	
	private Integer numero;
	
	private String status;
	
	@Override
	public TrancaDTO convertToDto() {

		return TrancaDTO.builder()
				.id(id)
				.marca(marca)
				.modelo(modelo)
				.ano(ano)
				.numero(numero)
				.status(status)
				.build();
	}

}
