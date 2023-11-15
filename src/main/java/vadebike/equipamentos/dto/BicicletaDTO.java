package vadebike.equipamentos.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vadebike.equipamentos.model.Bicicleta;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BicicletaDTO extends BaseDTO<Bicicleta>{
    
	private Integer id;
	
    private String marca;
    
    private String modelo;
    
    @DateTimeFormat(pattern = "yyyy")
    private Date ano;
    
    private Integer numero;
    
    private String status;
	
	@Override
	public Bicicleta convertToEntity(Integer id) {
		if(id != null) {
			this.id = id;
		}
		
		return Bicicleta.builder()
			.id(this.id)
			.ano(ano)
			.marca(marca)
			.modelo(modelo)
			.status(status)
			.numero(numero)
			.build();
	}

}
