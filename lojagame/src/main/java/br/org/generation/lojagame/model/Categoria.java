package br.org.generation.lojagame.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String classificacao;
	
	
	private Boolean DispoDigital; //atributo de disponibilidade game digital
	
	
	private Boolean DispoFisico; //atributo de disponibilidade game físico

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoria")
	private List<Produtos> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Boolean getDispoDigital() {
		return DispoDigital;
	}

	public void setDispoDigital(Boolean dispoDigital) {
		DispoDigital = dispoDigital;
	}

	public Boolean getDispoFisico() {
		return DispoFisico;
	}

	public void setDispoFisico(Boolean dispoFisico) {
		DispoFisico = dispoFisico;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}
	
}
