package vadebike.equipamentos.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vadebike.equipamentos.model.Bicicleta;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicicletaDTO extends BaseDTO<Bicicleta>{
    
    private String marca;
    
    private String modelo;
    
    @DateTimeFormat(pattern = "yyyy")
    private Date ano;
    
    private Integer numero;
    
    private String status;
	
	@Override
	public Bicicleta convertToEntity(Integer id) {
		return Bicicleta.builder()
			.id(id)
			.ano(ano)
			.marca(marca)
			.modelo(modelo)
			.status(status)
			.numero(numero)
			.build();
	}

}
