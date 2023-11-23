package vadebike.equipamentos.dto;

import org.junit.jupiter.api.Test;
import vadebike.equipamentos.model.Totem;

import static org.junit.jupiter.api.Assertions.*;

class TotemDTOTest {

    @Test
    void testBuilder() {
        TotemDTO totemDTO = TotemDTO.builder()
                .id(1)
                .localizacao("Local A")
                .descricao("Descrição A")
                .build();

        assertNotNull(totemDTO);
        assertEquals(1, totemDTO.getId());
        assertEquals("Local A", totemDTO.getLocalizacao());
        assertEquals("Descrição A", totemDTO.getDescricao());
    }

    @Test
    void testGettersAndSetters() {
        TotemDTO totemDTO = new TotemDTO();

        totemDTO.setId(1);
        totemDTO.setLocalizacao("Local A");
        totemDTO.setDescricao("Descrição A");

        assertEquals(1, totemDTO.getId());
        assertEquals("Local A", totemDTO.getLocalizacao());
        assertEquals("Descrição A", totemDTO.getDescricao());
    }

    @Test
    void testAllArgsConstructor() {
        TotemDTO totemDTO = new TotemDTO(1, "Local A", "Descrição A");

        assertEquals(1, totemDTO.getId());
        assertEquals("Local A", totemDTO.getLocalizacao());
        assertEquals("Descrição A", totemDTO.getDescricao());
    }

    @Test
    void testNoArgsConstructor() {
        TotemDTO totemDTO = new TotemDTO();

        assertNotNull(totemDTO);
        assertNull(totemDTO.getId());
        assertNull(totemDTO.getLocalizacao());
        assertNull(totemDTO.getDescricao());
    }

    @Test
    void testConvertToEntity() {
        TotemDTO totemDTO = new TotemDTO(1, "Local A", "Descrição A");

        Totem totem = totemDTO.convertToEntity(null);

        assertNotNull(totem);
        assertEquals(1, totem.getId());
        assertEquals("Local A", totem.getLocalizacao());
        assertEquals("Descrição A", totem.getDescricao());
    }
}
