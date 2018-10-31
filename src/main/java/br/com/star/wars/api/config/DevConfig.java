package br.com.star.wars.api.config;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import br.com.star.wars.api.model.Planeta;
import br.com.star.wars.api.service.PlanetaService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private PlanetaService planetaService;
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rest =  new RestTemplate();
		rest.getInterceptors().add(this.addRequestHeader("User-agent", "curl/7.59.0"));
		
		return rest;
	}

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		this.instantiateTestDatabase();
		return true;
	}

	private void instantiateTestDatabase() {
		Planeta planeta = new Planeta("Hoth", "Quente", "Plano");
		this.planetaService.adicionaPlaneta(planeta);		
		
		Planeta planeta2 = new Planeta("Yavin IV", "Temperate", "cityscape, mountains");
		this.planetaService.adicionaPlaneta(planeta2);		
	}
	
	private ClientHttpRequestInterceptor addRequestHeader(String name, String value) {

		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().set(name, value);
				return execution.execute(request, body);
			}
		};
	}
}
