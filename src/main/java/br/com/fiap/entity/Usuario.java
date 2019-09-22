package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private String nomeUsuario;
	private String enderecoUsuario;
	private int telefoneUsuario;
	private String caminhoFoto;
	//private Set<Curso> cursos = new HashSet<>();
	//private Set<UsuarioCurso> usuarioCurso = new HashSet<>();

	public Usuario() {
		super();
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "nome_usuario", nullable = false, length = 255)
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(name = "endereco_usuario", nullable = false, length = 255)
	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	@Column(name = "telefone_usuario", nullable = false)
	public int getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(int telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	@Column(name = "foto", nullable = false, length = 255)
	public String getFoto() {
		return caminhoFoto;
	}

	public void setFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

/*
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_usuario_curso", joinColumns = { @JoinColumn(name = "fk_id_usuario", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "fk_id_curso", nullable = false, updatable = false) })
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<UsuarioCurso> getUsuarioCurso() {
		return usuarioCurso;
	}

	public void setUsuarioCurso(Set<UsuarioCurso> usuarioCurso) {
		this.usuarioCurso = usuarioCurso;
	}
*/

}
