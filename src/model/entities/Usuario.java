package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String profissao;
	private Double receita;
	private Double despesa;
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public Usuario(int id, String nome, String profissao, Double receita, Double despesa) {
		this.id = id;
		this.nome = nome;
		this.profissao = profissao;
		this.receita = receita;
		this.despesa = despesa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	public Double getReceita() {
		return receita;
	}


	public void setReceita(Double receita) {
		this.receita = receita;
	}


	public Double getDespesa() {
		return despesa;
	}


	public void setDespesa(Double despesa) {
		this.despesa = despesa;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", profissao=" + profissao + ", receita=" + receita
				+ ", despesa=" + despesa + "]";
	}
	
	// Calcula o valor da receita lançada
	public void calcularReceita(Receita receita) {
		this.receita += receita.getValor();
	}

	// Calcula o valor da despesa lançada
	public void calcularDespesa(Despesa despesa) {
		this.despesa += despesa.getValor();
	}
	
}
