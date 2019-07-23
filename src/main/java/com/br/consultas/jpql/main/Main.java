package com.br.consultas.jpql.main;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.br.consultas.jpql.main.dto.UsuarioDTO;
import com.br.consultas.jpql.main.dto.model.Configuracao;
import com.br.consultas.jpql.main.dto.model.Dominio;
import com.br.consultas.jpql.main.dto.model.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CONFIG-DATABASE-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// paginandoResultados(entityManager);
		// retornandoClientes(entityManager);
		// returnUsuarios(entityManager);
		// returnIsEmptyDominio(entityManager);
		// findWithUsernameInDomain(entityManager);
		// primeirasConsultas(entityManager);
		// escolhendoORetorno(entityManager);
		// fazendoProjecoes(entityManager);
		// passandoParametros(entityManager);
		// fazendoJoins(entityManager);
		// fazendoLeftJoin(entityManager);
		// carregamentoComJoinFetch(entityManager);
		// filtrandoRegistros(entityManager);
		// utilizandoOperadoresLogicos(entityManager);
		// utilizandoOperadorIn(entityManager);
		// ordenandoResultados(entityManager);
		paginandoResultados(entityManager);
		

		entityManager.close();
		entityManagerFactory.close();

	
	}

	public static void returnUsuarios(EntityManager entityManager) {

		String jplqUsuario = "select u from Usuario u join fetch u.dominio join fetch u.configuracao";
		TypedQuery<Usuario> usuarios = entityManager.createQuery(jplqUsuario, Usuario.class);

		List<Usuario> listaUsuarios = usuarios.getResultList();

		listaUsuarios.forEach(pessoas -> System.out.println(pessoas.getNome()));

	}

	public static void returnIsEmptyDominio(EntityManager entityManager) {
		String jpql = "select d from Dominio d where d.usuarios is empty";
		TypedQuery<Dominio> listUsuariosSemDominio = entityManager.createQuery(jpql, Dominio.class);
		List<Dominio> listUsuariosSemDominio0 = listUsuariosSemDominio.getResultList();
		listUsuariosSemDominio0.forEach(d -> System.out.println(d.getNome()));
	}

	public static void findWithUsernameInDomain(EntityManager entityManager) {
		String jpql = "select u from Usuario u where u.dominio.nome like :searchName";
		TypedQuery<Usuario> listDominios = entityManager.createQuery(jpql, Usuario.class).setParameter("searchName",
				"LDAP");
		List<Usuario> listNameWithUsuario = listDominios.getResultList();
		listNameWithUsuario.forEach(u -> System.out.println(u.getNome()));
	}


	public static void paginandoResultados(EntityManager entityManager) {
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setFirstResult(0)
				.setMaxResults(2);
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void ordenandoResultados(EntityManager entityManager) {
		String jpql = "select u from Usuario u order by u.nome";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void utilizandoOperadorIn(EntityManager entityManager) {
		String jpql = "select u from Usuario u where u.id in (:ids)";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setParameter("ids",
				Arrays.asList(1, 2));
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void utilizandoOperadoresLogicos(EntityManager entityManager) {
		String jpql = "select u from Usuario u where " + " (u.ultimoAcesso > :ontem and u.ultimoAcesso < :hoje) "
				+ " or u.ultimoAcesso is null";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class)
				.setParameter("ontem", LocalDateTime.now().minusDays(1)).setParameter("hoje", LocalDateTime.now());
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void filtrandoRegistros(EntityManager entityManager) {
// LIKE, IS NULL, IS EMPTY, BETWEEN, >, <, >=, <=, =, <>
// LIKE = select u from Usuario u where u.nome like concat(:nomeUsuario, '%')
// IS NULL = select u from Usuario u where u.senha is null
// IS EMPTY = select d from Dominio d where d.usuarios is empty

		String jpql = "select u from Usuario u where u.ultimoAcesso between :ontem and :hoje";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class)
				.setParameter("ontem", LocalDateTime.now().minusDays(1)).setParameter("hoje", LocalDateTime.now());
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void carregamentoComJoinFetch(EntityManager entityManager) {
		String jpql = "select u from Usuario u join fetch u.configuracao join fetch u.dominio d";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void fazendoLeftJoin(EntityManager entityManager) {
		String jpql = "select u, c from Usuario u left join u.configuracao c";
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> lista = typedQuery.getResultList();

		lista.forEach(arr -> {
			String out = ((Usuario) arr[0]).getNome();
			if (arr[1] == null) {
				out += ", NULL";
			} else {
				out += ", " + ((Configuracao) arr[1]).getId();
			}

			System.out.println(out);
		});
	}

	public static void fazendoJoins(EntityManager entityManager) {
		String jpql = "select u from Usuario u join u.dominio d where d.id = 1";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}

	public static void passandoParametros(EntityManager entityManager) {
		String jpql = "select u from Usuario u where u.id = :idUsuario";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class).setParameter("idUsuario", 1);
		Usuario usuario = typedQuery.getSingleResult();
		System.out.println(usuario.getId() + ", " + usuario.getNome());

		String jpqlLog = "select u from Usuario u where u.login = :loginUsuario";
		TypedQuery<Usuario> typedQueryLog = entityManager.createQuery(jpqlLog, Usuario.class)
				.setParameter("loginUsuario", "ria");
		Usuario usuarioLog = typedQueryLog.getSingleResult();
		System.out.println(usuarioLog.getId() + ", " + usuarioLog.getNome());
	}

	public static void fazendoProjecoes(EntityManager entityManager) {
		String jpqlArr = "select id, login, nome from Usuario";
		TypedQuery<Object[]> typedQueryArr = entityManager.createQuery(jpqlArr, Object[].class);
		List<Object[]> listaArr = typedQueryArr.getResultList();
		listaArr.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));

		String jpqlDto = "select new com.br.consultas.jpql.dto.UsuarioDTO(id, login, nome)" + "from Usuario";
		TypedQuery<UsuarioDTO> typedQueryDto = entityManager.createQuery(jpqlDto, UsuarioDTO.class);
		List<UsuarioDTO> listaDto = typedQueryDto.getResultList();
		listaDto.forEach(u -> System.out.println("DTO: " + u.getId() + ", " + u.getNome()));
	}

	public static void escolhendoORetorno(EntityManager entityManager) {
		String jpql = "select u.dominio from Usuario u where u.id = 1";
		TypedQuery<Dominio> typedQuery = entityManager.createQuery(jpql, Dominio.class);
		Dominio dominio = typedQuery.getSingleResult();
		System.out.println(dominio.getId() + ", " + dominio.getNome());

		String jpqlNom = "select u.nome from Usuario u";
		TypedQuery<String> typedQueryNom = entityManager.createQuery(jpqlNom, String.class);
		List<String> listaNom = typedQueryNom.getResultList();
		listaNom.forEach(nome -> System.out.println(nome));
	}

	public static void primeirasConsultas(EntityManager entityManager) {
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));

		String jpqlSing = "select u from Usuario u where u.id = 1";
		TypedQuery<Usuario> typedQuerySing = entityManager.createQuery(jpqlSing, Usuario.class);
		Usuario usuario = typedQuerySing.getSingleResult();
		System.out.println(usuario.getId() + ", " + usuario.getNome());

		String jpqlCast = "select u from Usuario u where u.id = 1";
		Query query = entityManager.createQuery(jpqlCast);
		Usuario usuario2 = (Usuario) query.getSingleResult();
		System.out.println(usuario2.getId() + ", " + usuario2.getNome());
	}

}
