package br.com.star.wars.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planeta implements Serializable{

	private static final long serialVersionUID = 6303357232886734178L;

	@Id
	private String id;
	
	@Indexed(unique=true)
	@NotEmpty(message="Nome é obrigatório")
	@Length(min=2, max=80, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String nome; 
	
	@NotEmpty(message="Clima é obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres")
	private String clima;
	
	@NotEmpty(message="Terreno é obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres")
	private String terreno;
	
	@Transient
	private Integer countAparicoesFilmes;
	
	public Planeta() {}

	public Planeta(String id, String nome, String clima, String terreno) {
		super();
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}
	
	public Planeta(String nome, String clima, String terreno) {
		super();
		this.id = null;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getCountAparicoesFilmes() {
		return countAparicoesFilmes;
	}

	public void setCountAparicoesFilmes(Integer countAparicoesFilmes) {
		this.countAparicoesFilmes = countAparicoesFilmes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
