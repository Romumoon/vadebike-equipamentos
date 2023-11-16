package vadebike.equipamentos.repository;

import org.springframework.stereotype.Repository;

import vadebike.equipamentos.model.Bicicleta;

@Repository
public interface IBicicletaRepository extends IBaseRepository<Bicicleta>{
	
	Bicicleta findByNumero(Integer numero);
}
