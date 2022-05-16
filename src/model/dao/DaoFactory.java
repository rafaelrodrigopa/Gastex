package model.dao;

import db.DB;
import model.dao.Impl.CategoriaDaoJDBC;
import model.dao.Impl.DespesaDaoJDBC;
import model.dao.Impl.ReceitaDaoJDBC;
import model.dao.Impl.UsuarioDaoJDBC;

public class DaoFactory {

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static ReceitaDao createReceitaDao() {
		return new ReceitaDaoJDBC(DB.getConnection());
	}
	
	public static DespesaDao createDespesaDao() {
		return new DespesaDaoJDBC(DB.getConnection());
	}
	
	public static CategoriaDao createCategoriaDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}
}
