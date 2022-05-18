package model.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ReceitaDao;
import model.entities.Categoria;
import model.entities.Receita;
import model.entities.Usuario;

public class ReceitaDaoJDBC implements ReceitaDao {

	private Connection conn;

	public ReceitaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Receita receita) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"INSERT INTO receita "
							+ "( Descricao, Valor, DataMovimento, FK_Cat_Rec, FK_Usu_Rec ) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, receita.getDescricao());
			st.setDouble(2, receita.getValor());
			st.setDate(3, new Date(receita.getData().getTime()));
			st.setInt(4, receita.getCategoria().getId());
			st.setInt(5, receita.getUsuario().getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					receita.setId(id);
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
	public void update(Receita obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE receita " + "SET Descricao = ?, " + "Valor = ?, " + "DataMovimento = ?, "
					+ "FK_Cat_Rec = ?, " + "FK_Usu_Rec = ? " + "WHERE Id_Rec = ?");

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

			st = conn.prepareStatement("DELETE FROM receita WHERE Id_Rec = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Receita findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM receita r "
					+"INNER JOIN categoria c ON r.FK_Cat_Rec = c.Id_Cat "
					+"INNER JOIN usuario u ON r.FK_Usu_Rec = u.Id_Usuario "
					+"WHERE Id_Rec = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Receita obj = new Receita();
				
				obj.setId(rs.getInt("Id_Rec"));
				obj.setDescricao(rs.getString("Descricao"));
				obj.setValor(rs.getDouble("Valor"));
				obj.setData(rs.getDate("DataMovimento"));
				obj.setCategoria(new Categoria(rs.getInt("FK_Cat_Rec"), rs.getString("c.Descricao")));
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
	public List<Receita> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
