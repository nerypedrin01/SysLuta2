package br.com.pedro.sysluta.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.pedro.sysluta.domain.Professor;
import br.com.pedro.sysluta.persistence.BancoDados;
import br.com.pedro.sysluta.persistence.dao.ProfessorDao;
import br.com.pedro.sysluta.util.Constantes;

public class UiProfessor {

	private JDialog frame;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtDataNascimento;
	private JButton btnFechar;
	private Professor professor;
	private int indexProfessor;
	private JButton btnApagar;

	public UiProfessor() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		professor = BancoDados.listaProfessor.get(indexProfessor);
		mostraCampo();
		liberaBotoes(true);
		liberaCampo(false);
	}

	private void initialize() {

		frame = new JDialog();
		frame.setTitle(Constantes.NOME_SYSTEMA);
		frame.setBounds(100, 100, 453, 332);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Cadastro de Professores");
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnPrimeiro = new JButton("<<");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexProfessor = 0;
				professor = BancoDados.listaProfessor.get(indexProfessor);
				mostraCampo();
				JOptionPane.showMessageDialog(frame, "Chegou no Primeiro!!");
				botoesNavegacao(false);
			}
		});
		panel_1.add(btnPrimeiro);

		btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (indexProfessor == 0) {
					JOptionPane.showMessageDialog(frame, "Ja está no Primeiro!!");
					botoesNavegacao(false);
				} else {
					indexProfessor = indexProfessor - 1;
					professor = BancoDados.listaProfessor.get(indexProfessor);
					mostraCampo();
					liberabotoesNavegacao(true);
				}
			}
		});
		panel_1.add(btnAnterior);

		btnProximo = new JButton(">");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (indexProfessor == (BancoDados.listaAluno.size() - 1)) {
					JOptionPane.showMessageDialog(frame, "Ja está no último!!");
					botoesNavegacao(true);
				} else {
					indexProfessor = indexProfessor + 1;
					professor = BancoDados.listaProfessor.get(indexProfessor);
					mostraCampo();
					liberabotoesNavegacao(true);
				}
			}
		});
		panel_1.add(btnProximo);

		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexProfessor = BancoDados.listaProfessor.size() - 1;
				professor = BancoDados.listaProfessor.get(indexProfessor);
				mostraCampo();
				JOptionPane.showMessageDialog(frame, "Chegou no último!!");
				botoesNavegacao(true);
			}
		});
		panel_1.add(btnUltimo);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberaCampo(true);
				limpaCampo();
				liberaBotoes(false);
				liberabotoesNavegacao(false);
				professor = new Professor();
			}
		});
		panel_2.add(btnNovo);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberaCampo(true);
				liberabotoesNavegacao(false);
				liberaBotoes(false);
			}
		});
		panel_2.add(btnAlterar);

		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "Deseja realmente apagar?", "OPA!!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {

						BancoDados.listaProfessor.remove(indexProfessor);
						ProfessorDao.romoveProfessor(professor);
						JOptionPane.showMessageDialog(frame, "Apagado com Sucesso");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					mostraCampo();
				}
			}
		});
		panel_2.add(btnApagar);

		JLabel label = new JLabel("");
		panel_2.add(label);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCpf.getText().equals("")) {
					txtCpf.requestFocus();
					JOptionPane.showMessageDialog(frame, "Cooloque o CPF!");
				} else if (txtNome.getText().equals("")) {
					txtNome.requestFocus();
					JOptionPane.showMessageDialog(frame, "Cooloque o Nome!");

				} else if (txtDataNascimento.getText().equals("")) {
					txtDataNascimento.requestFocus();
					JOptionPane.showMessageDialog(frame, "Cooloque o Nome!");

				} else {
					if (professor.getCpf() == null) {
						try {
							gravaVariaveis();
							BancoDados.listaProfessor.add(professor);
							liberaBotoes(true);
							liberaCampo(false);
							liberabotoesNavegacao(true);
							ProfessorDao.insere(professor);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, e1.getMessage());
							e1.printStackTrace();
						}

					} else if (professor.getCpf() != null) {
						try {
							gravaVariaveis();
							liberaBotoes(true);
							liberaCampo(false);
							liberabotoesNavegacao(true);
							ProfessorDao.atualizaprofessor(professor);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, e1.getMessage());
							e1.printStackTrace();
						}
					}
				}
			}
		});
		panel_2.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				professor = BancoDados.listaProfessor.get(0);
				mostraCampo();
				liberaBotoes(true);
				liberabotoesNavegacao(true);
				liberaCampo(false);
			}
		});
		panel_2.add(btnCancelar);

		JLabel label_1 = new JLabel("");
		panel_2.add(label_1);

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnFechar.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnFechar);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);

		txtCpf = new JTextField();
		txtCpf.setBounds(6, 41, 327, 28);
		panel_3.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setBounds(6, 13, 55, 16);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(6, 84, 55, 16);
		panel_3.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(6, 112, 327, 28);
		panel_3.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Data de Nascimento:");
		lblNewLabel_3.setBounds(6, 156, 129, 16);
		panel_3.add(lblNewLabel_3);

		try {
			txtDataNascimento = new JTextField();
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataNascimento.setBounds(6, 191, 122, 28);
			panel_3.add(txtDataNascimento);
			txtDataNascimento.setColumns(10);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	public void show() {

		frame.setVisible(true);
	}

	public void liberaCampo(Boolean b) {

		txtCpf.setEditable(b);
		txtNome.setEditable(b);
		txtDataNascimento.setEditable(b);
	}

	public void limpaCampo() {

		txtCpf.setText("");
		txtNome.setText("");
		txtDataNascimento.setText("");
		txtCpf.requestFocus();
	}

	public void liberaBotoes(Boolean b) {

		btnNovo.setEnabled(b);
		btnAlterar.setEnabled(b);
		btnSalvar.setEnabled(!b);
		btnCancelar.setEnabled(!b);
		btnFechar.setEnabled(b);
		btnApagar.setEnabled(b);

	}

	public void mostraCampo() {

		txtCpf.setText(professor.getCpf());
		txtNome.setText(professor.getNome());
		txtDataNascimento.setText(professor.getDataNascimento());

	}

	public void botoesNavegacao(Boolean b) {
		btnPrimeiro.setEnabled(b);
		btnAnterior.setEnabled(b);
		btnProximo.setEnabled(!b);
		btnUltimo.setEnabled(!b);
	}

	public void liberabotoesNavegacao(Boolean b) {
		btnPrimeiro.setEnabled(b);
		btnAnterior.setEnabled(b);
		btnProximo.setEnabled(b);
		btnUltimo.setEnabled(b);
	}

	public void gravaVariaveis() {

		professor.setCpf(txtCpf.getText());
		professor.setNome(txtNome.getText());
		professor.setDataNascimento(txtDataNascimento.getText());
	}
}
