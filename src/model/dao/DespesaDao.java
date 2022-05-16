package model.dao;

import java.util.List;

import model.entities.Despesa;

public interface DespesaDao {

	void insert(Despesa obj);
	void update(Despesa obj);
	void deleteById(Integer id);
	Despesa findById(Integer id);
	List<Despesa> findAll();
}
