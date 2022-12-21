package cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.model.Fruita;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s04.t02.n03.repository.FruitaRepository;


@Service
public class FruitaService {

	@Autowired
	FruitaRepository fruitaRepository;
	
	//Por defecto el repositorio al extender de MongoRepository trae el metodo por defecto
    public List<Fruita> llistaFruites(){
        return  fruitaRepository.findAll();
    }
    
    public Optional<Fruita> getFruita(String idFruita){
        return  fruitaRepository.findById(idFruita);
    }

    public void saveFruita(Fruita fruita) {
    	fruitaRepository.save(fruita);
    }
    
    public void deleteFruita(String idFruita) {
    	fruitaRepository.deleteById(idFruita);
    }
}
