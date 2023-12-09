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

public class TelaEventos {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;

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
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 460, 201);
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
