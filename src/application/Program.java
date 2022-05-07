package application;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
	
		System.out.println("\n=== TEST 1: Usuario insert =====");
		Usuario usuario = new Usuario();
		
		usuario.setNome("Rodrigo");
		usuario.setProfissao("Analista");
		
		usuarioDao.insert(usuario);
		
		System.out.println("Inserted! New id = " + usuario.getId());
		
	}

}
