package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TrancarDTOTest {

    @Test
    void testBuilder() {
        TrancarDTO trancarDTO = TrancarDTO.builder()
                .idBicicleta(1)
                .build();

        assertNotNull(trancarDTO);
        assertEquals(1, trancarDTO.getIdBicicleta());
    }

    @Test
    void testGettersAndSetters() {
        TrancarDTO trancarDTO = new TrancarDTO();

        trancarDTO.setIdBicicleta(1);

        assertEquals(1, trancarDTO.getIdBicicleta());
    }

    @Test
    void testAllArgsConstructor() {
        TrancarDTO trancarDTO = new TrancarDTO(1);

        assertEquals(1, trancarDTO.getIdBicicleta());
    }

    @Test
    void testNoArgsConstructor() {
        TrancarDTO trancarDTO = new TrancarDTO();

        assertNotNull(trancarDTO);
        assertNull(trancarDTO.getIdBicicleta());
    }
}
