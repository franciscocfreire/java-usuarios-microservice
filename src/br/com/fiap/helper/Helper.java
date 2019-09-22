package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Usuario;

public class Helper {

	private EntityManager entityManager;

	public Helper(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() {
		Query query = entityManager.createQuery("select a from Usuario a");
		return query.getResultList();
	}

	/*@SuppressWarnings("unchecked")
	public List<Curso> listarCursos() {
		Query query = entityManager.createQuery("select c from Curso c");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioCurso> listarUsuarioCurso() {
		Query query = entityManager.createQuery("select ac from UsuarioCurso ac");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Escola> listarEscola() {
		Query query = entityManager.createQuery("select e from Escola e");
		return query.getResultList();
	}*/

	public Usuario buscarUsuario(int numMatricula) {
		Query query = entityManager
				.createQuery("select a from Usuario a where idUsuario = :idUsuario");
		query.setParameter("idUsuario", numMatricula);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario;
	}

	/*public Curso buscarCurso(int numCurso) {
		Query query = entityManager
				.createQuery("select c from Curso c where idCurso = :idCurso");
		query.setParameter("idCurso", numCurso);
		Curso curso = (Curso) query.getSingleResult();
		return curso;
	}

	public Escola buscarEscola(int codEscola) {
		Query query = entityManager
				.createQuery("select e from Escola e where idEscola =:idEscola");
		query.setParameter("idEscola", codEscola);
		Escola escola = (Escola) query.getSingleResult();
		return escola;
	}
*/
	public void salvarUsuario(Usuario usuario) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	/*public void salvarEscola(Escola escola) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(escola);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void salvarCurso(Curso curso) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(curso);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void salvarUsuarioCursoNota(UsuarioCurso usuarioCurso) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuarioCurso);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}*/

}
