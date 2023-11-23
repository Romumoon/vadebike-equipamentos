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
public class IntegraTrancaRedeDTO {
	
	@NotNull
	private Integer idTranca;
	
	@NotNull
	private Integer idTotem;
	
	@NotNull
	private Integer idFuncionario;
}
