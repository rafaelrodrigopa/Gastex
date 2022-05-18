package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String profissao;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public Usuario(int id, String nome, String profissao) {
		this.id = id;
		this.nome = nome;
		this.profissao = profissao;
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
		return "Usuario [id=" + id + ", nome=" + nome + ", profissao=" + profissao + "]";
	}
	
	
}
