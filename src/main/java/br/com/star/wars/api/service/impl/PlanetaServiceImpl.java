package br.com.star.wars.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.star.wars.api.exceptions.ObjectNotFoundException;
import br.com.star.wars.api.model.Planeta;
import br.com.star.wars.api.repository.PlanetaRepository;
import br.com.star.wars.api.service.ApiStarWarsService;
import br.com.star.wars.api.service.PlanetaService;
import br.com.star.wars.api.utils.Utils;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private ApiStarWarsService apiService;
	
	@Override
	public Planeta buscaPlanetaId(String id) {
		Optional<Planeta> planeta = this.planetaRepository.findById(id);
		this.quantidadeAparicoes(planeta.get());
		return planeta.orElseThrow(() -> new ObjectNotFoundException("Planeta não encontrado! Id: " + id + ", Tipo: " + Planeta.class.getName()));
	}

	@Override
	public List<Planeta> listarTodosPlanetas() {
		List<Planeta> listPlanetas = this.planetaRepository.findAll();
		listPlanetas.stream().forEach(p -> this.quantidadeAparicoes(p));
		return listPlanetas;
	}

	@Override
	public Planeta buscaPlanetaNome(String nomePlaneta) {
		String nomePlanetaDecode = Utils.decodeParam(nomePlaneta);
		Optional<Planeta> planeta = this.planetaRepository.findByNome(nomePlanetaDecode);
		this.quantidadeAparicoes(planeta.get());
		return planeta.orElseThrow(() -> new ObjectNotFoundException("Planeta não encontrado! Nome: " + nomePlaneta + ", Tipo: " + Planeta.class.getName()));
	}

	@Override
	public Planeta adicionaPlaneta(Planeta planeta) {
		planeta.setId(null);
		return this.planetaRepository.save(planeta);
	}

	@Override
	public Planeta atualizaPlaneta(Planeta planeta) {
		Planeta newPlaneta = this.buscaPlanetaId(planeta.getId());
		this.update(newPlaneta, planeta);
		return this.planetaRepository.save(newPlaneta);
	}

	@Override
	public void excluirPlaneta(String id) {
		Planeta planeta = this.buscaPlanetaId(id);
		this.planetaRepository.delete(planeta);	
	}
	
	private void update(Planeta newPlaneta, Planeta planeta) {
		newPlaneta.setClima(planeta.getClima());
		newPlaneta.setNome(planeta.getNome());
		newPlaneta.setTerreno(planeta.getTerreno());
	}

	private void quantidadeAparicoes(Planeta planeta) {
		Integer quantidadeAparicoes = this.apiService.returnQuantidadeAparicoesDoPlanetaEmFilmes(planeta.getNome());
		planeta.setCountAparicoesFilmes(quantidadeAparicoes);
	}

}
