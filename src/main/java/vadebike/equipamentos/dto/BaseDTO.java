package vadebike.equipamentos.dto;

public abstract class BaseDTO<E> {

	public abstract <E> E convertToEntity(Integer id);


}
