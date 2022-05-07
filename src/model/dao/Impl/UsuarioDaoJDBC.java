package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{
	
	private Connection conn;

	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Usuario usuario) {
	
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO usuario "
					+ "(Profissao, Nome) VALUES "
					+ "(?,?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, usuario.getProfissao());
			st.setString(2, usuario.getNome());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					usuario.setId(id);
				}
			}else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
