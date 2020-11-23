package br.com.pedro.sysluta.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.pedro.sysluta.util.Constantes;

public class ConnectionFactory {

	private static Connection conn;

	public static Connection getConexaoMySQL() {

		if (conn != null) {
			return conn;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(Constantes.CONNECTION_MYSQL, Constantes.USER_NAME, Constantes.PASSWORD);

			if (!conn.isClosed()) {
				System.out.println("STATUS--->Conectado com sucesso!");
			} else {
				System.out.println("STATUS--->Não foi possivel realizar conexão");
			}

			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}

	public static boolean FecharConexao() {

		try {

			ConnectionFactory.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	
	public static void insereProfessor(String nome, String cpf, String dataNascimento) {
		try {

			String inserir = "insert into sysluta.professores(nome,cpf,dataNascimento,mensalidade)values(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(inserir);
			pst.setString(1, nome);
			pst.setString(2, cpf);
			pst.setString(3, dataNascimento);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
