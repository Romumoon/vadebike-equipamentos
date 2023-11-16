package vadebike.equipamentos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date anoDeFabricacao;
	
	@Column(nullable = false)
	private Integer numero;
	
	@Column(nullable = false)
	private String status;
	
	@OneToOne	
	@JoinColumn(name = "bicicleta_id", unique = true)
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
