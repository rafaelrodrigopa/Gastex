package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DespesaDao;
import model.dao.ReceitaDao;
import model.dao.UsuarioDao;
import model.entities.Despesa;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		
		//Testes implementação Receita
		ReceitaDao receitaDao = DaoFactory.createReceitaDao();
		
		//Testes implementação Despesa
		DespesaDao despesaDao = DaoFactory.createDespesaDao();
//		System.out.println("\n Teste 01: Receita insert");
//		Despesa despesa = new Despesa(null, "Compra de Arroz", 18.56, new Date(), new Categoria(1, "Alimentos") ,new Usuario(1, "Rafael", "Analista"));
//		
//		despesaDao.insert(despesa);
//		
//		System.out.println("\n Teste 02: Receita update");
//		despesa.setDescricao("Compra de café");
//		despesa.setValor(6.59);
//		
//		despesaDao.update(despesa);
		
//		System.out.println("\n Teste 03: Receita delete");
//		despesaDao.deleteById(11);
//		despesaDao.deleteById(12);
		
		System.out.println("\n Teste 04: Receita findById");
		Despesa despesa = despesaDao.findById(10);
		System.out.println(despesa);
		
		System.out.println("\n Teste 05: Receita findByAll");
		List<Despesa> list = despesaDao.findAll();
		
		for(Despesa despesa2 : list) {
			System.out.println(despesa2);
		}
		
		
//		//Criação de usuaio pra teste
//		Usuario usuario = usuarioDao.findById(1);
//		
//		System.out.println("\n Teste 01: Receita insert");
//		Receita receita = new Receita(null, "Compra de Arroz", 18.56, new Date(), new Categoria(1, "Alimentos") ,usuario);
//		
//		receitaDao.insert(receita);
//		
//		
//		System.out.println("\n Teste 02: Receita update");
//		receita.setId(5);
//		receita.setDescricao("Bico");
//		receita.setValor(300.00);
//		receita.setCategoria(new Categoria(7, "Pagamentos"));
//		
//		receitaDao.update(receita);
//		System.out.println("Update completed");
//		
//		
//		System.out.println("\n Teste 03: Receita deleteById");
//		receitaDao.deleteById(11);
//		
//		System.out.println("Delete completed");
//		
//		System.out.println("\n Teste 04: Receita findById");
//		Receita receita = receitaDao.findById(1);
//		System.out.println(receita);
//		System.out.println(receita.getCategoria());
//		System.out.println(receita.getUsuario());
//		
//		System.out.println("\n Teste 05: Receita findAll");
//		List<Receita> receitas = receitaDao.findAll();
//		
//		for(Receita rec : receitas) {
//			System.out.println(rec);
//		}
		
		
		
		
		//Testes Implementação Usuario
//		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
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
//		System.out.println("\n=== TEST 4: Usuario update (byName) =====");
//		Usuario usuarioUpdate = usuarioDao.findById(12);
//		usuarioUpdate.setNome("Cecile");
//		usuarioDao.update(usuarioUpdate);
//		System.out.println("Update completed");
		
//		System.out.println("\n=== TEST 5: Usuario deleteById =====");
//		usuarioDao.deleteById(3);
//		System.out.println("Delete completed");
		
		
	}

}
