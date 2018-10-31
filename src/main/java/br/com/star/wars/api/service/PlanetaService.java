package br.com.star.wars.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.star.wars.api.model.Planeta;

@Component
public interface PlanetaService {

	Planeta buscaPlanetaId(String id);	

	List<Planeta> listarTodosPlanetas();

	Planeta buscaPlanetaNome(String nomePlaneta);

	Planeta adicionaPlaneta(Planeta planeta);

	Planeta atualizaPlaneta(Planeta planeta);

	void excluirPlaneta(String id);



}
