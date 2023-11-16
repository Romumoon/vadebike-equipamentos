package vadebike.equipamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.enums.StatusBicicleta;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.IBaseRepository;
import vadebike.equipamentos.service.BicicletaService;

@ExtendWith(MockitoExtension.class)
public class BicicletaServiceTest {

    @Mock
    private IBaseRepository baseRepository;
    
    @Autowired
    private EntityManager em;

    @InjectMocks
    private BicicletaService bicicletaService;

    @Test
    public void testCreate() {
        // Mock the random number generator to return a predictable sequence of numbers
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(1, 2, 3, 4);

        // Create a new bicicleta entity
        Bicicleta newEntity = new Bicicleta();

        // Call the create method
        BicicletaDTO bicicletaDTO = bicicletaService.create(newEntity);

        // Verify that the bicicletaDTO is not null
        assertNotNull(bicicletaDTO);

        // Verify that the bicicletaDTO has the correct status
        assertEquals(StatusBicicleta.NOVA.getStatus(), bicicletaDTO.getStatus());

        // Verify that the bicicletaDTO has a unique numero
        assertEquals(4, bicicletaDTO.getNumero());

        // Verify that the bicicleta entity was saved to the repository
        Mockito.verify(baseRepository, times(1)).save(newEntity);
    }

    @Test
    public void testUpdate() {
        // Create an existing bicicleta entity
        Bicicleta updated = new Bicicleta();
        updated.setId(1);
        updated.setStatus(StatusBicicleta.NOVA.getStatus());
        updated.setNumero(1);

        // Call the update method
        BicicletaDTO bicicletaDTO = bicicletaService.update(updated);

        // Verify that the bicicletaDTO is not null
        assertNotNull(bicicletaDTO);

        // Verify that the bicicletaDTO has the same status and numero as the updated entity
        assertEquals(StatusBicicleta.NOVA.getStatus(), bicicletaDTO.getStatus());
        assertEquals(1, bicicletaDTO.getNumero());

        // Verify that the bicicleta entity was saved to the repository
        Mockito.verify(baseRepository, times(1)).save(updated);
    }

    @Test
    public void testUpdateWithInvalidStatusOrNumero() {
        // Create an existing bicicleta entity
        Bicicleta updated = new Bicicleta();
        updated.setId(1);
        updated.setStatus(StatusBicicleta.APOSENTADA.getStatus());
        updated.setNumero(2);

        // Call the update method
        assertThrows(BusinessException.class, () -> bicicletaService.update(updated));
    }

    @Test
    public void testDelete() {
        // Create a bicicleta entity to be deleted
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(1);
        bicicleta.setStatus(StatusBicicleta.APOSENTADA.getStatus());
        bicicleta.setTranca(null);

        // Mock the findById method to return the bicicleta entity
        Mockito.when(baseRepository.findById(1)).thenReturn(Optional.of(bicicleta));

        // Call the delete method
        bicicletaService.delete(1);

        // Verify that the bicicleta entity was deleted from the repository
        Mockito.verify(baseRepository, times(1)).delete(bicicleta);
    }

    @Test
    public void testDeleteWithInvalidStatusOrTranca() {
        // Create a bicicleta entity that cannot be deleted
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(1);
        bicicleta.setStatus(StatusBicicleta.NOVA.getStatus());
        bicicleta.setTranca(new Tranca());
        // Mock the findById method to return the bicicleta entity
        Mockito.when(baseRepository.findById(1)).thenReturn(Optional.of(bicicleta));

        // Call the delete method
        assertThrows(BusinessException.class, () -> bicicletaService.delete(1));
    }
}
