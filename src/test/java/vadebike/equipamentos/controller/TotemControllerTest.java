package vadebike.equipamentos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TotemDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TotemService;

@ExtendWith(MockitoExtension.class)
class TotemControllerTest {

    @Mock
    TotemService totemService;

    @InjectMocks
    TotemController totemController;

    @Test
    void listAll() {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        when(totemService.findAlltoDTO()).thenReturn(new ArrayList<>());

        ResponseEntity<Object> actualResponse = totemController.listAll();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void create() {
        TotemDTO totemDTO = new TotemDTO();
        Totem totem = totemDTO.convertToEntity(null);
        when(totemService.create(totem)).thenReturn(totemDTO);

        ResponseEntity<TotemDTO> actualResponse = totemController.create(totemDTO);

        assertEquals(totemDTO, actualResponse.getBody());
    }

    @Test
    void update() {
        Integer id = 1;
        TotemDTO totemDTO = new TotemDTO();
        Totem totem = totemDTO.convertToEntity(id);
        when(totemService.update(totem)).thenReturn(totemDTO);

        ResponseEntity<TotemDTO> actualResponse = totemController.update(id, totemDTO);

        assertEquals(totemDTO, actualResponse.getBody());
    }

    @Test
    void delete() {
        Integer id = 1;
        doNothing().when(totemService).delete(id);

        ResponseEntity<Object> actualResponse = totemController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
    }

    @Test
    void listBicicletas() {
        Integer id = 1;
        List<Bicicleta> bicicletas = new ArrayList<>();
        when(totemService.listBicicletas(id)).thenReturn(bicicletas);

        ResponseEntity<List<BicicletaDTO>> actualResponse = totemController.listBicicletas(id);

        assertEquals(bicicletas.stream().map(Bicicleta::convertToDto).toList(), actualResponse.getBody());
    }

    @Test
    void listTrancas() {
        Integer id = 1;
        List<Tranca> trancas = new ArrayList<>();
        when(totemService.listTrancas(id)).thenReturn(trancas);

        ResponseEntity<List<TrancaDTO>> actualResponse = totemController.listTrancas(id);

        assertEquals(trancas.stream().map(Tranca::convertToDto).toList(), actualResponse.getBody());
    }
}

