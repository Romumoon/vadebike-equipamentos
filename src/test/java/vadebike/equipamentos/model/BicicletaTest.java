package vadebike.equipamentos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import vadebike.equipamentos.dto.BicicletaDTO;

class BicicletaTest {

    @Test
    void testConstrutores() {
        Bicicleta bicicleta = new Bicicleta();
        assertNotNull(bicicleta);

        Bicicleta bicicletaComValores = new Bicicleta("Marca", "Modelo", new Date(), 123, "Status", new Tranca());
        assertNotNull(bicicletaComValores);
    }

    @Test
    void testGettersAndSetters() {
    	
        Date date = new Date();
    	
        Bicicleta bicicleta = new Bicicleta();
        assertNotNull(bicicleta);

        bicicleta.setId(1);
        bicicleta.setMarca("Marca");
        bicicleta.setModelo("Modelo");
        bicicleta.setAno(date);
        bicicleta.setNumero(123);
        bicicleta.setStatus("Status");
        bicicleta.setTranca(new Tranca());

        assertEquals(1, bicicleta.getId());
        assertEquals("Marca", bicicleta.getMarca());
        assertEquals("Modelo", bicicleta.getModelo());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        assertEquals(sdf.format(date), sdf.format(bicicleta.getAno())); // Assumindo um novo método getAnoFormatted()
        assertEquals(123, bicicleta.getNumero());
        assertEquals("Status", bicicleta.getStatus());
        assertNotNull(bicicleta.getTranca());
    }

    @Test
    void testBuilder() {
        Bicicleta bicicleta = Bicicleta.builder().id(1).marca("Marca").modelo("Modelo").ano(new Date()).numero(123)
                .status("Status").tranca(new Tranca()).build();
        assertNotNull(bicicleta);

        assertEquals(1, bicicleta.getId());
        assertEquals("Marca", bicicleta.getMarca());
        assertEquals("Modelo", bicicleta.getModelo());
        assertEquals(new Date(), bicicleta.getAno());
        assertEquals(123, bicicleta.getNumero());
        assertEquals("Status", bicicleta.getStatus());
        assertNotNull(bicicleta.getTranca());
    }

    @Test
    void testConvertToDto() {
        Date date = new Date();
    	
        Bicicleta bicicleta = new Bicicleta("Marca", "Modelo", date, 123, "Status", new Tranca());
        bicicleta.setId(1);
        BicicletaDTO dto = bicicleta.convertToDto();

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("Marca", dto.getMarca());
        assertEquals("Modelo", dto.getModelo());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        assertEquals(sdf.format(date), sdf.format(bicicleta.getAno())); // Assumindo um novo método getAnoFormatted()
        assertEquals(123, dto.getNumero());
        assertEquals("Status", dto.getStatus());
    }
}
