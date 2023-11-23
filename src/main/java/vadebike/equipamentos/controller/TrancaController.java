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
import vadebike.equipamentos.dto.IntegraTrancaRedeDTO;
import vadebike.equipamentos.dto.RetiraTrancaRedeDTO;
import vadebike.equipamentos.dto.TrancaDTO;
import vadebike.equipamentos.dto.TrancarDTO;
import vadebike.equipamentos.model.Tranca;
import vadebike.equipamentos.service.TrancaService;

@RestController
@RequestMapping("/tranca")
public class TrancaController {
	
	private final TrancaService trancaService;
	
    @Autowired
    public TrancaController(TrancaService trancaService) {
        this.trancaService = trancaService;
    }
	
    @GetMapping
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(trancaService.findAlltoDTO(), HttpStatusCode.valueOf(200));
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
    
    @PostMapping(path = "/integrarNaRede")
    public ResponseEntity<Object> integrarNaRede(@RequestBody IntegraTrancaRedeDTO dto) {
    	trancaService.integrarNaRede(dto.getIdTotem(), dto.getIdTranca(), dto.getIdFuncionario());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    
    @PostMapping(path = "/retirarDaRede")
    public ResponseEntity<Object> retirarDaRede(@RequestBody RetiraTrancaRedeDTO dto) {
    	trancaService.retirarDaRede(dto.getIdTotem(), dto.getIdTranca(), dto.getIdFuncionario(), dto.getStatusAcaoReparador());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    
    @PostMapping(path = "/{id}/trancar")
    public ResponseEntity<TrancaDTO> trancar (@PathVariable Integer id, @RequestBody TrancarDTO dto){
    	return new ResponseEntity<>(trancaService.trancar(id, dto.getIdBicicleta()).convertToDto(), HttpStatusCode.valueOf(200));
    }
    
    @PostMapping(path = "/{id}/destrancar")
    public ResponseEntity<TrancaDTO> destrancar (@PathVariable Integer id, @RequestBody TrancarDTO dto){
    	return new ResponseEntity<>(trancaService.destrancar(id, dto.getIdBicicleta()).convertToDto(), HttpStatusCode.valueOf(200));
    }
}
