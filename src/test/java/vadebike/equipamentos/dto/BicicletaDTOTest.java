package vadebike.equipamentos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import vadebike.equipamentos.model.Bicicleta;

class BicicletaDTOTest {

    @Test
    void testBuilder() {
        BicicletaDTO bicicletaDTO = BicicletaDTO.builder()
                .id(1)
                .marca("Marca")
                .modelo("Modelo")
                .ano(new Date())
                .numero(123)
                .status("Ativo")
                .build();

        assertNotNull(bicicletaDTO);
        assertEquals(1, bicicletaDTO.getId());
        assertEquals("Marca", bicicletaDTO.getMarca());
        assertEquals("Modelo", bicicletaDTO.getModelo());
        assertNotNull(bicicletaDTO.getAno());
        assertEquals(123, bicicletaDTO.getNumero());
        assertEquals("Ativo", bicicletaDTO.getStatus());
    }

    @Test
    void testGettersAndSetters() {
        BicicletaDTO bicicletaDTO = new BicicletaDTO();

        bicicletaDTO.setId(1);
        bicicletaDTO.setMarca("Marca");
        bicicletaDTO.setModelo("Modelo");
        bicicletaDTO.setAno(new Date());
        bicicletaDTO.setNumero(123);
        bicicletaDTO.setStatus("Ativo");

        assertEquals(1, bicicletaDTO.getId());
        assertEquals("Marca", bicicletaDTO.getMarca());
        assertEquals("Modelo", bicicletaDTO.getModelo());
        assertNotNull(bicicletaDTO.getAno());
        assertEquals(123, bicicletaDTO.getNumero());
        assertEquals("Ativo", bicicletaDTO.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        Date ano = new Date();
        BicicletaDTO bicicletaDTO = new BicicletaDTO(1, "Marca", "Modelo", ano, 123, "Ativo");

        assertEquals(1, bicicletaDTO.getId());
        assertEquals("Marca", bicicletaDTO.getMarca());
        assertEquals("Modelo", bicicletaDTO.getModelo());
        assertEquals(ano, bicicletaDTO.getAno());
        assertEquals(123, bicicletaDTO.getNumero());
        assertEquals("Ativo", bicicletaDTO.getStatus());
    }

    @Test
    void testNoArgsConstructor() {
        BicicletaDTO bicicletaDTO = new BicicletaDTO();

        assertNotNull(bicicletaDTO);
        assertNull(bicicletaDTO.getId());
        assertNull(bicicletaDTO.getMarca());
        assertNull(bicicletaDTO.getModelo());
        assertNull(bicicletaDTO.getAno());
        assertNull(bicicletaDTO.getNumero());
        assertNull(bicicletaDTO.getStatus());
    }

    @Test
    void testConvertToEntity() {
        BicicletaDTO bicicletaDTO = new BicicletaDTO(1, "Marca", "Modelo", new Date(), 123, "Ativo");

        Bicicleta bicicleta = bicicletaDTO.convertToEntity(null);

        assertNotNull(bicicleta);
        assertEquals(1, bicicleta.getId());
        assertEquals("Marca", bicicleta.getMarca());
        assertEquals("Modelo", bicicleta.getModelo());
        assertNotNull(bicicleta.getAno());
        assertEquals(123, bicicleta.getNumero());
        assertEquals("Ativo", bicicleta.getStatus());
    }
}

