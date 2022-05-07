package model.dao;

import db.DB;
import model.dao.Impl.UsuarioDaoJDBC;

public class DaoFactory {

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
