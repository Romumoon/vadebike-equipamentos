package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class RetiraTrancaRedeDTOTest {

    @Test
    void testBuilder() {
        RetiraTrancaRedeDTO dto = RetiraTrancaRedeDTO.builder()
                .idTranca(1)
                .idTotem(2)
                .idFuncionario(3)
                .statusAcaoReparador("Em Andamento")
                .build();

        assertNotNull(dto);
        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testGettersAndSetters() {
        RetiraTrancaRedeDTO dto = new RetiraTrancaRedeDTO();

        dto.setIdTranca(1);
        dto.setIdTotem(2);
        dto.setIdFuncionario(3);
        dto.setStatusAcaoReparador("Em Andamento");

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testAllArgsConstructor() {
        RetiraTrancaRedeDTO dto = new RetiraTrancaRedeDTO(1, 2, 3, "Em Andamento");

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testNoArgsConstructor() {
        RetiraTrancaRedeDTO dto = new RetiraTrancaRedeDTO();

        assertNotNull(dto);
        assertNull(dto.getIdTranca());
        assertNull(dto.getIdTotem());
        assertNull(dto.getIdFuncionario());
        assertNull(dto.getStatusAcaoReparador());
    }

    @Test
    void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RetiraTrancaRedeDTO dto = RetiraTrancaRedeDTO.builder().build();
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}
