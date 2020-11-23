package br.com.pedro.sysluta.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.persistence.ConnectionFactory;

public class AlunoDao {

	private static Connection conn = ConnectionFactory.getConexaoMySQL();

	public static void insere(Aluno aluno) {
		try {

			String inserir = "INSERT INTO aluno (nome, cpf, dataNascimento, mensalidade) values(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(inserir);

			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getCpf());
			pst.setString(3, aluno.getDataNascimento());
			pst.setFloat(4, aluno.getMensalidade());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void atualizaAluno(Aluno aluno) {
		try {

			String inserir = "UPDATE aluno SET nome = ?, dataNascimento = ?, mensalidade = ? , cpf = ? WHERE (id_aluno = ?)";
			PreparedStatement pst = conn.prepareStatement(inserir);

			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getDataNascimento());
			pst.setFloat(3, aluno.getMensalidade());
			pst.setString(4, aluno.getCpf());
			pst.setInt(5, aluno.getIdAluno());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void romoveAluno(Aluno aluno) {

		try {
			String inserir = "DELETE FROM aluno WHERE (id_aluno = ?)";
			PreparedStatement pst = conn.prepareStatement(inserir);
			pst.setInt(1, aluno.getIdAluno());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
