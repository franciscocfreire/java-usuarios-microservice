package br.com.fiap.programa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Usuario;
import br.com.fiap.helper.Helper;

public class Aplicacao {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Scanner entrada = new Scanner(System.in);
		int opcao;
		int idUsuario = 0;
		int idCurso = 0;
		System.out.println("Escolha uma das op��es abaixo:\n"
				+ "1 - Listar Todos os usuarios.\n"
				+ "2 - Listar todos os cursos.\n"
				+ "3 - Listar todos os usuarios e cursos matriculados.\n"
				+ "4 - Incluir Usuario.\n"
				+ "5 - Incluir Curso.\n"
				+ "6 - Buscar Usuario.\n"
				+ "7 - Buscar Curso.\n"
				+ "8 - Incluir Nota Usuario.\n"
				+ "0 - Finalizar.\n");

		opcao = entrada.nextInt();
		while (opcao != 0) {
			switch (opcao) {
			case 1:
				listarUsuarios(entityManager);
				break;
		/*	case 2:
				listarCursos(entityManager);
				break;
			case 3:
				listarUsuarioCurso(entityManager);
				break;
			case 4:
				incluirUsuario(entityManager);
				break;
			case 5:
				incluirCurso(entityManager);
				break;
			case 6:
				buscarUsuario(entityManager, idUsuario);
				break;
			case 7:
				buscarCurso(entityManager, idCurso);
				break;
			case 8:
				incluirUsuarioCursoNota(entityManager);
				break;*/
			default:
				System.out.println("Op��o inv�lida, favor selecionar umas das op��es v�lidas.\n");
			}
			System.out.println("\nEscolha uma das op��es abaixo:\n"
					+ "1 - Listar Todos os usuarios.\n"
					+ "2 - Listar todos os cursos.\n"
					+ "3 - Listar todos os usuarios e cursos matriculados.\n"
					+ "4 - Incluir Usuario.\n"
					+ "5 - Incluir Curso.\n"
					+ "6 - Buscar Usuario.\n"
					+ "7 - Buscar Curso.\n"
					+ "8 - Incluir Nota Usuario.\n"
					+ "0 - Finalizar.\n");
			opcao = entrada.nextInt();

		}
		System.out.println("Opera��o de consulta finalizado!!!!");
		entityManager.close();
	}

	private static void listarUsuarios(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<Usuario> usuarios = dao.listarUsuarios();
		for (Usuario usuario : usuarios) {
			System.out.println("Matr�cula: " + usuario.getIdUsuario() + " Nome: "
					+ usuario.getNomeUsuario() + "  Endere�o: "
					+ usuario.getEnderecoUsuario() + " Telefone: "
					+ usuario.getTelefoneUsuario() + "\n");
		}
	}

	/*private static void listarCursos(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<Curso> cursos = dao.listarCursos();
		for (Curso curso : cursos) {
			System.out.println("Curso: " + curso.getNomeCurso()
					+ " Carga Hor�ria: " + curso.getCargaHoraria() + "\n");
		}
	}

	private static void listarUsuarioCurso(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		List<UsuarioCurso> usuarioCursos = dao.listarUsuarioCurso();
		for (UsuarioCurso usuarioCurso : usuarioCursos) {
			System.out.println("Matr�cula Usuario: "
					+ usuarioCurso.getUsuarioCursoPk().getFkIdUsuario()
					+ " Nome Usuario: " + usuarioCurso.getUsuario().getNomeUsuario()
					+ " Curso: " + usuarioCurso.getCurso().getNomeCurso()
					+ " Nota: " + usuarioCurso.getNota() + "\n");
		}
	}*/

	private static void buscarUsuario(EntityManager entityManager, int idUsuario) {
		Helper dao = new Helper(entityManager);
		Scanner buscarUsuario = new Scanner(System.in);
		System.out.println("Informe a matr�cula do usuario: ");
		String matricula = buscarUsuario.nextLine();
		idUsuario = Integer.parseInt(matricula);
		Usuario usuario = dao.buscarUsuario(idUsuario);
		System.out.println("Matr�cula: " + usuario.getIdUsuario() + " Nome: "
				+ usuario.getNomeUsuario() + " Endere�o: "
				+ usuario.getEnderecoUsuario() + " Telefone: ");
	}

