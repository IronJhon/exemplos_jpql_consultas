package com.br.consultas.jpql.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.br.consultas.jpql.main.dto.model.Dominio;
import com.br.consultas.jpql.main.dto.model.Usuario;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 EntityManagerFactory entityManagerFactory = Persistence
	                .createEntityManagerFactory("CONFIG-DATABASE-PU");
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
		
	        
	        //paginandoResultados(entityManager);
	        //retornandoClientes(entityManager);
	        //returnUsuarios(entityManager);
	        //returnIsEmptyDominio(entityManager);
	        findWithUsernameInDomain(entityManager);
	}
	
	
	   public static void paginandoResultados(EntityManager entityManager) {
	        String jpql = "select u from Usuario u";
	        TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class)
	                .setFirstResult(0)
	                .setMaxResults(2);
	        List<Usuario> lista = typedQuery.getResultList();
	        lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	    }
	   
	   

  
  public static void returnUsuarios (EntityManager entityManager) {
	   
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
	  TypedQuery<Usuario> listDominios = entityManager.createQuery(jpql, Usuario.class)
			  .setParameter("searchName", "LDAP");
	  List<Usuario> listNameWithUsuario = listDominios.getResultList();
	  listNameWithUsuario.forEach(u -> System.out.println(u.getNome()));
  }
	   
	   
	

}
