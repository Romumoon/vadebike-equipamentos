package vadebike.equipamentos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class EnviarEmailDTO {
	
	@JsonProperty("destinatario")
	private String destinatario;
	
	@JsonProperty("assunto")
	private String assunto;
	
	@JsonProperty("mensagem")
	private String mensagem;
}
