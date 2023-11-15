package vadebike.equipamentos.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.BicicletaDTO;

@Entity(name = "bicicleta")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bicicleta extends BaseEntity{
	
	@Column(nullable = false)
    private String marca;
    
	@Column(nullable = false)
    private String modelo;

	@Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy")
    private Date ano;
    
    @Column(unique = true, nullable = false)
    private Integer numero;
    
    @Column(nullable = false)
    private String status;

	@Override
	public BicicletaDTO convertToDto() {

		return BicicletaDTO.builder()
				.id(id)
				.marca(marca)
				.modelo(modelo)
				.numero(numero)
				.ano(ano)
				.status(status)
				.build();
	}
}
