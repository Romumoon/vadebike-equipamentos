package vadebike.equipamentos.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vadebike.equipamentos.controller.TrancaController;
import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TrancaService;

@SpringBootTest
public class TrancaControllerIntegrationTest {

    @Autowired
    private TrancaController trancaController;
    
    @Autowired
    private TrancaService trancaService;

    @BeforeEach
    public void setUp() {
        trancaService = Mockito.mock(TrancaService.class);
    }
    
    @Test
    public void listAll_shouldReturnAllTrancas() throws ParseException {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        List<Object> expectedTrancas = Arrays.asList(
                new TrancaDTO(1, "Localizacao1", "Modelo1", dateFormatter.parse("2022-01-01"), 123, "LIVRE", 1),
                new TrancaDTO(2, "Localizacao2", "Modelo2", dateFormatter.parse("2021-01-01"), 456, "NOVA", 2),
                new TrancaDTO(3, "Localizacao3", "Modelo3", dateFormatter.parse("2020-01-01"), 789, "LIVRE", null)
        );
//        Mockito.when(trancaService.findAlltoDTO()).thenReturn(expectedTrancas);

        ResponseEntity<Object> response = trancaController.listAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Object> actualTrancas = (List<Object>) response.getBody();
        
        Assertions.assertEquals(actualTrancas.size(), 3);
        
        for (int i = 0; i < expectedTrancas.size(); i++) {
            TrancaDTO expectedTranca = (TrancaDTO)expectedTrancas.get(i);
            TrancaDTO actualTranca = (TrancaDTO)actualTrancas.get(i);

            Assertions.assertEquals(expectedTranca.getId(), actualTranca.getId());
            Assertions.assertEquals(expectedTranca.getLocalizacao(), actualTranca.getLocalizacao());
            Assertions.assertEquals(expectedTranca.getModelo(), actualTranca.getModelo());
            Assertions.assertEquals(expectedTranca.getAnoDeFabricacao(), actualTranca.getAnoDeFabricacao());
            Assertions.assertEquals(expectedTranca.getNumero(), actualTranca.getNumero());
            Assertions.assertEquals(expectedTranca.getStatus(), actualTranca.getStatus());
            Assertions.assertEquals(expectedTranca.getBicicletaId(), actualTranca.getBicicletaId());
        }
    }
    
    @Test
    public void findBicicletaByTrancaId_shouldReturnBicicletaDTO() throws ParseException {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        BicicletaDTO expectedBicicletaDTO = new BicicletaDTO(1, "Marca1", "Modelo1", dateFormatter.parse("2022-01-01"), 123, "DISPONÍVEL");
                
        Mockito.when(trancaService.findBicicletaByTrancaId(1)).thenReturn(expectedBicicletaDTO);

        ResponseEntity<BicicletaDTO> response = trancaController.findBicicletaByTrancaId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        
        BicicletaDTO actualBicicletaDTO = response.getBody();
        assertEquals(expectedBicicletaDTO.getId(), actualBicicletaDTO.getId());
        assertEquals(expectedBicicletaDTO.getMarca(), actualBicicletaDTO.getMarca());
        assertEquals(expectedBicicletaDTO.getModelo(), actualBicicletaDTO.getModelo());
        assertEquals(expectedBicicletaDTO.getAno(), actualBicicletaDTO.getAno());
        assertEquals(expectedBicicletaDTO.getNumero(), actualBicicletaDTO.getNumero());
        assertEquals(expectedBicicletaDTO.getStatus(), actualBicicletaDTO.getStatus());
    }
    
    @Test
    public void create_shouldReturnTrancaDTO() throws ParseException {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); 

    	TrancaDTO expectedTrancaDTO = new TrancaDTO(null, "Localizacao1", "Modelo1", dateFormatter.parse("2022-01-01"), 123, "NOVA", null);
        
        ResponseEntity<TrancaDTO> response = trancaController.create(expectedTrancaDTO);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        
        TrancaDTO actualTrancaDTO = response.getBody();
        Assertions.assertEquals(expectedTrancaDTO.getLocalizacao(), actualTrancaDTO.getLocalizacao());
        Assertions.assertEquals(expectedTrancaDTO.getModelo(), actualTrancaDTO.getModelo());
        Assertions.assertEquals(expectedTrancaDTO.getAnoDeFabricacao(), actualTrancaDTO.getAnoDeFabricacao());
        Assertions.assertEquals(expectedTrancaDTO.getNumero(), actualTrancaDTO.getNumero());
        Assertions.assertEquals(expectedTrancaDTO.getStatus(), actualTrancaDTO.getStatus());
        Assertions.assertEquals(expectedTrancaDTO.getBicicletaId(), actualTrancaDTO.getBicicletaId());
    }
    
    @Test
    public void findById_shouldReturnTrancaDTO_whenTrancaExists() throws ParseException {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
    	
    	TrancaDTO expectedTrancaDTO = new TrancaDTO(1, "Localizacao1", "Modelo1", dateFormatter.parse("2022-01-01"), 123, "LIVRE", 1);
        
        ResponseEntity<TrancaDTO> response = trancaController.findById(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        
        TrancaDTO actualTrancaDTO = response.getBody();
        Assertions.assertEquals(expectedTrancaDTO.getId(), actualTrancaDTO.getId());
        Assertions.assertEquals(expectedTrancaDTO.getLocalizacao(), actualTrancaDTO.getLocalizacao());
        Assertions.assertEquals(expectedTrancaDTO.getModelo(), actualTrancaDTO.getModelo());
        Assertions.assertEquals(expectedTrancaDTO.getAnoDeFabricacao(), actualTrancaDTO.getAnoDeFabricacao());
        Assertions.assertEquals(expectedTrancaDTO.getNumero(), actualTrancaDTO.getNumero());
        Assertions.assertEquals(expectedTrancaDTO.getStatus(), actualTrancaDTO.getStatus());
        Assertions.assertEquals(expectedTrancaDTO.getBicicletaId(), actualTrancaDTO.getBicicletaId());
    }
}

