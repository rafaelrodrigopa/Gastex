package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Despesa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Double valor;
	private Date data;
	
	private Categoria categoria;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Despesa() {
		// TODO Auto-generated constructor stub
	}

	public Despesa(Integer id, String descricao, Double valor, Date data, Categoria categoria, Usuario usuario) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.setCategoria(categoria);
		this.usuario=usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		Despesa other = (Despesa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + ", Categoria=" + categoria + ", Usuario=" + usuario + "]";
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