/*
	private static void buscarCurso(EntityManager entityManager, int idCurso) {
		Helper dao = new Helper(entityManager);
		Scanner buscarCurso = new Scanner(System.in);
		System.out.println("Informe o c�digo do curso: ");
		String curso = buscarCurso.nextLine();
		idCurso = Integer.parseInt(curso);
		Curso cursos = dao.buscarCurso(idCurso);
		System.out.println("Curso: " + cursos.getNomeCurso()
				+ " Carga Hor�ria: " + cursos.getCargaHoraria());
	}
*/

	
	private static void incluirUsuario(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaUsuario = new Scanner(System.in);
			System.out.println("Informe o nome do usuario: ");
			String nome = entradaUsuario.nextLine();
			System.out.println("Informe endere�o do usuario: ");
			String endereco = entradaUsuario.nextLine();
			System.out.println("Informe telefone do usuario: ");
			String telefone = entradaUsuario.nextLine();
			int telefoneUsuario = Integer.parseInt(telefone);

			Usuario usuario = new Usuario();
			usuario.setNomeUsuario(nome);
			usuario.setEnderecoUsuario(endereco);
			usuario.setTelefoneUsuario(telefoneUsuario);

			dao.salvarUsuario(usuario);
			System.out.println("Usuario inclu�do com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro na inclus�o do usuario: " + e.getMessage());
		}
	}

	/*private static void incluirCurso(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaEscola = new Scanner(System.in);
			System.out.println("Informe o c�digo da Escola a qual pertence o curso: ");
			String escola = entradaEscola.nextLine();
			int idEscola = Integer.parseInt(escola);

			Escola escolas = dao.buscarEscola(idEscola);

			Scanner entradaCurso = new Scanner(System.in);
			System.out.println("Informe nome do Curso: ");
			String nomeCurso = entradaCurso.nextLine();
			System.out.println("Informa a carga hor�ria do curso: ");
			String cargaHoraria = entradaCurso.nextLine();
			int cargHoraria = Integer.parseInt(cargaHoraria);

			Escola novaEscola = new Escola();
			novaEscola.setIdEscola(escolas.getIdEscola());
			novaEscola.setNomeEscola(escolas.getNomeEscola());
			novaEscola.setEnderecoEscola(escolas.getEnderecoEscola());

			Curso curso = new Curso();
			curso.setEscola(novaEscola);
			curso.setNomeCurso(nomeCurso);
			curso.setCargaHoraria(cargHoraria);

			novaEscola.getCursos().add(curso);

			dao.salvarCurso(curso);
			System.out.println("Curso inclu�do com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro na inclus�o do curso: " + e.getMessage());
		}

	}
*/
/*	public static void incluirUsuarioCursoNota(EntityManager entityManager) {
		Helper dao = new Helper(entityManager);
		try {
			Scanner entradaUsuario = new Scanner(System.in);
			System.out.println("Informe a matr�cula do usuario: ");
			String usuario = entradaUsuario.nextLine();
			int idUsuario = Integer.parseInt(usuario);

			Usuario usuarios = dao.buscarUsuario(idUsuario);

			System.out.println("Informe c�digo do curso na qual o usuario est� matriculado: ");
			String curso = entradaUsuario.nextLine();
			int idCurso = Integer.parseInt(curso);

			Curso cursos = dao.buscarCurso(idCurso);

			System.out.println("Informe a nota do usuario "
					+ usuarios.getNomeUsuario() + " matriculado no curso "
					+ cursos.getNomeCurso() + ": ");
			String notas = entradaUsuario.nextLine();
			int nota = Integer.parseInt(notas);
			
			if(nota < 0 || nota > 10){
				System.out.println("Nota inv�lida!!!\nFavor informar nota de 0 a 10!!!");
			}else{
				UsuarioCursoPK usuarioCursoPk = new UsuarioCursoPK();
				usuarioCursoPk.setFkIdUsuario(usuarios.getIdUsuario());
				usuarioCursoPk.setFkIdCurso(cursos.getIdCurso());
				
				UsuarioCurso usuarioCurso = new UsuarioCurso();
				usuarioCurso.setUsuarioCursoPk(usuarioCursoPk);
				usuarioCurso.setNota(nota);
				
				dao.salvarUsuarioCursoNota(usuarioCurso);
				System.out.println("Nota do usuario incluido com sucesso.");
			}
		} catch (Exception e) {
			System.out.println("Erro na inclus�o da nota do usuario: " + e.getMessage());
		}

	}*/

}
