package br.com.pedro.sysluta.teste;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.util.Constantes;

public class TestaCodigo {
	
	public static void main(String[] args) throws SQLException {

		
			String query = "SELECT * FROM sysluta.aluno";
			Connection con =  DriverManager.getConnection(Constantes.CONNECTION_MYSQL, Constantes.USER_NAME, Constantes.PASSWORD);
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(query);

			List<Aluno> alunos = new ArrayList<>();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setCpf(rs.getString(3));
				aluno.setNome(rs.getString(2));
				aluno.setIdAluno(Integer.parseInt(rs.getString(1)));
				aluno.setDataNascimento(rs.getString(4));
				aluno.setMensalidade(Float.parseFloat(rs.getString(5)));
				
				System.out.print(rs.getString(1));
				System.out.print("\t|\t");
				System.out.print(rs.getString(2));
				System.out.print("\t|\t");
				System.out.print(rs.getString(3));
				System.out.print("\t|\t");
				System.out.print(rs.getString(4));
				System.out.print("\t|\t");
				System.out.println(rs.getString(5));
				
				alunos.add(aluno);
			}

		con.close();
	}
}
