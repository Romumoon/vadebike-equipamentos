package vadebike.equipamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.exception.BusinessException;
import vadebike.equipamentos.exception.NoContentException;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.repository.ITotemRepository;
import vadebike.equipamentos.service.TotemService;

@ExtendWith(MockitoExtension.class)
class TotemServiceTest {

	@Mock
	private ITotemRepository totemRepository;

	@InjectMocks
	private TotemService totemService;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(totemService, "baseRepository", totemRepository);
	}

    @Test
    void testListTrancas() {
        // Arrange
        Integer totemId = 1;
        Tranca tranca1 = new Tranca();
        tranca1.setId(1);
        Tranca tranca2 = new Tranca();
        tranca2.setId(2);
        Totem totem = new Totem();
        totem.setTrancasList(Arrays.asList(tranca1, tranca2));
        when(totemRepository.findById(totemId)).thenReturn(Optional.of(totem));

        // Act
        List<TrancaDTO> trancaDTOList = totemService.listTrancas(totemId);

        // Assert
        assertEquals(2, trancaDTOList.size());
        assertEquals(1, trancaDTOList.get(0).getId());
        assertEquals(2, trancaDTOList.get(1).getId());
        // Add more assertions if needed
    }

    @Test
    void testListBicicletas() {
        // Arrange
        Integer totemId = 1;
        Bicicleta bicicleta1 = new Bicicleta();
        bicicleta1.setId(1);
        Bicicleta bicicleta2 = new Bicicleta();
        bicicleta2.setId(2);
        Totem totem = new Totem();
        totem.setBicicletasList(Arrays.asList(bicicleta1, bicicleta2));
        when(totemRepository.findById(totemId)).thenReturn(Optional.of(totem));

        // Act
        List<BicicletaDTO> bicicletaDTOList = totemService.listBicicletas(totemId);

        // Assert
        assertEquals(2, bicicletaDTOList.size());
        assertEquals(1, bicicletaDTOList.get(0).getId());
        assertEquals(2, bicicletaDTOList.get(1).getId());
        // Add more assertions if needed
    }

	@Test
	void testDeleteTotemWithNoContent() {
		// Arrange
		Integer totemId = 1;
		when(totemRepository.findById(totemId)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(NoContentException.class, () -> totemService.delete(totemId));
	}

	@Test
	void testDeleteTotemWithBicicletasOrTrancas() {
		// Arrange
		Integer totemId = 1;
		Totem totem = new Totem();
		List<Bicicleta> bicicletasList = new ArrayList<>();
		bicicletasList.add(createRandomBicicleta()); // Add some dummy data
		List<Tranca> trancasList = new ArrayList<>();
		trancasList.add(createRandomTranca()); // Add some dummy data
		totem.setBicicletasList(bicicletasList);
		totem.setTrancasList(trancasList);
		when(totemRepository.findById(totemId)).thenReturn(Optional.of(totem));

		// Act & Assert
		assertThrows(BusinessException.class, () -> totemService.delete(totemId));
	}

	@Test
	void testDeleteTotemSuccess() {
		// Arrange
		Integer totemId = 1;
		Totem totem = new Totem();
        
		when(totemRepository.findById(totemId)).thenReturn(Optional.of(totem));

		// Act
		totemService.delete(totemId);

		// Assert
		verify(totemRepository).delete(totem);
	}

	Bicicleta createRandomBicicleta() {
		Random random = new Random();
		Integer numeroAleatorio = random.nextInt(11); // Gera um número aleatório de 0 a 10

		Bicicleta bicicleta = new Bicicleta();
		bicicleta.setId(numeroAleatorio);
		
		return bicicleta;
	}
	
	Tranca createRandomTranca() {
		Random random = new Random();
		Integer numeroAleatorio = random.nextInt(11); // Gera um número aleatório de 0 a 10

		Tranca tranca = new Tranca();
		tranca.setId(numeroAleatorio);
		
		return tranca;
	}
}
