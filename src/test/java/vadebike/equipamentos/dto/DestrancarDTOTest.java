package vadebike.equipamentos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DestrancarDTOTest {

    @Test
    void testBuilder() {
        DestrancarDTO destrancarDTO = DestrancarDTO.builder()
                .idBicicleta(1)
                .build();

        assertNotNull(destrancarDTO);
        assertEquals(1, destrancarDTO.getIdBicicleta());
    }

    @Test
    void testGettersAndSetters() {
        DestrancarDTO destrancarDTO = new DestrancarDTO();

        destrancarDTO.setIdBicicleta(1);

        assertEquals(1, destrancarDTO.getIdBicicleta());
    }

    @Test
    void testAllArgsConstructor() {
        DestrancarDTO destrancarDTO = new DestrancarDTO(1);

        assertEquals(1, destrancarDTO.getIdBicicleta());
    }

    @Test
    void testNoArgsConstructor() {
        DestrancarDTO destrancarDTO = new DestrancarDTO();

        assertNotNull(destrancarDTO);
        assertNull(destrancarDTO.getIdBicicleta());
    }
}

