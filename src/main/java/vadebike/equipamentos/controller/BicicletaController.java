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
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.service.BicicletaService;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {
	
    @Autowired
    private BicicletaService bicicletaService;
    
    @GetMapping
    public ResponseEntity<Bicicleta> listAll() {
        return new ResponseEntity(bicicletaService.findAll(), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Bicicleta> findById(@PathVariable Integer id) {
        return new ResponseEntity(bicicletaService.findById(id), HttpStatusCode.valueOf(200));
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<Bicicleta> update(@PathVariable Integer id, @RequestBody BicicletaDTO dto) {
    	Bicicleta updatedEntity = dto.convertToEntity(id);
    	
    	return new ResponseEntity(bicicletaService.update(updatedEntity), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping
    public ResponseEntity<Bicicleta> create(@RequestBody BicicletaDTO dto) {
    	Bicicleta newEntity = dto.convertToEntity(null);
    	
        return new ResponseEntity(bicicletaService.create(newEntity), HttpStatusCode.valueOf(200));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
    	bicicletaService.delete(id);
    	return new ResponseEntity(HttpStatusCode.valueOf(204));
    }
    
}
