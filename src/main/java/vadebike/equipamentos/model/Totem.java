package vadebike.equipamentos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vadebike.equipamentos.dto.TotemDTO;

@Entity(name = "totem")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Totem extends BaseEntity{
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(nullable = true)
	private String descricao;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "totem")
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
