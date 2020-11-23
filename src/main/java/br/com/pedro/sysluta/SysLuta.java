package br.com.pedro.sysluta;

import java.awt.EventQueue;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.domain.Professor;
import br.com.pedro.sysluta.persistence.BancoDados;
import br.com.pedro.sysluta.persistence.ConnectionFactory;
import br.com.pedro.sysluta.ui.UiPrincipal;

public class SysLuta {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Iniciando");
		
		Aluno aln1 = new Aluno("55885599", "Jose", (float) 90, "20/12/2000");
		Aluno aln2 = new Aluno("44488899", "João", (float) 90, "22/12/2000");
		Aluno aln3 = new Aluno("33355499", "Joana", (float) 90, "14/12/2000");
		BancoDados.listaAluno.add(aln1);
		BancoDados.listaAluno.add(aln2);
		BancoDados.listaAluno.add(aln3);
		Professor pr1 = new Professor("555549949", "MONICA", "20/10/200");
		Professor pr2 = new Professor("444549949", "MomkeICA", "20/10/200");
		Professor pr3 = new Professor("33349949", "Miller", "20/10/200");
		BancoDados.listaProfessor.add(pr1);
		BancoDados.listaProfessor.add(pr2);
		BancoDados.listaProfessor.add(pr3);
		ConnectionFactory.getConexaoMySQL();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiPrincipal window = new UiPrincipal();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				}
			}
		} catch (Exception ignore) {
		}

	}

}
