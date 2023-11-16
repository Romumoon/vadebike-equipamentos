package vadebike.equipamentos.dto;

public abstract class BaseDTO<E> {

    public abstract E convertToEntity(Integer id);
}