package model.dao.Impl;

import java.sql.Connection;
import java.util.List;

import model.dao.CategoriaDao;
import model.entities.Categoria;

public class CategoriaDaoJDBC implements CategoriaDao {

	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
