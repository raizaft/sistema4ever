package appswing;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Evento;
import regras_negocio.Fachada;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TelaEventos {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JLabel label;
	private JTextField textField_1;
	private JLabel label_1;
	private JTextField textField_2;
	private JLabel label_2;
	private JTextField textField_3;
	private JLabel label_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEventos window = new TelaEventos();
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
	public TelaEventos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Eventos");
		frame.setBounds(100, 100, 630, 305);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 460, 211);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Data", "Capacidade", "Preço", "Vendidos", "Total", "Esgotado"
			}
		));
		
		textField = new JTextField();
		textField.setBounds(480, 27, 123, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label = new JLabel("Data");
		label.setBounds(480, 11, 46, 14);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(480, 74, 123, 20);
		frame.getContentPane().add(textField_1);
		
		label_1 = new JLabel("Descrição");
		label_1.setBounds(480, 58, 46, 14);
		frame.getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(480, 121, 123, 20);
		frame.getContentPane().add(textField_2);
		
		label_2 = new JLabel("Capacidade");
		label_2.setBounds(480, 105, 86, 14);
		frame.getContentPane().add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(480, 168, 123, 20);
		frame.getContentPane().add(textField_3);
		
		label_3 = new JLabel("Preço");
		label_3.setBounds(480, 152, 86, 14);
		frame.getContentPane().add(label_3);
		
		button = new JButton("Criar Evento");
		button.setBounds(480, 199, 123, 23);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Excluir Evento");
		button_1.setBounds(480, 233, 123, 23);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Ver Ingressos");
		button_2.setBounds(347, 233, 123, 23);
		frame.getContentPane().add(button_2);
		
		label_4 = new JLabel("tem um label aqui");
		label_4.setBounds(10, 233, 194, 23);
		frame.getContentPane().add(label_4);
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
