package br.com.star.wars.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.star.wars.api.model.Planeta;
import br.com.star.wars.api.service.PlanetaService;




@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {
	
	@Autowired
	private PlanetaService planetaService;
	
	@GetMapping
	public ResponseEntity<List<Planeta>> listarTodos() {
		List<Planeta> list = this.planetaService.listarTodosPlanetas();
		return ResponseEntity.ok().body(list);
	}	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Planeta> buscaPlanetaId(@PathVariable String id) {
		Planeta planeta = this.planetaService.buscaPlanetaId(id);		
		return ResponseEntity.ok().body(planeta);
	}
	
	@GetMapping(value="/buscar")
	public ResponseEntity<Planeta> buscaPlanetaNome(
			@RequestParam(value="nome", required=true) String nomePlaneta) {
		Planeta planeta = this.planetaService.buscaPlanetaNome(nomePlaneta);	
		return ResponseEntity.ok().body(planeta);
	}
	
	@PostMapping
	public ResponseEntity<Void> adicionaPlaneta(@Valid @RequestBody Planeta planeta) {
		planeta = this.planetaService.adicionaPlaneta(planeta);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizaPlaneta(@Valid @RequestBody Planeta planeta, @PathVariable String id) {
		planeta.setId(id);
		planeta = this.planetaService.atualizaPlaneta(planeta);		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluirPlaneta(@PathVariable String id) {
		this.planetaService.excluirPlaneta(id);		
		return ResponseEntity.noContent().build();
	}
}
