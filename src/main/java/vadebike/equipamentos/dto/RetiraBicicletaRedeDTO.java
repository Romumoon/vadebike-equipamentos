package vadebike.equipamentos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetiraBicicletaRedeDTO {
	
	@NotNull
	private Integer idTranca;
	
	@NotNull
	private Integer idBicicleta;
	
	@NotNull
	private Integer idFuncionario;
	
	@NotNull
	private String statusAcaoReparador;
}
