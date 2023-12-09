package appswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TelaPrincipal {

	private JFrame frame;
	private JLabel label;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 351, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setIcon(new ImageIcon("D:\\IFPB\\P3\\POO\\logo\\logo2.png"));
		label.setBounds(0, 26, 335, 235);
		frame.getContentPane().add(label);
		
		button = new JButton("Eventos");
		button.setBounds(0, 0, 113, 28);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Participantes");
		button_1.setBounds(111, 0, 113, 28);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Ingressos");
		button_2.setBounds(221, 0, 114, 28);
		frame.getContentPane().add(button_2);
	}
}
