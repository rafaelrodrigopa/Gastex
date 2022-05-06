package model.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.Receita;

public interface ReceitaDao {

	void insert(Receita obj);
	void update(Receita obj);
	void deleteById(Integer id);
	Receita findById(Integer id);
	List<Receita> findAll();
	List<Receita> findByCategory(Categoria categoria);
	
}
