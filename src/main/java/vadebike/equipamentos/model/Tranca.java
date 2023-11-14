package vadebike.equipamentos.model;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "tranca")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tranca extends BaseEntity{
	
	private String marca;
	
	private String modelo;
	
	private Date ano;
	
	private Integer numero;
	
	private String status;
	
	@Override
	public <D> D convertToDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
