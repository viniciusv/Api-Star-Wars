package br.com.star.wars.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.star.wars.api.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	Optional<Planeta> findByNome(String nomePlaneta);

}
