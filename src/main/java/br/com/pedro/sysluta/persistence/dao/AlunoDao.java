package br.com.pedro.sysluta.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.persistence.ConnectionFactory;

public class AlunoDao {

	private static Connection conn = ConnectionFactory.getConexaoMySQL();

	public static void insere(Aluno aluno) throws SQLException {
		

			String inserir = "INSERT INTO aluno (nome, cpf, dataNascimento, mensalidade) values(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(inserir);

			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getCpf());
			pst.setString(3, aluno.getDataNascimento());
			pst.setFloat(4, aluno.getMensalidade());

			pst.executeUpdate();

		
	}

	public static void atualizaAluno(Aluno aluno) throws SQLException {

			String inserir = "UPDATE aluno SET nome = ?, dataNascimento = ?, mensalidade = ? , cpf = ? WHERE (id_aluno = ?)";
			PreparedStatement pst = conn.prepareStatement(inserir);

			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getDataNascimento());
			pst.setFloat(3, aluno.getMensalidade());
			pst.setString(4, aluno.getCpf());
			pst.setInt(5, aluno.getIdAluno());

			pst.executeUpdate();

	}

	public static void romoveAluno(Aluno aluno) throws SQLException {

		String remove = "DELETE FROM aluno WHERE id_aluno = ?";
		PreparedStatement pst = conn.prepareStatement(remove);
		pst.setInt(1, aluno.getIdAluno());
		pst.executeUpdate();
	}

}
