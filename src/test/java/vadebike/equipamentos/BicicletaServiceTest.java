package vadebike.equipamentos;

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
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.IBicicletaRepository;
import vadebike.equipamentos.service.BicicletaService;

@ExtendWith(MockitoExtension.class)
public class BicicletaServiceTest {

    @Mock
    private IBicicletaRepository bicicletaRepository;

    @InjectMocks
    private BicicletaService bicicletaService;
    
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(bicicletaService, "baseRepository", bicicletaRepository);
	}
    
    @Test
    public void testCreateBicicleta() {
        // Arrange
        Bicicleta newBicicleta = new Bicicleta();
        when(bicicletaRepository.findByNumero(anyInt())).thenReturn(null);
        when(bicicletaRepository.save(newBicicleta)).thenReturn(newBicicleta);

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.create(newBicicleta);

        // Assert
        // Implement your assertions here
    }

    @Test
    public void testUpdateBicicleta() {
        // Arrange
        Bicicleta updatedBicicleta = new Bicicleta();
        updatedBicicleta.setId(1);
        Bicicleta originalBicicleta = new Bicicleta();
        originalBicicleta.setId(1);
        originalBicicleta.setStatus(StatusBicicleta.NOVA.getStatus());
        originalBicicleta.setNumero(123);

        when(bicicletaRepository.findById(updatedBicicleta.getId())).thenReturn(Optional.of(originalBicicleta));
        when(bicicletaRepository.save(updatedBicicleta)).thenReturn(updatedBicicleta);

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.update(updatedBicicleta);

        // Assert
        // Implement your assertions here
    }

    @Test
    public void testUpdateBicicletaException() {
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
    public void testDeleteBicicletaWithNoContent() {
        // Arrange
        Integer bicicletaId = 1;
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoContentException.class, () -> bicicletaService.delete(bicicletaId));
    }

    @Test
    public void testDeleteBicicletaWithTrancaOrNotAposentada() {
        // Arrange
        Integer bicicletaId = 1;
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setTranca(new Tranca()); // Assuming Tranca class exists
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));

        // Act & Assert
        assertThrows(BusinessException.class, () -> bicicletaService.delete(bicicletaId));
    }

    @Test
    public void testDeleteBicicletaSuccess() {
        // Arrange
        Integer bicicletaId = 1;
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(new Bicicleta()));

        // Act
        bicicletaService.delete(bicicletaId);

        // Assert
        verify(bicicletaRepository).delete(any(Bicicleta.class));
    }

    @Test
    public void testUpdateStatusBicicleta() {
        // Arrange
        Integer bicicletaId = 1;
        Bicicleta bicicleta = new Bicicleta();
        when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));

        // Act
        BicicletaDTO bicicletaDTO = bicicletaService.updateStatus(bicicletaId, "aposentada");

        // Assert
        // Implement your assertions here
    }
}
