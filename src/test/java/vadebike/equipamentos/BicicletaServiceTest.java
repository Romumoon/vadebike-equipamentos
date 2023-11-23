package vadebike.equipamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.enums.StatusTranca;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.IBicicletaRepository;
import vadebike.equipamentos.repository.ITrancaRepository;
import vadebike.equipamentos.service.BicicletaService;

@ExtendWith(MockitoExtension.class)
class BicicletaServiceTest {

    @Mock
    private IBicicletaRepository bicicletaRepository;
    
    @Mock
    private ITrancaRepository trancaRepository;

    @InjectMocks
    private BicicletaService bicicletaService;
    
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(bicicletaService, "baseRepository", bicicletaRepository);
	}
    
    @Test
    void testCreateBicicleta() {
        // Arrange
        Bicicleta newBicicleta = new Bicicleta();
        when(bicicletaRepository.findByNumero(anyInt())).thenReturn(null);
        when(bicicletaRepository.save(newBicicleta)).thenReturn(newBicicleta);

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.create(newBicicleta);

        assertNotNull(bicicletaDTO); // Verifica se o DTO não é nulo
    }

    @Test
    void testUpdateBicicleta() {
        // Arrange
        Bicicleta updatedBicicleta = new Bicicleta();
        updatedBicicleta.setId(1);
        updatedBicicleta.setStatus(StatusBicicleta.DISPONIVEL.getStatus());
        updatedBicicleta.setMarca("test");
        Bicicleta originalBicicleta = new Bicicleta();
        originalBicicleta.setId(1);
        originalBicicleta.setStatus(StatusBicicleta.DISPONIVEL.getStatus());
        originalBicicleta.setModelo("originalModelo"); // Adiciona um modelo diferente

        when(bicicletaRepository.findById(updatedBicicleta.getId())).thenReturn(Optional.of(originalBicicleta));
        when(bicicletaRepository.save(updatedBicicleta)).thenReturn(updatedBicicleta);

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.update(updatedBicicleta);

        // Assert
        assertEquals(originalBicicleta.getId(), bicicletaDTO.getId());
        assertNotEquals(originalBicicleta.getModelo(), bicicletaDTO.getModelo());
        assertEquals(originalBicicleta.getStatus(), bicicletaDTO.getStatus());
        // Adicione mais verificações conforme necessário
    }

    @Test
    void testUpdateBicicletaException() {
        // Arrange
        Bicicleta updatedBicicleta = new Bicicleta();
        updatedBicicleta.setId(1);
        Bicicleta originalBicicleta = new Bicicleta();
        originalBicicleta.setId(1);
        originalBicicleta.setStatus(StatusBicicleta.NOVA.getStatus());
        originalBicicleta.setNumero(123);

        when(bicicletaRepository.findById(updatedBicicleta.getId())).thenReturn(Optional.of(originalBicicleta));

        // Act & Assert
        assertThrows(BusinessException.class, () -> bicicletaService.update(updatedBicicleta));
    }

    @Test
    void testDeleteBicicletaWithNoContent() {
        // Arrange
        Integer bicicletaId = 1;
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoContentException.class, () -> bicicletaService.delete(bicicletaId));
    }

    @Test
    void testDeleteBicicletaWithTrancaOrNotAposentada() {
        // Arrange
        Integer bicicletaId = 1;
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setTranca(new Tranca()); // Assuming Tranca class exists
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));

        // Act & Assert
        assertThrows(BusinessException.class, () -> bicicletaService.delete(bicicletaId));
    }

    @Test
    void testDeleteBicicletaSuccess() {
        // Arrange
        Integer bicicletaId = 1;
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setStatus(StatusBicicleta.APOSENTADA.getStatus());
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));

        // Act
        bicicletaService.delete(bicicletaId);

        // Assert
        verify(bicicletaRepository).delete(any(Bicicleta.class));
    }

    @Test
    void testUpdateStatusBicicleta() {
        // Arrange
        Integer bicicletaId = 1;
        String acao = "disponivel";

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(bicicletaId);
        bicicleta.setStatus(StatusBicicleta.EM_USO.getStatus());

        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));
        when(bicicletaRepository.save(bicicleta)).thenReturn(bicicleta);

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.updateStatus(bicicletaId, acao);

        // Assert
        assertEquals(bicicletaId, bicicletaDTO.getId());
        assertEquals(StatusBicicleta.DISPONIVEL.getStatus(), bicicletaDTO.getStatus());
        // Add more assertions if needed
    }
    
    @Test
    void testIntegrarNaRede() {
        // Arrange
        Integer idBicicleta = 1;
        Integer idTranca = 2;
        Integer idFuncionario = 3;

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(idBicicleta);
        bicicleta.setStatus(StatusBicicleta.EM_REPARO.getStatus());

        Tranca tranca = new Tranca();
        tranca.setId(idTranca);
        tranca.setStatus(StatusTranca.LIVRE.getStatus());

        when(bicicletaRepository.findById(idBicicleta)).thenReturn(Optional.of(bicicleta));
        when(trancaRepository.findById(idTranca)).thenReturn(Optional.of(tranca));

        // Act
        bicicletaService.integrarNaRede(idBicicleta, idTranca, idFuncionario);

        // Assert
        assertEquals(StatusBicicleta.DISPONIVEL.getStatus(), bicicleta.getStatus());
        assertNotNull(bicicleta.getTranca());
        assertEquals(idTranca, bicicleta.getTranca().getId());
        assertEquals(StatusTranca.OCUPADA.getStatus(), bicicleta.getTranca().getStatus());
    }

    @Test
    void testRetirarDaRede() {
        // Arrange
        Integer idBicicleta = 1;
        Integer idTranca = 2;
        Integer idFuncionario = 3;
        String statusAcaoReparador = "EM_REPARO";

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(idBicicleta);
        bicicleta.setStatus(StatusBicicleta.EM_REPARO.getStatus());
        bicicleta.setTranca(new Tranca());

        Tranca tranca = new Tranca();
        tranca.setId(idTranca);
        tranca.setStatus(StatusTranca.OCUPADA.getStatus());
        tranca.setBicicleta(bicicleta);

        when(bicicletaRepository.findById(idBicicleta)).thenReturn(Optional.of(bicicleta));
        when(trancaRepository.findById(idTranca)).thenReturn(Optional.of(tranca));

        // Act
        bicicletaService.retirarDaRede(idBicicleta, idTranca, idFuncionario, statusAcaoReparador);

        // Assert
        assertEquals(StatusBicicleta.EM_REPARO.getStatus(), bicicleta.getStatus());
        assertNull(bicicleta.getTranca());
        assertNull(tranca.getBicicleta());
        assertEquals(StatusTranca.LIVRE.getStatus(), tranca.getStatus());
    }

    @Test
    void testRetirarDaRedeException() {
        // Arrange
        Integer idBicicleta = 1;
        Integer idTranca = 2;
        Integer idFuncionario = 3;
        String statusAcaoReparador = "EM_REPARO";

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(idBicicleta);
        bicicleta.setStatus(StatusBicicleta.DISPONIVEL.getStatus());

        Tranca tranca = new Tranca();
        tranca.setId(idTranca);
        tranca.setStatus(StatusTranca.LIVRE.getStatus());

        when(bicicletaRepository.findById(idBicicleta)).thenReturn(Optional.of(bicicleta));
        when(trancaRepository.findById(idTranca)).thenReturn(Optional.of(tranca));

        // Act & Assert
        assertThrows(BusinessException.class,
                () -> bicicletaService.retirarDaRede(idBicicleta, idTranca, idFuncionario, statusAcaoReparador));
    }
}
