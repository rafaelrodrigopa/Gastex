package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

	private Connection conn;

	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Usuario usuario) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO usuario " + "( Nome, Profissao ) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, usuario.getProfissao());
			st.setString(2, usuario.getNome());
			
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					usuario.setId(id);
				}
			} else {
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
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE usuario "
					+ "SET Nome = ? "
					+ "WHERE Id_Usuario = ?");
			
			st.setString(1, usuario.getNome());
			st.setInt(2, usuario.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM usuario WHERE Id_Usuario = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		

	}

	@Override
	public Usuario findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM usuario WHERE Id_Usuario = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setId(rs.getInt("Id_Usuario"));
				obj.setNome(rs.getString("Nome"));
				obj.setProfissao(rs.getString("Profissao"));
				return obj;
			}
			return null;
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Usuario> findAll() {
	
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM usuario ORDER BY Nome");
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			
			while (rs.next()) {
				Usuario obj = new Usuario();
				obj.setId(rs.getInt("Id_Usuario"));
				obj.setNome(rs.getString("Nome"));
				obj.setProfissao(rs.getString("Profissao"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
