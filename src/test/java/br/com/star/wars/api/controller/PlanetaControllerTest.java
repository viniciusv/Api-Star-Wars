package br.com.star.wars.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.star.wars.api.StarWarsApiApplicationTests;
import br.com.star.wars.api.model.Planeta;
import br.com.star.wars.api.service.PlanetaService;

public class PlanetaControllerTest extends StarWarsApiApplicationTests{
	
	private MockMvc mockMvc;

	@Autowired
	private PlanetaController controller;
	
	@Mock
	private PlanetaService service;

	private Gson gson = new Gson();
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void listarTodosTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planetas")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void buscaPlanetaNomeTest() throws Exception {
		
		 this.mockMvc
	      .perform(MockMvcRequestBuilders.get("/planetas/buscar").param("nome", "Hoth"))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Hoth"));
	}
	
	@Test
	public void buscaPlanetaIdTest() throws Exception {	
		
		MvcResult mvc = this.mockMvc
	      .perform(MockMvcRequestBuilders.get("/planetas/buscar").param("nome", "Hoth"))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andReturn();
		
		String json2 = mvc.getResponse().getContentAsString();
		Planeta p = this.gson.fromJson(json2,Planeta.class);
		
		this.mockMvc
			      .perform(MockMvcRequestBuilders.get("/planetas").param("id", p.getId()))
			      .andExpect(MockMvcResultMatchers.status().isOk());		
	}
	
	@Test
	public void adicionaPlanetaTest() throws Exception {
		
		Planeta p = new Planeta("Terra", "Temperada", "Montanhas");		
		this.mockMvc
	      .perform(MockMvcRequestBuilders.post("/planetas")
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .content(this.gson.toJson(p)))
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.status().isCreated());
		 
	}
	
	@Test
	public void adicionaPlanetaTestErrerNome() throws Exception {
		Planeta p = new Planeta("", "Temperada", "Montanhas");
		
		this.mockMvc
	      .perform(MockMvcRequestBuilders.post("/planetas")
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .content(this.gson.toJson(p)))
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.status().isBadRequest());
		 
	}
	
	@Test
	public void adicionaPlanetaTestErrerClima() throws Exception {
		Planeta p = new Planeta("Terra", "", "Montanhas");
		
		this.mockMvc
	      .perform(MockMvcRequestBuilders.post("/planetas")
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .content(this.gson.toJson(p)))
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.status().isBadRequest());
		 
	}
	
	@Test
	public void adicionaPlanetaTestErrerTerreno() throws Exception {
		Planeta p = new Planeta("Terra", "Temperada", "");
		
		this.mockMvc
	      .perform(MockMvcRequestBuilders.post("/planetas")
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .content(this.gson.toJson(p)))
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void atualizaPlanetaTest() throws Exception {
	
		MvcResult mvc = this.mockMvc.perform(MockMvcRequestBuilders.get("/planetas"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		List<Planeta> list =  this.gson.fromJson(mvc.getResponse().getContentAsString(), new TypeToken<List<Planeta>>(){}.getType());
		Planeta planetaAtualiza = list.get(0);
		planetaAtualiza.setClima("tropical");
		planetaAtualiza.setTerreno("rainforests");
		this.setUp();
		this.mockMvc
	      .perform(MockMvcRequestBuilders.put("/planetas/"+planetaAtualiza.getId())
	    		  .content(this.gson.toJson(planetaAtualiza))
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	public void excluirPlanetaIsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/planetas").param("id", "ua1sS2DV13"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
