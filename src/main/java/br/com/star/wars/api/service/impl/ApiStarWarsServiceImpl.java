package br.com.star.wars.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.star.wars.api.exceptions.SwapiConnectionErrorException;
import br.com.star.wars.api.service.ApiStarWarsService;
import br.com.star.wars.api.swapi.SwApi;

@Service
public class ApiStarWarsServiceImpl implements ApiStarWarsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${url.base.sw.api}")
	private String baseUrl;
	
	public Integer returnQuantidadeAparicoesDoPlanetaEmFilmes(String nome) throws SwapiConnectionErrorException{
		try {
			
			SwApi swApi = restTemplate.getForObject(baseUrl+nome, SwApi.class);
			if(swApi.getResults()!=null)
				return swApi.getResults().get(0).getCountFilms();		
			
				
		}catch(Exception e ) {
			throw new SwapiConnectionErrorException("Erro na conexão com api de SWAPI", e.getCause()); 
		}
		return 0;		
	}
	
}
