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
import vadebike.equipamentos.dto.IntegraTrancaRedeDTO;
import vadebike.equipamentos.dto.RetiraTrancaRedeDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.dto.TrancarDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TrancaService;

@ExtendWith(MockitoExtension.class)
class TrancaControllerTest {

	@Mock
	TrancaService trancaService;

	@InjectMocks
	TrancaController trancaController;

	@Test
	void listAll() {
		ResponseEntity<Object> expectedResponse = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		when(trancaService.findAlltoDTO()).thenReturn(new ArrayList<>());

		ResponseEntity<Object> actualResponse = trancaController.listAll();

		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	void findBicicletaByTrancaId() {
		Integer id = 1;
		Bicicleta bicicleta = new Bicicleta();
		bicicleta.setId(id);
		BicicletaDTO bicicletaDTO = bicicleta.convertToDto();
		when(trancaService.findBicicletaByTrancaId(id)).thenReturn(bicicletaDTO);

		ResponseEntity<BicicletaDTO> actualResponse = trancaController.findBicicletaByTrancaId(id);

		assertEquals(bicicletaDTO, actualResponse.getBody());
	}

	@Test
	void create() {
		TrancaDTO trancaDTO = new TrancaDTO();
		Tranca tranca = trancaDTO.convertToEntity(null);
		when(trancaService.create(tranca)).thenReturn(trancaDTO);

		ResponseEntity<TrancaDTO> actualResponse = trancaController.create(trancaDTO);

		assertEquals(trancaDTO, actualResponse.getBody());
	}

	@Test
	void findById() {
		Integer id = 1;
		Tranca tranca = new Tranca();
		tranca.setId(id);
		TrancaDTO trancaDTO = tranca.convertToDto();
		when(trancaService.findById(id)).thenReturn(trancaDTO);

		ResponseEntity<TrancaDTO> actualResponse = trancaController.findById(id);

		assertEquals(trancaDTO, actualResponse.getBody());
	}

	@Test
	void update() {
		Integer id = 1;
		TrancaDTO trancaDTO = new TrancaDTO();
		Tranca tranca = trancaDTO.convertToEntity(id);
		when(trancaService.update(tranca)).thenReturn(trancaDTO);

		ResponseEntity<TrancaDTO> actualResponse = trancaController.update(id, trancaDTO);

		assertEquals(trancaDTO, actualResponse.getBody());
	}

	@Test
	void delete() {
		Integer id = 1;
		doNothing().when(trancaService).delete(id);

		ResponseEntity<Object> actualResponse = trancaController.delete(id);

		assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
	}

	@Test
	void integrarNaRede() {
		IntegraTrancaRedeDTO dto = new IntegraTrancaRedeDTO();
		doNothing().when(trancaService).integrarNaRede(dto.getIdTotem(), dto.getIdTranca(), dto.getIdFuncionario());

		ResponseEntity<Object> actualResponse = trancaController.integrarNaRede(dto);

		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}
	
	@Test
	void retirarDaRede() {
	    Integer idTotem = 1;
	    Integer idTranca = 2;
	    Integer idFuncionario = 3;
	    String statusAcaoReparador = "REPARADO";

	    RetiraTrancaRedeDTO dto = new RetiraTrancaRedeDTO(idTotem, idTranca, idFuncionario, statusAcaoReparador);

	    doNothing().when(trancaService).retirarDaRede(dto.getIdTotem(), dto.getIdTranca(), dto.getIdFuncionario(), dto.getStatusAcaoReparador());

	    ResponseEntity<Object> actualResponse = trancaController.retirarDaRede(dto);

	    assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	void trancar() {
	    Integer id = 1;
	    Bicicleta bicicleta = new Bicicleta();
	    bicicleta.setId(id);
	    TrancaDTO trancaDTO = new TrancaDTO();
	    Tranca tranca = trancaDTO.convertToEntity(null);

	    // Corrected mock setup for the trancar method
	    when(trancaService.trancar(id, bicicleta.getId())).thenReturn(tranca);

	    ResponseEntity<TrancaDTO> actualResponse = trancaController.trancar(id, new TrancarDTO(bicicleta.getId()));

	    // Verifique apenas o status
	    assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	void destrancar() {
	    Integer id = 1;
	    Bicicleta bicicleta = new Bicicleta();
	    bicicleta.setId(id);
	    TrancaDTO trancaDTO = new TrancaDTO();
	    Tranca tranca = trancaDTO.convertToEntity(null);

	    // Corrected mock setup for the destrancar method
	    when(trancaService.destrancar(id, bicicleta.getId())).thenReturn(tranca);

	    ResponseEntity<TrancaDTO> actualResponse = trancaController.destrancar(id, new TrancarDTO(bicicleta.getId()));

	    // Verifique apenas o status
	    assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

}
