package vadebike.equipamentos.service;

import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vadebike.equipamentos.dto.BaseDTO;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.BaseEntity;
import vadebike.equipamentos.repository.IBaseRepository;

public class BaseServiceImpl<D extends BaseDTO, E extends BaseEntity, R extends IBaseRepository<E>>
		implements IBaseService<D, E> {

	@Autowired
	protected R baseRepository;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<E> findAll() {
		return baseRepository.findAll();
	}
	
	public List<Object> findAlltoDTO() {
		return baseRepository.findAll().stream().map(E::convertToDto).collect(Collectors.toList());
	}

	@Override
	public D findById(Integer id) {
		return (D) findEntityById(id).convertToDto();

	}

	@Override
	public E findEntityById(Integer id) {
		Optional<E> objeto = baseRepository.findById(id);
		if (!objeto.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		}

		return objeto.get();
	}

	@Override
	@Transactional
	public D update(E updated) {
		E updatedEntity = baseRepository.save(updated);
		return (D) updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public D create(E newEntity) {
		E updatedEntity = baseRepository.save(newEntity);
		return (D) updatedEntity.convertToDto();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<E> opEntity = baseRepository.findById(id);
		if (!opEntity.isPresent()) {
			throw new NoContentException(String.valueOf(id));
		}
		baseRepository.delete(opEntity.get());
	}

}
