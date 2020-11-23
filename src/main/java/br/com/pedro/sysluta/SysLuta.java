package br.com.pedro.sysluta;

import java.awt.EventQueue;

import br.com.pedro.sysluta.persistence.BancoDados;
import br.com.pedro.sysluta.persistence.ConnectionFactory;
import br.com.pedro.sysluta.ui.UiPrincipal;

public class SysLuta {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Iniciando");
		BancoDados.carregaAluno();
		BancoDados.carregaProfessor();
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
