package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class RetiraBicicletaRedeDTOTest {

    @Test
    void testBuilder() {
        RetiraBicicletaRedeDTO dto = RetiraBicicletaRedeDTO.builder()
                .idTranca(1)
                .idBicicleta(2)
                .idFuncionario(3)
                .statusAcaoReparador("Em Andamento")
                .build();

        assertNotNull(dto);
        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testGettersAndSetters() {
        RetiraBicicletaRedeDTO dto = new RetiraBicicletaRedeDTO();

        dto.setIdTranca(1);
        dto.setIdBicicleta(2);
        dto.setIdFuncionario(3);
        dto.setStatusAcaoReparador("Em Andamento");

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testAllArgsConstructor() {
        RetiraBicicletaRedeDTO dto = new RetiraBicicletaRedeDTO(1, 2, 3, "Em Andamento");

        assertEquals(1, dto.getIdTranca());
        assertEquals(2, dto.getIdBicicleta());
        assertEquals(3, dto.getIdFuncionario());
        assertEquals("Em Andamento", dto.getStatusAcaoReparador());
    }

    @Test
    void testNoArgsConstructor() {
        RetiraBicicletaRedeDTO dto = new RetiraBicicletaRedeDTO();

        assertNotNull(dto);
        assertNull(dto.getIdTranca());
        assertNull(dto.getIdBicicleta());
        assertNull(dto.getIdFuncionario());
        assertNull(dto.getStatusAcaoReparador());
    }

    @Test
    void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RetiraBicicletaRedeDTO dto = RetiraBicicletaRedeDTO.builder().build();
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}
