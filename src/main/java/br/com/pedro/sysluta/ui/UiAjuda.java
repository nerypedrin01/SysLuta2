package br.com.pedro.sysluta.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;

import br.com.pedro.sysluta.util.Constantes;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;

public class UiAjuda {
	private JDialog frame;
	private JTextField txtCadastroDeAlunos;

	public UiAjuda() {
		initialize();
	
	}

	private void initialize() {
		frame = new JDialog();
		frame.setTitle(Constantes.NOME_SYSTEMA);
		frame.setBounds(100, 100, 379, 264);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		txtCadastroDeAlunos = new JTextField();
		txtCadastroDeAlunos.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 12));
		txtCadastroDeAlunos.setText("    Cadastro de alunos e professores de artes marciais.");
		txtCadastroDeAlunos.setEditable(false);
		txtCadastroDeAlunos.setEnabled(false);
		frame.getContentPane().add(txtCadastroDeAlunos, BorderLayout.CENTER);
		txtCadastroDeAlunos.setColumns(10);

	}
	public void show() {

		frame.setVisible(true);
	}
}
