package br.com.pedro.sysluta.domain;

public class Aluno {

	private String cpf;
	private String nome;
	private Float mensalidade;
	private String dataNascimento;
	private int idAluno;

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Float mensalidade) {
		this.mensalidade = mensalidade;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Aluno(String cpf, String nome, Float mensalidade, String dataNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.mensalidade =mensalidade;
		this.dataNascimento = dataNascimento ;

	}

	public Aluno() {
		// TODO Auto-generated constructor stub
	}
}
