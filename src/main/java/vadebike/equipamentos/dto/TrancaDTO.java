package vadebike.equipamentos.dto;

import java.util.Date;

import vadebike.equipamentos.model.Tranca;

public class TrancaDTO extends BaseDTO<Tranca>{
	
	private Integer id;
	
	private String marca;
	
	private String modelo;
	
	private Date ano;
	
	private Integer numero;
	
	private String status;
	
	@Override
	public Tranca convertToEntity(Integer id) {
		if(id != null) {
			this.id = id;
		}
		
		return Tranca.builder()
			.id(this.id)
			.ano(ano)
			.marca(marca)
			.modelo(modelo)
			.status(status)
			.numero(numero)
			.build();
	}

}
