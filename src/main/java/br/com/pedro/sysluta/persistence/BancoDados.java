package br.com.pedro.sysluta.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.domain.Professor;
import br.com.pedro.sysluta.util.Constantes;

public class BancoDados {

	public static ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
	public static ArrayList<Professor> listaProfessor = new ArrayList<Professor>();

	public static void carregaAluno() {
		try {
			String query = "SELECT * FROM sysluta.aluno";
			Connection con = DriverManager.getConnection(Constantes.CONNECTION_MYSQL, Constantes.USER_NAME,
					Constantes.PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setCpf(rs.getString(3));
				aluno.setNome(rs.getString(2));
				aluno.setIdAluno(Integer.parseInt(rs.getString(1)));
				aluno.setDataNascimento(rs.getString(4));
				aluno.setMensalidade(Float.parseFloat(rs.getString(5)));

				listaAluno.add(aluno);
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void carregaProfessor() {
		try {
			String query = "SELECT * FROM sysluta.professor";
			Connection con = DriverManager.getConnection(Constantes.CONNECTION_MYSQL, Constantes.USER_NAME,
					Constantes.PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Professor professor = new Professor();
				professor.setIdProfessor(Integer.parseInt(rs.getString(1)));
				professor.setNome(rs.getString(2));
				professor.setCpf(rs.getString(3));
				professor.setDataNascimento(rs.getString(4));

				listaProfessor.add(professor);
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
