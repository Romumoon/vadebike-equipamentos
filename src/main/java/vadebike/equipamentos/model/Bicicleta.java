package vadebike.equipamentos.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.enums.StatusBicicleta;

@Entity(name = "bicicleta")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bicicleta extends BaseEntity{
	
    private String marca;
    
    private String modelo;

    @DateTimeFormat(pattern = "yyyy")
    private Date ano;
    
    private Integer numero;
    
    private String status;

	@Override
	public <D> D convertToDto() {
		// TODO Auto-generated method stub
		return null;
	}
}
