package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
//	
//		System.out.println("\n=== TEST 1: Usuario insert =====");
//		Usuario usuario = new Usuario();
//		
//		usuario.setNome("Rodrigo");
//		usuario.setProfissao("Analista");
//		
//		usuarioDao.insert(usuario);
//		
//		System.out.println("Inserted! New id = " + usuario.getId());
//		
//		
//		System.out.println("\n=== TEST 2: Usuario findall =====");
//		List<Usuario> list = usuarioDao.findAll();
//		
//		for(Usuario user:list) {
//			System.out.println(user);
//		}
//		
//		
//		System.out.println("\n=== TEST 3: Usuario findById =====");
//		Usuario obj = usuarioDao.findById(1);
//		
//		System.out.println(obj);
//		
//		
		System.out.println("\n=== TEST 4: Usuario update (byName) =====");
		Usuario usuarioUpdate = usuarioDao.findById(12);
		usuarioUpdate.setNome("Cecile");
		usuarioDao.update(usuarioUpdate);
		System.out.println("Update completed");
		
		
	}

}
