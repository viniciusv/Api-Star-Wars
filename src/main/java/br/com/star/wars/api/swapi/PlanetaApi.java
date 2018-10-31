package br.com.star.wars.api.swapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaApi {
	
	private String name;
	private List<String> films;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getFilms() {
		return films;
	}
	
	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	public Integer getCountFilms() {
		if(this.films != null)
			return this.films.size();
		
		return 0;
	}
	
	

}
