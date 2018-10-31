package br.com.star.wars.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsApiApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class StarWarsApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}