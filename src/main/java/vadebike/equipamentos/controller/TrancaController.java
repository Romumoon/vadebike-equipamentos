package vadebike.equipamentos.controller;

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
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TrancaService;

@RestController
@RequestMapping("/tranca")
public class TrancaController {
	
	@Autowired
	TrancaService trancaService;
	
    @GetMapping
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(trancaService.findAll(), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(path = "/{id}/bicicleta")
    public ResponseEntity<BicicletaDTO> findBicicletaByTrancaId(@PathVariable Integer id) {
        return new ResponseEntity<>(trancaService.findBicicletaByTrancaId(id), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping
    public ResponseEntity<TrancaDTO> create(@RequestBody TrancaDTO dto) {
    	Tranca newEntity = dto.convertToEntity(null);
    	
        return new ResponseEntity<>(trancaService.create(newEntity), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<TrancaDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(trancaService.findById(id), HttpStatusCode.valueOf(200));
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<TrancaDTO> update(@PathVariable Integer id, @RequestBody TrancaDTO dto) {
    	Tranca updatedEntity = dto.convertToEntity(id);
    	
    	return new ResponseEntity<>(trancaService.update(updatedEntity), HttpStatusCode.valueOf(200));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
    	trancaService.delete(id);
    	return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
