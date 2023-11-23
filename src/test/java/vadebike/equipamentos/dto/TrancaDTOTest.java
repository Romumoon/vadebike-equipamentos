package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import vadebike.equipamentos.model.Tranca;

class TrancaDTOTest {

    @Test
    void testBuilder() {
        TrancaDTO trancaDTO = TrancaDTO.builder()
                .id(1)
                .localizacao("Local A")
                .modelo("Modelo A")
                .anoDeFabricacao(new Date())
                .numero(123)
                .status("Ativo")
                .bicicletaId(456)
                .build();

        assertNotNull(trancaDTO);
        assertEquals(1, trancaDTO.getId());
        assertEquals("Local A", trancaDTO.getLocalizacao());
        assertEquals("Modelo A", trancaDTO.getModelo());
        assertNotNull(trancaDTO.getAnoDeFabricacao());
        assertEquals(123, trancaDTO.getNumero());
        assertEquals("Ativo", trancaDTO.getStatus());
        assertEquals(456, trancaDTO.getBicicletaId());
    }

    @Test
    void testGettersAndSetters() {
        TrancaDTO trancaDTO = new TrancaDTO();

        trancaDTO.setId(1);
        trancaDTO.setLocalizacao("Local A");
        trancaDTO.setModelo("Modelo A");
        trancaDTO.setAnoDeFabricacao(new Date());
        trancaDTO.setNumero(123);
        trancaDTO.setStatus("Ativo");
        trancaDTO.setBicicletaId(456);

        assertEquals(1, trancaDTO.getId());
        assertEquals("Local A", trancaDTO.getLocalizacao());
        assertEquals("Modelo A", trancaDTO.getModelo());
        assertNotNull(trancaDTO.getAnoDeFabricacao());
        assertEquals(123, trancaDTO.getNumero());
        assertEquals("Ativo", trancaDTO.getStatus());
        assertEquals(456, trancaDTO.getBicicletaId());
    }

    @Test
    void testAllArgsConstructor() {
        TrancaDTO trancaDTO = new TrancaDTO(1, "Local A", "Modelo A", new Date(), 123, "Ativo", 456);

        assertEquals(1, trancaDTO.getId());
        assertEquals("Local A", trancaDTO.getLocalizacao());
        assertEquals("Modelo A", trancaDTO.getModelo());
        assertNotNull(trancaDTO.getAnoDeFabricacao());
        assertEquals(123, trancaDTO.getNumero());
        assertEquals("Ativo", trancaDTO.getStatus());
        assertEquals(456, trancaDTO.getBicicletaId());
    }

    @Test
    void testNoArgsConstructor() {
        TrancaDTO trancaDTO = new TrancaDTO();

        assertNotNull(trancaDTO);
        assertNull(trancaDTO.getId());
        assertNull(trancaDTO.getLocalizacao());
        assertNull(trancaDTO.getModelo());
        assertNull(trancaDTO.getAnoDeFabricacao());
        assertNull(trancaDTO.getNumero());
        assertNull(trancaDTO.getStatus());
        assertNull(trancaDTO.getBicicletaId());
    }

    @Test
    void testConvertToEntityWithId() {
        TrancaDTO trancaDTO = TrancaDTO.builder()
                .id(1)
                .localizacao("Local A")
                .modelo("Modelo A")
                .anoDeFabricacao(new Date())
                .numero(123)
                .status("Ativo")
                .bicicletaId(456)
                .build();

        Integer id = 2;  // O ID fornecido ao método convertToEntity

        Tranca tranca = trancaDTO.convertToEntity(id);

        assertNotNull(tranca);
        assertEquals(id, tranca.getId());
        assertEquals("Local A", tranca.getLocalizacao());
        assertEquals("Modelo A", tranca.getModelo());
        assertNotNull(tranca.getAnoDeFabricacao());
        assertEquals(123, tranca.getNumero());
        assertEquals("Ativo", tranca.getStatus());
        assertNotNull(tranca.getBicicleta());
        assertEquals(456, tranca.getBicicleta().getId());
    }

    @Test
    void testConvertToEntityWithNullId() {
        Integer id = 1;
        TrancaDTO trancaDTO = TrancaDTO.builder()
                .id(id)
                .localizacao("Local A")
                .modelo("Modelo A")
                .anoDeFabricacao(new Date())
                .numero(123)
                .status("Ativo")
                .bicicletaId(456)
                .build();

        Tranca tranca = trancaDTO.convertToEntity(null);

        assertNotNull(tranca);
        assertEquals(id, tranca.getId());  // O ID deve ser o mesmo passado no builder
        assertEquals("Local A", tranca.getLocalizacao());
        assertEquals("Modelo A", tranca.getModelo());
        assertNotNull(tranca.getAnoDeFabricacao());
        assertEquals(123, tranca.getNumero());
        assertEquals("Ativo", tranca.getStatus());
        assertNotNull(tranca.getBicicleta());
        assertEquals(456, tranca.getBicicleta().getId());
    }
}
