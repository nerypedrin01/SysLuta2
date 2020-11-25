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
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.UtilDateModel;

import br.com.pedro.sysluta.domain.Aluno;
import br.com.pedro.sysluta.persistence.BancoDados;
import br.com.pedro.sysluta.persistence.dao.AlunoDao;
import br.com.pedro.sysluta.util.Constantes;

public class UiAluno {

	private JDialog frame;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnFechar;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtMensalidade;
	private JLabel label;
	private JLabel label_1;
	private Aluno aluno;
	private int indexAluno = 0;
	private UtilDateModel model = new UtilDateModel();

	private JPanel panel_3;
	private JPanel panel_2;
	private JPanel panel;
	private JButton btnApagar;
	private JTextField txtDataNascimento;

	public UiAluno() {
		initialize();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		aluno = BancoDados.listaAluno.get(indexAluno);
		mostraCampo();
		liberaBotoes(true);
		liberaCampo(false);
	}

	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 511, 341);
		frame.setTitle(Constantes.NOME_SYSTEMA);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		btnPrimeiro = new JButton("<<");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexAluno = 0;
				aluno = BancoDados.listaAluno.get(indexAluno);
				mostraCampo();
				JOptionPane.showMessageDialog(frame, "Chegou no Primeiro!!");
				botoesNavegacao(false);
			}
		});
		panel.add(btnPrimeiro);

		btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (indexAluno == 0) {
					JOptionPane.showMessageDialog(frame, "Ja está no Primeiro!!");
					botoesNavegacao(false);
				} else {
					indexAluno = indexAluno - 1;
					aluno = BancoDados.listaAluno.get(indexAluno);
					mostraCampo();
					liberabotoesNavegacao(true);
				}
			}
		});
		panel.add(btnAnterior);

		btnProximo = new JButton(">");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (indexAluno == (BancoDados.listaAluno.size() - 1)) {
					JOptionPane.showMessageDialog(frame, "Ja está no último!!");
					botoesNavegacao(true);
				} else {
					indexAluno = indexAluno + 1;
					aluno = BancoDados.listaAluno.get(indexAluno);
					mostraCampo();
					liberabotoesNavegacao(true);
				}
			}
		});
		panel.add(btnProximo);

		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexAluno = BancoDados.listaAluno.size() - 1;
				aluno = BancoDados.listaAluno.get(indexAluno);
				mostraCampo();
				JOptionPane.showMessageDialog(frame, "Chegou no último!!");
				botoesNavegacao(true);
			}
		});
		panel.add(btnUltimo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberaCampo(true);
				limpaCampo();
				liberaBotoes(false);
				liberabotoesNavegacao(false);
				aluno = new Aluno();

			}
		});
		panel_1.add(btnNovo);

		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(frame, "Deseja realmente apagar?", "OPA!!",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						BancoDados.listaAluno.remove(indexAluno);
						AlunoDao.romoveAluno(aluno);
						JOptionPane.showMessageDialog(frame, "Apagado com Sucesso");
					} else {
						mostraCampo();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
		});

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberaCampo(true);
				liberabotoesNavegacao(false);
				liberaBotoes(false);

			}
		});
		panel_1.add(btnAlterar);
		panel_1.add(btnApagar);

		label = new JLabel("");
		panel_1.add(label);

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

				} else if (txtMensalidade.getText().equals("")) {
					txtMensalidade.requestFocus();
					JOptionPane.showMessageDialog(frame, "Cooloque o Nome!");

				} else {

					if (aluno.getCpf() == null) {
						try {
							txtCpf.requestFocus();
							gravaVariaveis();
							BancoDados.listaAluno.add(aluno);
							liberaBotoes(true);
							liberaCampo(false);
							liberabotoesNavegacao(true);

							AlunoDao.insere(aluno);
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
					} else if (aluno.getCpf() != null) {
						try {
							gravaVariaveis();
							liberaBotoes(true);
							liberaCampo(false);
							liberabotoesNavegacao(true);
							AlunoDao.atualizaAluno(aluno);
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
					}
				}

			}
		});

		panel_1.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aluno = BancoDados.listaAluno.get(0);
				mostraCampo();
				liberaBotoes(true);
				liberabotoesNavegacao(true);
				liberaCampo(false);
			}
		});
		panel_1.add(btnCancelar);

		label_1 = new JLabel("");
		panel_1.add(label_1);

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);

			}
		});
		panel_1.add(btnFechar);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setBounds(10, 12, 46, 14);

		txtCpf = new JTextField();
		txtCpf.setEditable(false);
		txtCpf.setBounds(10, 36, 337, 28);
		panel_2.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(10, 77, 46, 14);
		panel_2.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(10, 102, 337, 28);
		panel_2.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Data de Nascimento:");
		lblNewLabel_3.setBounds(10, 143, 117, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Mensalidade:");
		lblNewLabel_4.setBounds(246, 143, 90, 14);
		panel_2.add(lblNewLabel_4);

		txtMensalidade = new JTextField();
		txtMensalidade.setEditable(false);
		txtMensalidade.setBounds(246, 170, 90, 26);
		panel_2.add(txtMensalidade);
		txtMensalidade.setColumns(10);

		try {
			txtDataNascimento = new JTextField();
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataNascimento.setBounds(10, 169, 122, 28);
			panel_2.add(txtDataNascimento);
			txtDataNascimento.setColumns(10);

			JLabel lblNewLabel_5 = new JLabel("CPF:");
			lblNewLabel_5.setBounds(10, 11, 46, 14);
			panel_2.add(lblNewLabel_5);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		model = new UtilDateModel();
		model.setSelected(true);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Cadastro de Alunos");
		panel_3.add(lblNewLabel);
	}

	public void show() {

		frame.setVisible(true);
	}

	public void liberaCampo(Boolean b) {

		txtCpf.setEditable(b);
		txtMensalidade.setEditable(b);
		txtNome.setEditable(b);
		txtDataNascimento.setEditable(b);
	}

	public void limpaCampo() {

		txtCpf.setText("");
		txtMensalidade.setText("");
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

		txtCpf.setText(aluno.getCpf());
		txtMensalidade.setText(aluno.getMensalidade() + "");
		txtNome.setText(aluno.getNome());
		txtDataNascimento.setText(aluno.getDataNascimento());

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

		aluno.setCpf(txtCpf.getText());
		aluno.setMensalidade(Float.parseFloat(txtMensalidade.getText()));
		aluno.setNome(txtNome.getText());
		aluno.setDataNascimento(txtDataNascimento.getText());
	}
}
