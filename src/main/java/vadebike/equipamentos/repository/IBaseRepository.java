package vadebike.equipamentos.repository;

import vadebike.equipamentos.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<E> extends JpaRepository<E,Integer>, CrudRepository<E,Integer> {

}
