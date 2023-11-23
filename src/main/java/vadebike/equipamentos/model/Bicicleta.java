package vadebike.equipamentos.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.BicicletaDTO;

@Entity(name = "bicicleta")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bicicleta extends BaseEntity{
	
	@Column(nullable = false)
    private String marca;
    
	@Column(nullable = false)
    private String modelo;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy")
    private Date ano;
    
    @Column(unique = true, nullable = false)
    private Integer numero;
    
    @Column(nullable = false)
    private String status;
    
    @OneToOne(mappedBy = "bicicleta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Tranca tranca;

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
