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
import vadebike.equipamentos.dto.IntegraBicicletaRedeDTO;
import vadebike.equipamentos.dto.RetiraBicicletaRedeDTO;
import vadebike.equipamentos.model.Bicicleta;
import vadebike.equipamentos.service.BicicletaService;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {
	
    private final BicicletaService bicicletaService;

    @Autowired
    public BicicletaController(BicicletaService bicicletaService) {
        this.bicicletaService = bicicletaService;
    }
    
    @GetMapping
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(bicicletaService.findAlltoDTO(), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<BicicletaDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(bicicletaService.findById(id), HttpStatusCode.valueOf(200));
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<BicicletaDTO> update(@PathVariable Integer id, @RequestBody BicicletaDTO dto) {
    	Bicicleta updatedEntity = dto.convertToEntity(id);
    	
    	return new ResponseEntity<>(bicicletaService.update(updatedEntity), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping(path = "/{id}/status/{acao}")
    public ResponseEntity<BicicletaDTO> updateStatus(@PathVariable Integer id, @PathVariable String acao) {
    	
    	return new ResponseEntity<>(bicicletaService.updateStatus(id, acao), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping
    public ResponseEntity<BicicletaDTO> create(@RequestBody BicicletaDTO dto) {
    	Bicicleta newEntity = dto.convertToEntity(null);
    	
        return new ResponseEntity<>(bicicletaService.create(newEntity), HttpStatusCode.valueOf(200));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
    	bicicletaService.delete(id);
    	return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
    
    @PostMapping(path = "/integrarNaRede")
    public ResponseEntity<Object> integrarNaRede(@RequestBody IntegraBicicletaRedeDTO dto) {
    	bicicletaService.integrarNaRede(dto.getIdBicicleta(), dto.getIdTranca(), dto.getIdFuncionario());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    
    @PostMapping(path = "/retirarDaRede")
    public ResponseEntity<Object> retirarDaRede(@RequestBody RetiraBicicletaRedeDTO dto) {
    	bicicletaService.retirarDaRede(dto.getIdBicicleta(), dto.getIdTranca(), dto.getIdFuncionario(), dto.getStatusAcaoReparador());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
