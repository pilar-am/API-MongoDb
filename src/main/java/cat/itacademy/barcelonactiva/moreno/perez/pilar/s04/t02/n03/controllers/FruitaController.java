package cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.Utils.Mensaje;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.model.Fruita;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.repository.FruitaRepository;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.services.FruitaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fruita")
public class FruitaController {
	
	@Autowired
	FruitaService fruitaService;
	
	@PostMapping("add")
	public ResponseEntity<Fruita> createFruita(@RequestBody Fruita fruita){
		
		try {
			Fruita _fruita = new Fruita(fruita.getNom(), fruita.getQuantitatQuilos());
			System.out.println(_fruita);
			fruitaService.saveFruita(_fruita);
			return new ResponseEntity<>(_fruita, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Fruita>> getAllFruites() {

		try {
			List<Fruita> fruites = fruitaService.llistaFruites();
			if(fruites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Fruita>>(fruites, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruita> getFruitaById(@PathVariable("id") String idFruita){

    	Optional<Fruita> _fruita = fruitaService.getFruita(idFruita);

		if (_fruita.isPresent()) {
			return new ResponseEntity<>(_fruita.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
    @PutMapping("/update/{id}")
    public ResponseEntity<Fruita> updateFruita(@PathVariable("id") String idFruita, @RequestBody Fruita fruita){

    	Optional<Fruita> _fruita = fruitaService.getFruita(idFruita);
    	
    	if (_fruita.isPresent()) {
    		Fruita fruitaActualitzar = _fruita.get();
    		fruitaActualitzar.setNom(fruita.getNom());
    		fruitaActualitzar.setQuantitatQuilos(fruita.getQuantitatQuilos());
    		fruitaService.saveFruita(fruitaActualitzar);
    		return new ResponseEntity<>(fruitaActualitzar, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruita(@PathVariable("id") String idFruita){
       
    	Optional<Fruita> _fruita = fruitaService.getFruita(idFruita);
    	if(_fruita.isEmpty()) {
    		return new ResponseEntity<>(new Mensaje("No existeix l'id"),HttpStatus.INTERNAL_SERVER_ERROR);
    	}else {
    		fruitaService.deleteFruita(idFruita);
    		return new ResponseEntity<>(new Mensaje("Fruita eliminada"), HttpStatus.OK);
    	}
    }

}
