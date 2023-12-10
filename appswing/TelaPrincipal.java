package appswing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Evento;
import modelo.Participante;
import modelo.Convidado;
import regras_negocio.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaPrincipal {

	private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel label;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    
    private JTable table;
    private JTable table2;
    private JTable table3;
    
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;
    
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    private JButton button_8;
    private JButton button_9;
    private JButton button_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("4EVER");
		frame.setResizable(false);
		frame.setBounds(100, 100, 630, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		frame.getContentPane().add(cardPanel);

		JPanel telaHome = new JPanel();
		telaHome.setLayout(null);
		label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\IFPB\\P3\\POO\\logo\\logo3.png"));
		label.setBounds(0, 0, 612, 268);
		telaHome.add(label);

		JPanel telaEventos = new JPanel();
        telaEventos.setLayout(null);
        setupTelaEventos(telaEventos);

		JPanel telaParticipantes = new JPanel();
		telaParticipantes.setLayout(null);
        setupTelaParticipantes(telaParticipantes);

		JPanel telaIngressos = new JPanel();
		telaIngressos.setLayout(null);
		// Adicione os componentes relevantes para a tela de ingressos aqui...

		cardPanel.add(telaHome, "telaHome");
		cardPanel.add(telaEventos, "telaEventos");
		cardPanel.add(telaParticipantes, "telaParticipantes");
		cardPanel.add(telaIngressos, "telaIngressos");

		button_3 = new JButton("             Home             ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "telaHome");
			}
		});

		button = new JButton("             Eventos             ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "telaEventos");
			}
		});

		button_1 = new JButton("        Participantes        ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "telaParticipantes");
			}
		});

		button_2 = new JButton("          Ingressos          ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "telaIngressos");
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 612, 25);
		menuBar.add(button_3);
		menuBar.add(button);
		menuBar.add(button_1);
		menuBar.add(button_2);

		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	private void setupTelaEventos(JPanel telaEventos) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagemEventos();
			}
		});
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 460, 211);
        telaEventos.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Data", "Capacidade", "Preço",
                "Vendidos", "Total", "Esgotado" }));
        
        textField = new JTextField();
		textField.setBounds(480, 27, 123, 20);
		telaEventos.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("Data");
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(480, 11, 46, 14);
		telaEventos.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(480, 74, 123, 20);
		telaEventos.add(textField_1);
		
		label_1 = new JLabel("Descrição");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_1.setBounds(480, 58, 46, 14);
		telaEventos.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(480, 121, 123, 20);
		telaEventos.add(textField_2);
		
		label_2 = new JLabel("Capacidade");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_2.setBounds(480, 105, 86, 14);
		telaEventos.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(480, 168, 123, 20);
		telaEventos.add(textField_3);
		
		label_3 = new JLabel("Preço");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_3.setBounds(480, 152, 86, 14);
		telaEventos.add(label_3);
        
        button_4 = new JButton("Criar Evento");
        button_4.setBounds(480, 199, 123, 23);
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {
            		if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
            				|| textField_2.getText().isEmpty()
            				|| textField_3.getText().isEmpty()) {
                		throw new Exception ("Preencha todos os campos.");
                	}
            	} catch (Exception e5) {
            		label_4.setText(e5.getMessage());
            	}

                String data = textField.getText();
                String descricao = textField_1.getText();
                int capacidade = Integer.parseInt(textField_2.getText());
                double preco = Double.parseDouble(textField_3.getText());

                try {
					Fachada.criarEvento(data, descricao, capacidade, preco);
				} catch (Exception e10) {
					label_4.setText(e10.getMessage());
				}

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");

                listagemEventos();
            }
        });
        telaEventos.add(button_4);

        button_5 = new JButton("Excluir");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    if (table.getSelectedRow() >= 0) {
                        Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
                        Fachada.apagarEvento(id);
                    } else {
                        label_4.setText("Selecione um evento.");
                    }
                } catch (Exception erro) {
                    label_4.setText(erro.getMessage());
                }
        		listagemEventos();
        	}
        });
        button_5.setBounds(480, 233, 123, 23);
        telaEventos.add(button_5);

        button_6 = new JButton("Ver Ingressos");
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                        Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
                        VerIngressos tela = new VerIngressos(id);
                        tela.setVisible(true);
                    } else {
                        label_4.setText("Selecione um evento.");
                    }
                } catch (Exception erro) {
                    label_4.setText(erro.getMessage());
                }
            }
        });
        button_6.setBounds(347, 233, 123, 23);
        telaEventos.add(button_6);

        label_4 = new JLabel("");
        label_4.setBounds(10, 233, 324, 23);
        telaEventos.add(label_4);
    }
	
	private void setupTelaParticipantes(JPanel telaParticipantes) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagemParticipantes();
			}
		});
		scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 11, 460, 211);
        telaParticipantes.add(scrollPane2);

        table2 = new JTable();
        scrollPane2.setViewportView(table2);
        table2.setModel(new DefaultTableModel(new Object[][] {}, new String[] 
        		{ "CPF", "Nascimento", "Idade", "Empresa"}));
        
        textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(480, 27, 123, 20);
		telaParticipantes.add(textField_4);
		
		label_5 = new JLabel("CPF");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_5.setBounds(480, 11, 46, 14);
		telaParticipantes.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(480, 74, 123, 20);
		telaParticipantes.add(textField_5);
		
		label_6 = new JLabel("Nascimento");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_6.setBounds(480, 58, 86, 14);
		telaParticipantes.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(480, 121, 123, 20);
		telaParticipantes.add(textField_6);
		
		label_7 = new JLabel("Empresa");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_7.setBounds(480, 105, 86, 14);
		telaParticipantes.add(label_7);
        
        button_7 = new JButton("Criar");
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String cpf = textField_4.getText();
                String nascimento = textField_5.getText();
                String empresa = textField_6.getText();

                try {
                	if (empresa == null || empresa.trim().isEmpty()) {
                		Fachada.criarParticipante(cpf, nascimento);
                	} else {
                		Fachada.criarConvidado(cpf, nascimento, empresa);
                	}
				} catch (Exception e1) {
					label_8.setText(e1.getMessage());
				}

                textField_4.setText("");
                textField_5.setText("");
                textField_6.setText("");

                listagemParticipantes();
            }
        });
        button_7.setBounds(480, 165, 123, 23);
        telaParticipantes.add(button_7);
        
        button_8 = new JButton("Limpar");
        button_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_4.setText(null);
                textField_5.setText(null);
                textField_6.setText(null);
            }
        });
        button_8.setBounds(480, 199, 123, 23);
        telaParticipantes.add(button_8);

        button_9 = new JButton("Excluir");
        button_9.setBounds(480, 233, 123, 23);
        button_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    if (table2.getSelectedRow() >= 0) {
                        String cpf = (String) table2.getValueAt(table2.getSelectedRow(), 0);
                        Fachada.apagarParticipante(cpf);
                    } else {
                        label_8.setText("Selecione um evento.");
                    }
                } catch (Exception erro) {
                    label_8.setText(erro.getMessage());
                }
        		listagemParticipantes();
        	}
        });
        telaParticipantes.add(button_9);
        
        button_10 = new JButton("Ver Participante");
        button_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table2.getSelectedRow() >= 0) {
                        String cpf = (String) table2.getValueAt(table2.getSelectedRow(), 0);
                        VerParticipante tela2 = new VerParticipante(cpf);
                        tela2.setVisible(true);
                    } else {
                        label_8.setText("Selecione um participante.");
                    }
                } catch (Exception erro) {
                    label_8.setText(erro.getMessage());
                }
            }
        });
        button_10.setBounds(335, 233, 135, 23);
        telaParticipantes.add(button_10);
        
        label_8 = new JLabel("");
        label_8.setBounds(10, 233, 315, 23);
        telaParticipantes.add(label_8);
	}
	
	public void listagemEventos() {
		try {
			List<Evento> lista = Fachada.listarEventos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			String lotado = "";
			
			for (Evento ev : lista) {
				if (ev.lotado() == false)
					lotado = "não";
				else
					lotado = "sim";
				model.addRow(new Object[]{
						ev.getId(),
						ev.getData(),
						ev.getCapacidade(),
						ev.getPreco(),
						ev.quantidadeIngressos(),
						ev.totalArrecadado(),
						lotado});
			}
			table.getColumnModel().getColumn(0).setMaxWidth(40);	
			table.getColumnModel().getColumn(1).setMaxWidth(80);
			table.getColumnModel().getColumn(2).setMaxWidth(80);
			table.getColumnModel().getColumn(3).setMaxWidth(60);
			table.getColumnModel().getColumn(4).setMaxWidth(70);
			table.getColumnModel().getColumn(5).setMaxWidth(50);
			table.getColumnModel().getColumn(6).setMaxWidth(80);
			
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		} catch(Exception erro){
			erro.getMessage();
		}
	}
	
	public void listagemParticipantes() {
		try {
			List<Participante> lista = Fachada.listarParticipantes();
			DefaultTableModel model = (DefaultTableModel) table2.getModel();
			model.setRowCount(0);
			
			for (Participante p : lista) {
				if (p instanceof Convidado c)
					model.addRow(new Object[]{
							p.getCpf(),
							p.getNascimento(),
							p.calcularIdade(),
							c.getEmpresa()
							});
				else
					model.addRow(new Object[]{
							p.getCpf(),
							p.getNascimento(),
							p.calcularIdade(),
							""
							});
			}
			
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

	        for (int i = 0; i < table2.getColumnCount(); i++) {
	            table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		} catch(Exception erro){
			erro.getMessage();
		}
	}
}