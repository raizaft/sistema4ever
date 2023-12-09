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
import regras_negocio.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel label;

    private JScrollPane scrollPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    private JLabel label_4;

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
		// Adicione os componentes relevantes para a tela de participantes aqui...

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
				listagem();
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
		label.setBounds(480, 11, 46, 14);
		telaEventos.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(480, 74, 123, 20);
		telaEventos.add(textField_1);
		
		label_1 = new JLabel("Descrição");
		label_1.setBounds(480, 58, 46, 14);
		telaEventos.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(480, 121, 123, 20);
		telaEventos.add(textField_2);
		
		label_2 = new JLabel("Capacidade");
		label_2.setBounds(480, 105, 86, 14);
		telaEventos.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(480, 168, 123, 20);
		telaEventos.add(textField_3);
		
		label_3 = new JLabel("Preço");
		label_3.setBounds(480, 152, 86, 14);
		telaEventos.add(label_3);
        
        button_4 = new JButton("Criar Evento");
        button_4.setBounds(480, 199, 123, 23);
        telaEventos.add(button_4);

        button_5 = new JButton("Excluir Evento");
        button_5.setBounds(480, 233, 123, 23);
        telaEventos.add(button_5);

        button_6 = new JButton("Ver Ingressos");
        button_6.setBounds(347, 233, 123, 23);
        telaEventos.add(button_6);

        label_4 = new JLabel("tem um label aqui");
        label_4.setBounds(10, 233, 194, 23);
        telaEventos.add(label_4);
    }
	
	public void listagem() {
		try {
			List<Evento> lista = Fachada.listarEventos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
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
}
