package vadebike.equipamentos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.IntegraBicicletaRedeDTO;
import vadebike.equipamentos.dto.RetiraBicicletaRedeDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.service.BicicletaService;

@ExtendWith(MockitoExtension.class)
class BicicletaControllerTest {

    @Mock
    BicicletaService bicicletaService;

    @InjectMocks
    BicicletaController bicicletaController;

    @Test
    void listAll() {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        when(bicicletaService.findAlltoDTO()).thenReturn(new ArrayList<>());

        ResponseEntity<Object> actualResponse = bicicletaController.listAll();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void findById() {
        Integer id = 1;
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(id);
        BicicletaDTO bicicletaDTO = bicicleta.convertToDto();
        when(bicicletaService.findById(id)).thenReturn(bicicletaDTO);

        ResponseEntity<BicicletaDTO> actualResponse = bicicletaController.findById(id);

        assertEquals(bicicletaDTO, actualResponse.getBody());
    }

    @Test
    void update() {
        Integer id = 1;
        BicicletaDTO bicicletaDTO = new BicicletaDTO();
        Bicicleta bicicleta = bicicletaDTO.convertToEntity(id);
        when(bicicletaService.update(bicicleta)).thenReturn(bicicletaDTO);

        ResponseEntity<BicicletaDTO> actualResponse = bicicletaController.update(id, bicicletaDTO);

        assertEquals(bicicletaDTO, actualResponse.getBody());
    }

    @Test
    void updateStatus() {
        Integer id = 1;
        String acao = "DISPONIVEL";
        BicicletaDTO bicicletaDTO = new BicicletaDTO();
        when(bicicletaService.updateStatus(id, acao)).thenReturn(bicicletaDTO);

        ResponseEntity<BicicletaDTO> actualResponse = bicicletaController.updateStatus(id, acao);

        assertEquals(bicicletaDTO, actualResponse.getBody());
    }

    @Test
    void create() {
        BicicletaDTO bicicletaDTO = new BicicletaDTO();
        Bicicleta bicicleta = bicicletaDTO.convertToEntity(null);
        when(bicicletaService.create(bicicleta)).thenReturn(bicicletaDTO);

        ResponseEntity<BicicletaDTO> actualResponse = bicicletaController.create(bicicletaDTO);

        assertEquals(bicicletaDTO, actualResponse.getBody());
    }

    @Test
    void delete() {
        Integer id = 1;
        doNothing().when(bicicletaService).delete(id);

        ResponseEntity<Object> actualResponse = bicicletaController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
    }

    @Test
    void integrarNaRede() {
        IntegraBicicletaRedeDTO dto = new IntegraBicicletaRedeDTO();
        doNothing().when(bicicletaService).integrarNaRede(dto.getIdBicicleta(), dto.getIdTranca(), dto.getIdFuncionario());

        ResponseEntity<Object> actualResponse = bicicletaController.integrarNaRede(dto);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    void retirarDaRede() {
        RetiraBicicletaRedeDTO dto = new RetiraBicicletaRedeDTO();
        doNothing().when(bicicletaService).retirarDaRede(dto.getIdBicicleta(), dto.getIdTranca(), dto.getIdFuncionario(), dto.getStatusAcaoReparador());

        ResponseEntity<Object> actualResponse = bicicletaController.retirarDaRede(dto);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }
}

