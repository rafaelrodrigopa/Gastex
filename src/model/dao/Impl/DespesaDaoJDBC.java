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
import model.dao.DespesaDao;
import model.entities.Despesa;

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
					"INSERT INTO receita "
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
