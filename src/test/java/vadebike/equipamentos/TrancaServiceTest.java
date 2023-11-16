package vadebike.equipamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.enums.StatusTranca;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.ITrancaRepository;
import vadebike.equipamentos.service.TrancaService;

@ExtendWith(MockitoExtension.class)
class TrancaServiceTest {

    @Mock
    private ITrancaRepository trancaRepository;

    @InjectMocks
    private TrancaService trancaService;
    
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(trancaService, "baseRepository", trancaRepository);
	}
    
    @Test
    void testFindBicicletaByTrancaId() {
        // Arrange
        Integer trancaId = 1;
        Tranca tranca = new Tranca();
        Bicicleta bicicleta = new Bicicleta(); // Assuming Bicicleta class exists
        tranca.setBicicleta(bicicleta);
        when(trancaRepository.findById(trancaId)).thenReturn(Optional.of(tranca));

        // Act
        BicicletaDTO bicicletaDTO = trancaService.findBicicletaByTrancaId(trancaId);

        // Assert
        // Implement your assertions here
    }

    @Test
    void testCreateTranca() {
        // Arrange
        Tranca newTranca = new Tranca();
        when(trancaRepository.save(newTranca)).thenReturn(newTranca);

        // Act
        TrancaDTO trancaDTO = trancaService.create(newTranca);

        // Assert
        // Implement your assertions here
    }

    @Test
    void testUpdateTranca() {
        // Arrange
        Tranca updatedTranca = new Tranca();
        updatedTranca.setId(1);
        updatedTranca.setStatus(StatusTranca.LIVRE.getStatus());
        updatedTranca.setNumero(123);

        Tranca originalTranca = new Tranca();
        originalTranca.setId(1);
        originalTranca.setStatus(StatusTranca.LIVRE.getStatus());
        originalTranca.setNumero(123);

        when(trancaRepository.findById(updatedTranca.getId())).thenReturn(Optional.of(originalTranca));
        when(trancaRepository.save(updatedTranca)).thenReturn(updatedTranca);

        // Act
        TrancaDTO trancaDTO = trancaService.update(updatedTranca);

        // Assert
        assertNotNull(trancaDTO);
        assertEquals(updatedTranca.getId(), trancaDTO.getId());
        assertEquals(updatedTranca.getStatus(), trancaDTO.getStatus());
        assertEquals(updatedTranca.getNumero(), trancaDTO.getNumero());
    }
    
    @Test
    void testUpdateTrancaThrowsException() {
        // Arrange
        Tranca updatedTranca = new Tranca();
        updatedTranca.setId(1);
        updatedTranca.setStatus(StatusTranca.LIVRE.getStatus());
        updatedTranca.setNumero(123);

        Tranca originalTranca = new Tranca();
        originalTranca.setId(1);
        originalTranca.setStatus(StatusTranca.LIVRE.getStatus());
        originalTranca.setNumero(456); // Different number

        when(trancaRepository.findById(updatedTranca.getId())).thenReturn(Optional.of(originalTranca));

        // Act & Assert
        assertThrows(BusinessException.class, () -> trancaService.update(updatedTranca));
    }

    @Test
    void testUpdateTrancaException() {
        // Arrange
        Tranca updatedTranca = new Tranca();
        updatedTranca.setId(1);
        Tranca originalTranca = new Tranca();
        originalTranca.setId(1);
        originalTranca.setStatus(StatusTranca.NOVA.getStatus());
        originalTranca.setNumero(123);

        when(trancaRepository.findById(updatedTranca.getId())).thenReturn(Optional.of(originalTranca));

        // Act & Assert
        assertThrows(BusinessException.class, () -> trancaService.update(updatedTranca));
    }

    @Test
    void testDeleteTrancaWithNoContent() {
        // Arrange
        Integer trancaId = 1;
        when(trancaRepository.findById(trancaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoContentException.class, () -> trancaService.delete(trancaId));
    }

    @Test
    void testDeleteTrancaWithBicicleta() {
        // Arrange
        Integer trancaId = 1;
        Tranca tranca = new Tranca();
        Bicicleta bicicleta = new Bicicleta(); // Assuming Bicicleta class exists
        tranca.setBicicleta(bicicleta);
        when(trancaRepository.findById(trancaId)).thenReturn(Optional.of(tranca));

        // Act & Assert
        assertThrows(BusinessException.class, () -> trancaService.delete(trancaId));
    }

    @Test
    void testDeleteTrancaSuccess() {
        // Arrange
        Integer trancaId = 1;
        when(trancaRepository.findById(trancaId)).thenReturn(Optional.of(new Tranca()));

        // Act
        trancaService.delete(trancaId);

        // Assert
        verify(trancaRepository).delete(any(Tranca.class));
    }
}
