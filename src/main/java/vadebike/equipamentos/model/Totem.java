package vadebike.equipamentos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.TotemDTO;

@Entity(name = "totem")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Totem extends BaseEntity{
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(nullable = false)
	private String descricao;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "totem")
    @JoinColumn(name = "totem_id")
    private List<Tranca> trancas;
	
	@Override
	public TotemDTO convertToDto() {
		
		return TotemDTO.builder()
				.id(id)
				.localizacao(localizacao)
				.descricao(descricao)
				.build();
	}
}
