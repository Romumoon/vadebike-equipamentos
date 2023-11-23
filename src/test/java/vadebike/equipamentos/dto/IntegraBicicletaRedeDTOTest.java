package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class IntegraBicicletaRedeDTOTest {

    @Test
    void testBuilder() {
        IntegraBicicletaRedeDTO dto = IntegraBicicletaRedeDTO.builder()
                .idTranca(1)
                .idBicicleta(2)
                .idFuncionario(3)
                .build();

        assertNotNull(dto);
        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testGettersAndSetters() {
        IntegraBicicletaRedeDTO dto = new IntegraBicicletaRedeDTO();

        dto.setIdTranca(1);
        dto.setIdBicicleta(2);
        dto.setIdFuncionario(3);

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testAllArgsConstructor() {
        IntegraBicicletaRedeDTO dto = new IntegraBicicletaRedeDTO(1, 2, 3);

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testNoArgsConstructor() {
        IntegraBicicletaRedeDTO dto = new IntegraBicicletaRedeDTO();

        assertNotNull(dto);
        assertNull(dto.getIdTranca());
        assertNull(dto.getIdBicicleta());
        assertNull(dto.getIdFuncionario());
    }

    @Test
    void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        IntegraBicicletaRedeDTO dto = IntegraBicicletaRedeDTO.builder().build();
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}

