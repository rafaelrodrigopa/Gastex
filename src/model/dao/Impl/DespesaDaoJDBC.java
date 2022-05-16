package model.dao.Impl;

import java.sql.Connection;
import java.util.List;

import model.dao.DespesaDao;
import model.entities.Despesa;

public class DespesaDaoJDBC implements DespesaDao {

	private Connection conn;
	
	public DespesaDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Despesa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Despesa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Despesa findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Despesa> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
