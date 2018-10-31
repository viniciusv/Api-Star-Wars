package br.com.star.wars.api.service;

import org.springframework.stereotype.Component;

@Component
public interface ApiStarWarsService {

	Integer returnQuantidadeAparicoesDoPlanetaEmFilmes(String nome);

}
