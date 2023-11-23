package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class IntegraTrancaRedeDTOTest {

    @Test
    void testBuilder() {
        IntegraTrancaRedeDTO dto = IntegraTrancaRedeDTO.builder()
                .idTranca(1)
                .idTotem(2)
                .idFuncionario(3)
                .build();

        assertNotNull(dto);
        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testGettersAndSetters() {
        IntegraTrancaRedeDTO dto = new IntegraTrancaRedeDTO();

        dto.setIdTranca(1);
        dto.setIdTotem(2);
        dto.setIdFuncionario(3);

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testAllArgsConstructor() {
        IntegraTrancaRedeDTO dto = new IntegraTrancaRedeDTO(1, 2, 3);

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdTotem());
        assertEquals(3, dto.getIdFuncionario());
    }

    @Test
    void testNoArgsConstructor() {
        IntegraTrancaRedeDTO dto = new IntegraTrancaRedeDTO();

        assertNotNull(dto);
        assertNull(dto.getIdTranca());
        assertNull(dto.getIdTotem());
        assertNull(dto.getIdFuncionario());
    }

    @Test
    void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        IntegraTrancaRedeDTO dto = IntegraTrancaRedeDTO.builder().build();
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}
