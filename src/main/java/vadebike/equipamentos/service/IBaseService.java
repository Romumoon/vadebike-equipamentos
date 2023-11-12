package vadebike.equipamentos.service;

import java.util.List;

public interface IBaseService<D, E> {
	
    List<E> findAll();

    D findById(Integer id);

    E findEntityById(Integer id);

    D update(E updated);

    D create(E created);

    void delete(Integer id);

}
