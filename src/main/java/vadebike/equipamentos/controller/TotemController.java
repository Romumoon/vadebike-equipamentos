package vadebike.equipamentos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vadebike.equipamentos.dto.BicicletaDTO;
import vadebike.equipamentos.dto.TotemDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.model.Totem;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TotemService;

@RestController
@RequestMapping("/totem")
public class TotemController {
	
	private final TotemService totemService;

    @Autowired
    public TotemController(TotemService totemService) {
        this.totemService = totemService;
    }
	
    @GetMapping
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(totemService.findAlltoDTO(), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping
    public ResponseEntity<TotemDTO> create(@RequestBody TotemDTO dto) {
    	Totem newEntity = dto.convertToEntity(null);
    	
        return new ResponseEntity<>(totemService.create(newEntity), HttpStatusCode.valueOf(200));
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<TotemDTO> update(@PathVariable Integer id, @RequestBody TotemDTO dto) {
    	Totem updatedEntity = dto.convertToEntity(id);
    	
    	return new ResponseEntity<>(totemService.update(updatedEntity), HttpStatusCode.valueOf(200));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
    	totemService.delete(id);
    	return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
    
    @GetMapping(path = "/{id}/bicicletas")
    public ResponseEntity<List<BicicletaDTO>> listBicicletas(@PathVariable Integer id) {
    	
    	return new ResponseEntity<>(totemService.listBicicletas(id).stream().map(Bicicleta::convertToDto).collect(Collectors.toList())
    			, HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(path = "/{id}/trancas")
    public ResponseEntity<List<TrancaDTO>> listTrancas(@PathVariable Integer id) {
    	
    	return new ResponseEntity<>(totemService.listTrancas(id).stream().map(Tranca::convertToDto).collect(Collectors.toList())
    			, HttpStatusCode.valueOf(200));
    }
}
