package br.com.star.wars.api.swapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwApi {
	
	private Integer count;
	//private PlanetaApi[] results;
	
	private List<PlanetaApi> results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public List<PlanetaApi> getResults() {
		return results;
	}
	
	public void setResults(List<PlanetaApi> results) {
		this.results = results;
	}
	
}
