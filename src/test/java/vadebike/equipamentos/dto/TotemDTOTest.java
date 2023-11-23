package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import vadebike.equipamentos.model.Totem;

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
    void testConvertToEntityWithNullId() {
        TotemDTO totemDTO = TotemDTO.builder()
                .id(null)
                .localizacao("Local A")
                .descricao("Descrição A")
                .build();

        Totem totem = totemDTO.convertToEntity(null);

        assertNotNull(totem);
        assertNull(totem.getId());  // O ID deve ser nulo quando não fornecido
        assertEquals("Local A", totem.getLocalizacao());
        assertEquals("Descrição A", totem.getDescricao());
    }

    @Test
    void testConvertToEntityWithNotNullId() {
        Integer id = 1;
        TotemDTO totemDTO = TotemDTO.builder()
                .id(id)
                .localizacao("Local A")
                .descricao("Descrição A")
                .build();

        Totem totem = totemDTO.convertToEntity(null);

        assertNotNull(totem);
        assertEquals(id, totem.getId());  // O ID deve ser o mesmo passado no builder
        assertEquals("Local A", totem.getLocalizacao());
        assertEquals("Descrição A", totem.getDescricao());
    }
}
