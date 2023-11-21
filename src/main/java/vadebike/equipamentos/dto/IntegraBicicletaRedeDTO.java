package vadebike.equipamentos.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntegraBicicletaRedeDTO {
	
	@NotNull
	private Integer idTranca;
	
	@NotNull
	private Integer idBicicleta;
	
	@NotNull
	private Integer idFuncionario;
}
