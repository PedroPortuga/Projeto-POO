package br.com.mvc.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.mvc.dao.*;
import br.com.mvc.model.*;


public class Telas extends JFrame {

	private JPanel contentPane;
	private JTextField textRGM;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textEnd;
	private JTextField textRGM2;
	private JTextField textFal;
	private JTextField textRGM3;
	private JTextField textMuni;
	private JFormattedTextField formattedData;
	private JFormattedTextField formattedCPF;
	private JFormattedTextField formattedCel;
	private JComboBox cmbUF;
	private JComboBox cmbCurso;
	private JComboBox cmbCampus;
	private JRadioButton rdbtnMatutino;
	private	JRadioButton rdbtnVespertino;
	private	JRadioButton rdbtnNoturno;
	private ButtonGroup grupoBotoes;
	private JMenuItem mntmSalvar;
	private JMenuItem mntmSalvar2;
	private JMenuItem mntmAlterar;
	private JMenuItem mntmAlterar2;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmConsultar2;
	private JMenuItem mntmExcluir;
	private JMenuItem mntmExcluir2;
	private JLabel lbl_Nome;
	private JLabel lbl_Curso;
	private JComboBox cmbDis;
	private JComboBox cmbSem;
	private JComboBox cmbNot;
	private JLabel lbl_Fal2;
	
	
	Aluno aluno = new Aluno();
	Not_Fal notfal = new Not_Fal();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telas frame = new Telas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Telas() throws Exception {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 562, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Aluno");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mnNewMenu.add(mntmNovo);
		
		mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//---------------------------------------	
			try {				
				// Criando o objeto aluno para pegar os dados da tela
				
				aluno.setRGM_Aluno(Integer.parseInt(textRGM.getText()));
				aluno.setNome_Aluno(textNome.getText());
				
				String dataString = formattedData.getText();
			    dataString = dataString.replaceAll("/", "");
			    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			    Date dataNascimento = format.parse(dataString);
			    aluno.setDat_Nas_Aluno(dataNascimento); 	
			    			    
			    String cpf = formattedCPF.getValue().toString().replaceAll("[^0-9]", "");
			    aluno.setCPF_Aluno(Long.parseLong(cpf));
				aluno.setEmail_Aluno(textEmail.getText());
				aluno.setEnd_Aluno(textEnd.getText());
				aluno.setMuni_Aluno(textMuni.getText());
				aluno.setUF_Aluno((String) cmbUF.getSelectedItem());
			
				Object phoneValue = formattedCel.getValue();
				String phone = "";
				if (phoneValue != null) {
				    phone = phoneValue.toString().replaceAll("[^0-9]", "");
				}
				aluno.setCel_Aluno(Long.parseLong(phone));
				aluno.setCur_Aluno((String) cmbCurso.getSelectedItem());
				aluno.setCam_Aluno((String) cmbCampus.getSelectedItem());
				aluno.setPer_Aluno(PeriodoAluno.Matutino);
				if (rdbtnMatutino.isSelected()) {
				    aluno.setPer_Aluno(PeriodoAluno.Matutino);
				} else if (rdbtnVespertino.isSelected()) {
				    aluno.setPer_Aluno(PeriodoAluno.Vespertino);
				} else if (rdbtnNoturno.isSelected()) {
				    aluno.setPer_Aluno(PeriodoAluno.Noturno);
				}		
				// Abrir a conexão
				AlunoDao dao = new AlunoDao();
				// Salvar
				dao.salvar(aluno);
				JOptionPane.showMessageDialog(null, "Aluno matrículado com Sucesso!!!");
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro ao matrícular!!!"+e1.getMessage());
			} 
			//---------------------------------------
		}
	});
		
		
		
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmSalvar);
		
		mntmAlterar = new JMenuItem("Alterar");
		mnNewMenu.add(mntmAlterar);
		mntmAlterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Criando o objeto aluno para pegar os dados da tela
		            Aluno aluno = new Aluno();
		            
		            // Preenchendo os dados do aluno com os campos da tela
		            aluno.setRGM_Aluno(Integer.parseInt(textRGM.getText()));
		            aluno.setNome_Aluno(textNome.getText());
		            
		            String dataString = formattedData.getText();
		            dataString = dataString.replaceAll("/", "");
		            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		            Date dataNascimento = format.parse(dataString);
		            aluno.setDat_Nas_Aluno(dataNascimento);
		            
		            String cpf = formattedCPF.getValue().toString().replaceAll("[^0-9]", "");
		            aluno.setCPF_Aluno(Long.parseLong(cpf));
		            
		            aluno.setEmail_Aluno(textEmail.getText());
		            aluno.setEnd_Aluno(textEnd.getText());
		            aluno.setMuni_Aluno(textMuni.getText());
		            aluno.setUF_Aluno((String) cmbUF.getSelectedItem());
		            
		            Object phoneValue = formattedCel.getValue();
		            String phone = "";
		            if (phoneValue != null) {
		                phone = phoneValue.toString().replaceAll("[^0-9]", "");
		            }
		            aluno.setCel_Aluno(Long.parseLong(phone));
		            aluno.setCur_Aluno((String) cmbCurso.getSelectedItem());
		            aluno.setCam_Aluno((String) cmbCampus.getSelectedItem());
		            
		            if (rdbtnMatutino.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Matutino);
		            } else if (rdbtnVespertino.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Vespertino);
		            } else if (rdbtnNoturno.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Noturno);
		            }
		            
		            // Abrir a conexão
		            AlunoDao dao = new AlunoDao();
		            // Alterar os dados do aluno no banco de dados
		            dao.alterar(aluno);
		            JOptionPane.showMessageDialog(null, "Dados do aluno alterados com sucesso!");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do aluno: " + ex.getMessage());
		        }
		    }
		});
		mnNewMenu.add(mntmAlterar);

		
		
		AbstractButton mntmConsultar;
		mntmConsultar = new JMenuItem("Consultar");
		mnNewMenu.add(mntmConsultar);
		mntmConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            //Abrir a conexão
		            AlunoDao dao = new AlunoDao();
		            Aluno aluno = dao.consultar(Integer.parseInt(textRGM.getText()));
		            if(aluno != null) {
		                //setar os dados na tela
		                textNome.setText(aluno.getNome_Aluno());
		                formattedData.setText(new SimpleDateFormat("dd/MM/yyyy").format(aluno.getDat_Nas_Aluno()));
		                formattedCPF.setValue(aluno.getCPF_Aluno());
		                textEmail.setText(aluno.getEmail_Aluno());
		                textEnd.setText(aluno.getEnd_Aluno());
		                textMuni.setText(aluno.getMuni_Aluno());
		                cmbUF.setSelectedItem(aluno.getUF_Aluno());
		                formattedCel.setValue(aluno.getCel_Aluno());
		                cmbCurso.setSelectedItem(aluno.getCur_Aluno());
		                cmbCampus.setSelectedItem(aluno.getCam_Aluno());
		                String cpf = String.format("%011d", aluno.getCPF_Aluno());
		                formattedCPF.setValue(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9));

		                // Ajusta a máscara do campo celular
		                String cel = String.format("%011d", aluno.getCel_Aluno());
		                formattedCel.setValue("(" + cel.substring(0, 2) + ")" + cel.substring(2, 7) + "-" + cel.substring(7));

		                if(aluno.getPer_Aluno() != null) {
		                    switch (aluno.getPer_Aluno()) {
		                        case Matutino:
		                            rdbtnMatutino.setSelected(true);
		                            break;
		                        case Vespertino:
		                            rdbtnVespertino.setSelected(true);
		                            break;
		                        case Noturno:
		                            rdbtnNoturno.setSelected(true);
		                            break;
		                
		                    }
		                }
		                JOptionPane.showMessageDialog(null, "Aluno encontrado com sucesso!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
		            }
		        } catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage());
		        }
		    }
		});
		


		
		mntmExcluir = new JMenuItem("Excluir");
		mnNewMenu.add(mntmExcluir);
		mntmExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Obtenha o RGM_Aluno selecionado
		            int RGM_Aluno = Integer.parseInt(textRGM.getText());
		            
		            // Crie uma instância de AlunoDao
		            AlunoDao dao = new AlunoDao();
		            
		            // Chame o método de exclusão
		            dao.excluir(RGM_Aluno);
		            
		            // Exiba uma mensagem de sucesso
		            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
		            
		            // Limpe os campos de entrada
		            // ...
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno: " + ex.getMessage());
		        }
		    }
		});
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				//------------------
				System.exit(0);
				//------------------
			}
		});
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
		mnNewMenu.add(mntmSair);
		
		JMenu mnNewMenu_1 = new JMenu("Notas e Faltas");
		menuBar.add(mnNewMenu_1);
		
		mntmSalvar2 = new JMenuItem("Salvar");
		mnNewMenu_1.add(mntmSalvar2);
		mntmSalvar2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            //Verificar se todos os campos foram preenchidos
		        	if(textRGM2.getText().isEmpty() || cmbDis.getSelectedItem() == null || cmbSem.getSelectedItem() == null || cmbNot.getSelectedItem() == null || textFal.getText().isEmpty()) {
		        	    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		        	    return;
		        	}

		            //Criar o objeto Not_Fal com os dados informados
		            Not_Fal notfal = new Not_Fal();
		            notfal.setRGM_NotFal(Integer.parseInt(textRGM2.getText()));
		            notfal.setDis_NotFal((String) cmbDis.getSelectedItem());
		            notfal.setSem_NotFal((String) cmbSem.getSelectedItem());
		            notfal.setNot_NotFal((String) cmbNot.getSelectedItem());
		            notfal.setFal_NotFal(textFal.getText());

		            //Salvar os dados na tabela "notefaltb"
		            Not_FalDao dao = new Not_FalDao();
		            dao.salvar(notfal);

		            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

		        } catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + ex.getMessage());
		        }
		    }
		});

		
		mntmAlterar2 = new JMenuItem("Alterar");
		mntmAlterar2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmAlterar2);
		mntmAlterar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        // Abrir a conexão
			        Not_FalDao notfaldao = new Not_FalDao();
			        Not_Fal notfal = new Not_Fal();
			        AlunoDao dao = new AlunoDao();
			        notfal.setRGM_NotFal(Integer.parseInt(textRGM2.getText()));
			        notfal.setDis_NotFal((String) cmbDis.getSelectedItem());
			        notfal.setSem_NotFal((String) cmbSem.getSelectedItem());
			        notfal.setNot_NotFal((String) cmbNot.getSelectedItem());
			        notfal.setFal_NotFal(textFal.getText());
			        notfaldao.alterar(notfal);
			        JOptionPane.showMessageDialog(null, "Notas e faltas alteradas com sucesso!");
			    } catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Erro ao alterar notas e faltas: " + ex.getMessage());
			    }
			}

		});

		
		mntmExcluir2 = new JMenuItem("Excluir");
		mnNewMenu_1.add(mntmExcluir2);
		mntmExcluir2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int rgm = Integer.parseInt(textRGM2.getText());
		            Not_FalDao notfalDao = new Not_FalDao();
		            notfalDao.excluir(rgm);
		            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
		        } catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: " + ex.getMessage());
		        }
		    }
		});

		mntmConsultar2 = new JMenuItem("Consultar");
		mnNewMenu_1.add(mntmConsultar2);
		mntmConsultar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        //Abrir a conexão
			        AlunoDao dao = new AlunoDao();
			        Aluno aluno = dao.consultar(Integer.parseInt(textRGM2.getText()));
			        Not_FalDao notfalDao = new Not_FalDao();
			        Not_Fal notfal = notfalDao.consultar(aluno.getRGM_Aluno());
			        //setar os dados na tela
					lbl_Nome.setText(aluno.getNome_Aluno());
					lbl_Curso.setText(aluno.getCur_Aluno());
					if (notfal != null) {
					    cmbDis.setSelectedItem(notfal.getDis_NotFal());
					    cmbSem.setSelectedItem(notfal.getSem_NotFal());
					    cmbNot.setSelectedItem(notfal.getNot_NotFal());
					    textFal.setText(notfal.getFal_NotFal());
					} else {
					    cmbDis.setSelectedItem(null);
					    cmbSem.setSelectedItem(null);
					    cmbNot.setSelectedItem(null);
					    textFal.setText(null);
					}
					
					JOptionPane.showMessageDialog(null, "Aluno encontrado com sucesso!");
			    } catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage());
			    }
			}

		});
		
		JMenu mnNewMenu_2 = new JMenu("Ajuda");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sobre");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//------------------
				JOptionPane.showMessageDialog(null, "Mensagem do Menu");
				//------------------
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 51, 542, 323);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RGM");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 35, 21);
		panel.add(lblNewLabel);
		
		textRGM = new JTextField();
		textRGM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textRGM.setBounds(55, 7, 129, 35);
		panel.add(textRGM);
		textRGM.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(194, 11, 43, 21);
		panel.add(lblNewLabel_1);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNome.setBounds(258, 7, 248, 35);
		panel.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data de Nascimento");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 65, 151, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(325, 65, 28, 21);
		panel.add(lblNewLabel_1_1);
		
		
		formattedCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		formattedCPF.setText("   .   .   -");
		formattedCPF.setColumns(11);
		formattedCPF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		formattedCPF.setBounds(364, 61, 142, 35);
		panel.add(formattedCPF);
		
		formattedCel = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		formattedCel.setColumns(11);
		formattedCel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		formattedCel.setBounds(364, 240, 142, 35);
		panel.add(formattedCel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Celular");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(301, 244, 51, 21);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("UF");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_2.setBounds(211, 244, 20, 21);
		panel.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Município");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_2_1.setBounds(8, 244, 69, 21);
		panel.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("End.");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_3.setBounds(12, 180, 33, 21);
		panel.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Email");
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_3_1.setBounds(10, 126, 41, 21);
		panel.add(lblNewLabel_1_1_3_1);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEmail.setColumns(10);
		textEmail.setBounds(63, 122, 443, 35);
		panel.add(textEmail);
		
		textEnd = new JTextField();
		textEnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEnd.setColumns(10);
		textEnd.setBounds(63, 176, 443, 35);
		panel.add(textEnd);
		
		cmbUF = new JComboBox();
		cmbUF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmbUF.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		cmbUF.setBounds(241, 240, 50, 35);
		panel.add(cmbUF);
		
		textMuni = new JTextField();
		textMuni.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textMuni.setColumns(10);
		textMuni.setBounds(87, 240, 114, 35);
		panel.add(textMuni);
			
		
		formattedData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		formattedData.setColumns(10);
		formattedData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		formattedData.setBounds(171, 61, 120, 35);
		panel.add(formattedData);
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Curso", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCurso.setBounds(10, 11, 42, 21);
		panel_1.add(lblCurso);
		
		cmbCurso = new JComboBox();
		cmbCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmbCurso.setModel(new DefaultComboBoxModel(new String[] {"Analise e Desenvolvimento de Sistemas", "Ciências da Computação", "Engenharia da Computação"}));
		cmbCurso.setBounds(85, 7, 417, 35);
		panel_1.add(cmbCurso);
		
		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCampus.setBounds(10, 66, 59, 21);
		panel_1.add(lblCampus);
		
		cmbCampus = new JComboBox();
		cmbCampus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmbCampus.setModel(new DefaultComboBoxModel(new String[] {"Tatuapé", "Pinheiros"}));
		cmbCampus.setBounds(85, 62, 417, 35);
		panel_1.add(cmbCampus);
		
		JLabel lblPerodo = new JLabel("Período");
		lblPerodo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPerodo.setBounds(10, 134, 59, 21);
		panel_1.add(lblPerodo);
		
		rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnMatutino.setBounds(96, 136, 109, 23);
		panel_1.add(rdbtnMatutino);
		
		
		rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnVespertino.setBounds(229, 136, 109, 23);
		panel_1.add(rdbtnVespertino);
		
		
		rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnNoturno.setBounds(368, 136, 109, 23);
		panel_1.add(rdbtnNoturno);
		

		grupoBotoes = new ButtonGroup();
		grupoBotoes.add(rdbtnMatutino);
		grupoBotoes.add(rdbtnVespertino);
		grupoBotoes.add(rdbtnNoturno);
		
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------------------
				System.exit(0);
				//------------------
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(15, 202, 89, 59);
		panel_1.add(btnSair);
		
		JButton btnConsultar = new JButton("Consultar");	
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConsultar.setBounds(119, 202, 89, 59);
		panel_1.add(btnConsultar);	
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            //Abrir a conexão
			            AlunoDao dao = new AlunoDao();
			            Aluno aluno = dao.consultar(Integer.parseInt(textRGM.getText()));
			            if(aluno != null) {
			                //setar os dados na tela
			                textNome.setText(aluno.getNome_Aluno());
			                formattedData.setText(new SimpleDateFormat("dd/MM/yyyy").format(aluno.getDat_Nas_Aluno()));
			                formattedCPF.setValue(aluno.getCPF_Aluno());
			                textEmail.setText(aluno.getEmail_Aluno());
			                textEnd.setText(aluno.getEnd_Aluno());
			                textMuni.setText(aluno.getMuni_Aluno());
			                cmbUF.setSelectedItem(aluno.getUF_Aluno());
			                formattedCel.setValue(aluno.getCel_Aluno());
			                cmbCurso.setSelectedItem(aluno.getCur_Aluno());
			                cmbCampus.setSelectedItem(aluno.getCam_Aluno());
			                String cpf = String.format("%011d", aluno.getCPF_Aluno());
			                formattedCPF.setValue(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9));

			                // Ajusta a máscara do campo celular
			                String cel = String.format("%011d", aluno.getCel_Aluno());
			                formattedCel.setValue("(" + cel.substring(0, 2) + ")" + cel.substring(2, 7) + "-" + cel.substring(7));

			                if(aluno.getPer_Aluno() != null) {
			                    switch (aluno.getPer_Aluno()) {
			                        case Matutino:
			                            rdbtnMatutino.setSelected(true);
			                            break;
			                        case Vespertino:
			                            rdbtnVespertino.setSelected(true);
			                            break;
			                        case Noturno:
			                            rdbtnNoturno.setSelected(true);
			                            break;
			                
			                    }
			                }
			                JOptionPane.showMessageDialog(null, "Aluno encontrado com sucesso!");
			            } else {
			                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
			            }
			        } catch(Exception ex) {
			            JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage());
			        }
			    }	
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAlterar.setBounds(223, 202, 89, 59);
		panel_1.add(btnAlterar);
		btnAlterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Criando o objeto aluno para pegar os dados da tela
		            Aluno aluno = new Aluno();
		            
		            // Preenchendo os dados do aluno com os campos da tela
		            aluno.setRGM_Aluno(Integer.parseInt(textRGM.getText()));
		            aluno.setNome_Aluno(textNome.getText());
		            
		            String dataString = formattedData.getText();
		            dataString = dataString.replaceAll("/", "");
		            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		            Date dataNascimento = format.parse(dataString);
		            aluno.setDat_Nas_Aluno(dataNascimento);
		            
		            String cpf = formattedCPF.getValue().toString().replaceAll("[^0-9]", "");
		            aluno.setCPF_Aluno(Long.parseLong(cpf));
		            
		            aluno.setEmail_Aluno(textEmail.getText());
		            aluno.setEnd_Aluno(textEnd.getText());
		            aluno.setMuni_Aluno(textMuni.getText());
		            aluno.setUF_Aluno((String) cmbUF.getSelectedItem());
		            
		            Object phoneValue = formattedCel.getValue();
		            String phone = "";
		            if (phoneValue != null) {
		                phone = phoneValue.toString().replaceAll("[^0-9]", "");
		            }
		            aluno.setCel_Aluno(Long.parseLong(phone));
		            aluno.setCur_Aluno((String) cmbCurso.getSelectedItem());
		            aluno.setCam_Aluno((String) cmbCampus.getSelectedItem());
		            
		            if (rdbtnMatutino.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Matutino);
		            } else if (rdbtnVespertino.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Vespertino);
		            } else if (rdbtnNoturno.isSelected()) {
		                aluno.setPer_Aluno(PeriodoAluno.Noturno);
		            }
		            
		            // Abrir a conexão
		            AlunoDao dao = new AlunoDao();
		            // Alterar os dados do aluno no banco de dados
		            dao.alterar(aluno);
		            JOptionPane.showMessageDialog(null, "Dados do aluno alterados com sucesso!");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do aluno: " + ex.getMessage());
		        }
		    }
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            // Obtenha o RGM_Aluno selecionado
		            int RGM_Aluno = Integer.parseInt(textRGM.getText());
		            
		            // Crie uma instância de AlunoDao
		            AlunoDao dao = new AlunoDao();
		            
		            // Chame o método de exclusão
		            dao.excluir(RGM_Aluno);
		            
		            // Exiba uma mensagem de sucesso
		            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
		            
		            // Limpe os campos de entrada
		            // ...
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno: " + ex.getMessage());
		        }
		    }
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(327, 202, 89, 59);
		panel_1.add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(431, 202, 89, 59);
		panel_1.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//---------------------------------------	
				try {				
					// Criando o objeto aluno para pegar os dados da tela
					
					aluno.setRGM_Aluno(Integer.parseInt(textRGM.getText()));
					aluno.setNome_Aluno(textNome.getText());
					
					String dataString = formattedData.getText();
				    dataString = dataString.replaceAll("/", "");
				    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
				    Date dataNascimento = format.parse(dataString);
				    aluno.setDat_Nas_Aluno(dataNascimento); 	
				    			    
				    String cpf = formattedCPF.getValue().toString().replaceAll("[^0-9]", "");
				    aluno.setCPF_Aluno(Long.parseLong(cpf));
					aluno.setEmail_Aluno(textEmail.getText());
					aluno.setEnd_Aluno(textEnd.getText());
					aluno.setMuni_Aluno(textMuni.getText());
					aluno.setUF_Aluno((String) cmbUF.getSelectedItem());
				
					Object phoneValue = formattedCel.getValue();
					String phone = "";
					if (phoneValue != null) {
					    phone = phoneValue.toString().replaceAll("[^0-9]", "");
					}
					aluno.setCel_Aluno(Long.parseLong(phone));
					aluno.setCur_Aluno((String) cmbCurso.getSelectedItem());
					aluno.setCam_Aluno((String) cmbCampus.getSelectedItem());
					aluno.setPer_Aluno(PeriodoAluno.Matutino);
					if (rdbtnMatutino.isSelected()) {
					    aluno.setPer_Aluno(PeriodoAluno.Matutino);
					} else if (rdbtnVespertino.isSelected()) {
					    aluno.setPer_Aluno(PeriodoAluno.Vespertino);
					} else if (rdbtnNoturno.isSelected()) {
					    aluno.setPer_Aluno(PeriodoAluno.Noturno);
					}		
					// Abrir a conexão
					AlunoDao dao = new AlunoDao();
					// Salvar
					dao.salvar(aluno);
					JOptionPane.showMessageDialog(null, "Aluno matrículado com Sucesso!!!");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao matrícular!!!"+e1.getMessage());
				} 
				//---------------------------------------
			}
		});
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("RGM");
		lblNewLabel_3.setBounds(10, 11, 35, 21);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_3);
		
		textRGM2 = new JTextField();
		textRGM2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textRGM2.setBounds(54, 7, 129, 35);
		textRGM2.setColumns(10);
		panel_2.add(textRGM2);
		
		lbl_Nome = new JLabel("Nome do Aluno");
		lbl_Nome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Nome.setBounds(195, 5, 325, 38);
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lbl_Nome);
		
		lbl_Curso = new JLabel("Curso do Aluno");
		lbl_Curso.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Curso.setBounds(10, 49, 510, 38);
		lbl_Curso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lbl_Curso);
		
		JLabel lblDis = new JLabel("Disciplina");
		lblDis.setBounds(12, 113, 71, 21);
		lblDis.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lblDis);
		
		cmbDis = new JComboBox();
		cmbDis.setBounds(94, 110, 420, 35);
		cmbDis.setModel(new DefaultComboBoxModel(new String[] {"Programação Orientada a Objetos", "Estrutura de Dados I", "Matemática Aplicada"}));
		cmbDis.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(cmbDis);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Semestre");
		lblNewLabel_1_1_2_2.setBounds(15, 165, 70, 21);
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_1_1_2_2);
		
		cmbSem = new JComboBox();
		cmbSem.setBounds(94, 158, 77, 35);
		cmbSem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmbSem.setModel(new DefaultComboBoxModel(new String[] {"2022-1", "2022-2", "2023-3", "2023-4"}));
		panel_2.add(cmbSem);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Nota");
		lblNewLabel_1_1_2_2_1.setBounds(189, 164, 40, 21);
		lblNewLabel_1_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_1_1_2_2_1);
		
		cmbNot = new JComboBox();
		cmbNot.setBounds(233, 158, 77, 35);
		cmbNot.setModel(new DefaultComboBoxModel(new String[] {"0,00", "0,25", "0,50", "0,75", "1,00", "1,25", "1,50", "1,75", "2,00", "2,25", "2,50", "2,75", "3,00", "3,25", "3,50", "3,75", "4,00", "4,25", "4,50", "4,75", "5,00"}));
		cmbNot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(cmbNot);
		
		JLabel lblNewLabel_3_1 = new JLabel("Faltas");
		lblNewLabel_3_1.setBounds(333, 164, 45, 21);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_3_1);
		
		textFal = new JTextField();
		textFal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFal.setBounds(392, 156, 123, 35);
		textFal.setColumns(10);
		panel_2.add(textFal);
		
		JButton btnSair2 = new JButton("Sair");
		btnSair2.setBounds(431, 216, 89, 59);
		btnSair2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnSair2);
		btnSair2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				//------------------
				System.exit(0);
				//------------------
			}
		});
		
		JButton btnAlterar2 = new JButton("Alterar");
		btnAlterar2.setBounds(119, 216, 89, 59);
		btnAlterar2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnAlterar2);
		btnAlterar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        // Abrir a conexão
			        Not_FalDao notfaldao = new Not_FalDao();
			        Not_Fal notfal = new Not_Fal();
			        AlunoDao dao = new AlunoDao();
			        notfal.setRGM_NotFal(Integer.parseInt(textRGM2.getText()));
			        notfal.setDis_NotFal((String) cmbDis.getSelectedItem());
			        notfal.setSem_NotFal((String) cmbSem.getSelectedItem());
			        notfal.setNot_NotFal((String) cmbNot.getSelectedItem());
			        notfal.setFal_NotFal(textFal.getText());
			        notfaldao.alterar(notfal);
			        JOptionPane.showMessageDialog(null, "Notas e faltas alteradas com sucesso!");
			    } catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Erro ao alterar notas e faltas: " + ex.getMessage());
			    }
			}

		});
		
		JButton btnConsultar2 = new JButton("Consultar");
		btnConsultar2.setBounds(15, 216, 89, 59);
		btnConsultar2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnConsultar2);
		btnConsultar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        //Abrir a conexão
			        AlunoDao dao = new AlunoDao();
			        Aluno aluno = dao.consultar(Integer.parseInt(textRGM2.getText()));
			        Not_FalDao notfalDao = new Not_FalDao();
			        Not_Fal notfal = notfalDao.consultar(aluno.getRGM_Aluno());
			        //setar os dados na tela
					lbl_Nome.setText(aluno.getNome_Aluno());
					lbl_Curso.setText(aluno.getCur_Aluno());
					if (notfal != null) {
					    cmbDis.setSelectedItem(notfal.getDis_NotFal());
					    cmbSem.setSelectedItem(notfal.getSem_NotFal());
					    cmbNot.setSelectedItem(notfal.getNot_NotFal());
					    textFal.setText(notfal.getFal_NotFal());
					} else {
					    cmbDis.setSelectedItem(null);
					    cmbSem.setSelectedItem(null);
					    cmbNot.setSelectedItem(null);
					    textFal.setText(null);
					}
					
					JOptionPane.showMessageDialog(null, "Aluno encontrado com sucesso!");
			    } catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage());
			    }
			}
		});
		
		
		JButton btnSalvar2 = new JButton("Salvar");
		btnSalvar2.setBounds(327, 216, 89, 59);
		btnSalvar2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnSalvar2);
		btnSalvar2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            //Verificar se todos os campos foram preenchidos
		        	if(textRGM2.getText().isEmpty() || cmbDis.getSelectedItem() == null || cmbSem.getSelectedItem() == null || cmbNot.getSelectedItem() == null || textFal.getText().isEmpty()) {
		        	    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		        	    return;
		        	}

		            //Criar o objeto Not_Fal com os dados informados
		            Not_Fal notfal = new Not_Fal();
		            notfal.setRGM_NotFal(Integer.parseInt(textRGM2.getText()));
		            notfal.setDis_NotFal((String) cmbDis.getSelectedItem());
		            notfal.setSem_NotFal((String) cmbSem.getSelectedItem());
		            notfal.setNot_NotFal((String) cmbNot.getSelectedItem());
		            notfal.setFal_NotFal(textFal.getText());

		            //Salvar os dados na tabela "notefaltb"
		            Not_FalDao dao = new Not_FalDao();
		            dao.salvar(notfal);

		            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

		        } catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + ex.getMessage());
		        }
		    }
		});
		
		JButton btnExcluir2 = new JButton("Excluir");
		btnExcluir2.setBounds(223, 216, 89, 59);
		btnExcluir2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnExcluir2);
		btnExcluir2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int rgm = Integer.parseInt(textRGM2.getText());
		            Not_FalDao notfalDao = new Not_FalDao();
		            notfalDao.excluir(rgm);
		            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
		        } catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: " + ex.getMessage());
		        }
		    }
		});
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Boletim", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3_2 = new JLabel("RGM");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2.setBounds(15, 30, 35, 21);
		panel_3.add(lblNewLabel_3_2);
		
		JLabel lbl_Nome2 = new JLabel("Nome do Aluno");
		lbl_Nome2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_Nome2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Nome2.setBounds(75, 72, 446, 38);
		panel_3.add(lbl_Nome2);
		
		JLabel lbl_Curso2 = new JLabel("Curso do Aluno");
		lbl_Curso2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_Curso2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Curso2.setBounds(76, 125, 445, 38);
		panel_3.add(lbl_Curso2);
		
		JLabel lbl_Dis2 = new JLabel("Disciplina do Aluno");
		lbl_Dis2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_Dis2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Dis2.setBounds(116, 181, 405, 38);
		panel_3.add(lbl_Dis2);
		
		JLabel lbl_Not2 = new JLabel("Notas do Aluno");
		lbl_Not2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_Not2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Not2.setBounds(67, 231, 117, 38);
		panel_3.add(lbl_Not2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Nome");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2_1.setBounds(15, 79, 43, 21);
		panel_3.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Curso");
		lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2_2.setBounds(15, 133, 42, 21);
		panel_3.add(lblNewLabel_3_2_2);
		
		JLabel lblNewLabel_3_2_3 = new JLabel("Disciplina");
		lblNewLabel_3_2_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2_3.setBounds(15, 186, 79, 21);
		panel_3.add(lblNewLabel_3_2_3);
		
		JLabel lblNewLabel_3_2_4 = new JLabel("Notas");
		lblNewLabel_3_2_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2_4.setBounds(15, 239, 49, 21);
		panel_3.add(lblNewLabel_3_2_4);
		
		textRGM3 = new JTextField();
		textRGM3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textRGM3.setColumns(10);
		textRGM3.setBounds(67, 23, 315, 35);
		panel_3.add(textRGM3);
		
		JButton btnConsultar3 = new JButton("Consultar");
		btnConsultar3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConsultar3.setBounds(399, 19, 122, 41);
		panel_3.add(btnConsultar3);
		btnConsultar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        //Abrir a conexão
			        AlunoDao dao = new AlunoDao();
			        Aluno aluno = dao.consultar(Integer.parseInt(textRGM3.getText()));
			        Not_FalDao notfalDao = new Not_FalDao();
			        Not_Fal notfal = notfalDao.consultar(aluno.getRGM_Aluno());
			        //setar os dados na tela
					lbl_Nome2.setText(aluno.getNome_Aluno());
					lbl_Curso2.setText(aluno.getCur_Aluno());
					lbl_Dis2.setText(notfal.getDis_NotFal());
					lbl_Not2.setText(notfal.getNot_NotFal());
					lbl_Fal2.setText(notfal.getFal_NotFal());

					
					JOptionPane.showMessageDialog(null, "Aluno encontrado com sucesso!");
			    } catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + ex.getMessage());
			    }
			}
		});
		
		lbl_Fal2 = new JLabel("Faltas do Aluno");
		lbl_Fal2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_Fal2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_Fal2.setBounds(390, 231, 131, 38);
		panel_3.add(lbl_Fal2);
		
		JLabel lblNewLabel_3_2_4_1 = new JLabel("Faltas");
		lblNewLabel_3_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3_2_4_1.setBounds(336, 239, 49, 21);
		panel_3.add(lblNewLabel_3_2_4_1);
	}
}
