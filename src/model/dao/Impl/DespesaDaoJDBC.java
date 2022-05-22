package model.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DespesaDao;
import model.entities.Categoria;
import model.entities.Despesa;
import model.entities.Usuario;

public class DespesaDaoJDBC implements DespesaDao {

	private Connection conn;
	
	public DespesaDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Despesa despesa) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"INSERT INTO despesa "
							+ "( Descricao, Valor, DataMovimento, FK_Cat_Des, FK_Usu_Des ) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, despesa.getDescricao());
			st.setDouble(2, despesa.getValor());
			st.setDate(3, new Date(despesa.getData().getTime()));
			st.setInt(4, despesa.getCategoria().getId());
			st.setInt(5, despesa.getUsuario().getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					despesa.setId(id);
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
	public void update(Despesa obj) {
		
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE despesa " + "SET Descricao = ?, " + "Valor = ?, " + "DataMovimento = ?, "
					+ "FK_Cat_Des = ?, " + "FK_Usu_Des = ? " + "WHERE Id_Des = ?");

			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getValor());
			st.setDate(3, new Date(obj.getData().getTime()));
			st.setInt(4, obj.getCategoria().getId());
			st.setInt(5, obj.getUsuario().getId());
			st.setInt(6, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("DELETE FROM despesa WHERE Id_Des = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Despesa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM despesa d "
					+"INNER JOIN categoria c ON d.FK_Cat_Des = c.Id_Cat "
					+"INNER JOIN usuario u ON d.FK_Usu_Des = u.Id_Usuario "
					+"WHERE Id_Des = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Despesa obj = new Despesa();
				
				obj.setId(rs.getInt("Id_Des"));
				obj.setDescricao(rs.getString("Descricao"));
				obj.setValor(rs.getDouble("Valor"));
				obj.setData(rs.getDate("DataMovimento"));
				obj.setCategoria(new Categoria(rs.getInt("FK_Cat_Des"), rs.getString("c.Descricao")));
				obj.setUsuario(new Usuario(rs.getInt("u.Id_Usuario"), rs.getString("u.Nome"), rs.getString("u.Profissao")));
				
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
	public List<Despesa> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM despesa d "
					+"INNER JOIN categoria c ON d.FK_Cat_Des = c.Id_Cat "
					+"INNER JOIN usuario u ON d.FK_Usu_Des = u.Id_Usuario");
			
			rs = st.executeQuery();
			
			List<Despesa> list = new ArrayList<>();
			Map<Integer, Categoria> mapCat = new HashMap<>();
			Map<Integer, Usuario> mapUser = new HashMap<>();
			
			while (rs.next()) {
				
				Categoria cat = mapCat.get(rs.getInt("c.Id_Cat"));
				Usuario user = mapUser.get(rs.getInt("u.Id_Usuario"));
				
				
				if (cat == null) {
					cat = instantiateCategoria(rs);
					mapCat.put(rs.getInt("c.Id_Cat"), cat);
				}
				if (user == null) {
					user = instantiateUsuario(rs);
					mapUser.put(rs.getInt("u.Id_Usuario"), user);
				}
				
				
				Despesa obj = instantiateReceita(rs, user, cat);
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

	private Despesa instantiateReceita(ResultSet rs, Usuario user, Categoria cat) throws SQLException {
		Despesa obj = new Despesa();
		obj.setId(rs.getInt("Id_Des"));
		obj.setDescricao(rs.getString("Descricao"));
		obj.setValor(rs.getDouble("Valor"));
		obj.setData(rs.getDate("DataMovimento"));
		obj.setUsuario(user);
		obj.setCategoria(cat);
		
		return obj;
	}
	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		Usuario user = new Usuario();
		user.setId(rs.getInt("u.Id_Usuario"));
		user.setNome(rs.getString("u.Nome"));
		user.setProfissao(rs.getString("u.Profissao"));
		
		return user;
		
	}
	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		
		categoria.setId(rs.getInt("c.Id_Cat"));
		categoria.setDescricao(rs.getString("c.Descricao"));
		
		return categoria;
	}
	
}
