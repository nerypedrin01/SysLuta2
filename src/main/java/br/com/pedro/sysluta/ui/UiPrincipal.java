package br.com.pedro.sysluta.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import br.com.pedro.sysluta.util.Constantes;

public class UiPrincipal {

	public static JFrame frame;
	private JLabel lblNewLabel;
	private JMenuItem mnProfessor;
	private JMenu mnCadastro;
	private JMenu mnAjuda;

	public UiPrincipal() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Constantes.NOME_SYSTEMA);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		mnProfessor = new JMenuItem("Professor");
		mnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UiProfessor ui = new UiProfessor();
					ui.show();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		mnCadastro.add(mnProfessor);

		JMenuItem mnAluno = new JMenuItem("Aluno");
		mnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UiAluno ui = new UiAluno();
					ui.show();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		mnCadastro.add(mnAluno);

		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);

		JMenuItem mntSair = new JMenuItem("Sair");
		mntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnCadastro.add(mntSair);

		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mnSobre = new JMenuItem("Sobre");
		mnSobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UiAjuda ui = new UiAjuda();
					ui.show();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		mnAjuda.add(mnSobre);

		lblNewLabel = new JLabel("SysLuta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}

	public void show() {

		frame.setVisible(true);
	}

}
