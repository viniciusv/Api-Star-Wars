package br.com.star.wars.api.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.star.wars.api.StarWarsApiApplicationTests;

public class ApiStarWarsServiceImplTest extends StarWarsApiApplicationTests{
	
	@Mock
	private ApiStarWarsService service;
	
	@Before
	public void setUp() {}
	
	@Test
	public void returnQuantidadeAparicoesDoPlanetaEmFilmesTest() {
		when(service.returnQuantidadeAparicoesDoPlanetaEmFilmes("Hoth")).thenReturn(2);
		  
		 Integer result  = this.service.returnQuantidadeAparicoesDoPlanetaEmFilmes("Hoth");
		 Assert.assertNotEquals(new Integer(0), result);
	}
	
	@Test
	public void returnQuantidadeAparicoesDoPlanetaEmFilmesTestErroNomeNaoExiste() {
		when(service.returnQuantidadeAparicoesDoPlanetaEmFilmes("A1")).thenReturn(0);
		
		 Integer result  = this.service.returnQuantidadeAparicoesDoPlanetaEmFilmes("A1");
		 Assert.assertEquals(new Integer(0), result);
	}

	
}
