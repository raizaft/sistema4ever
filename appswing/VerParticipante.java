package appswing;

import modelo.Ingresso;
import regras_negocio.Fachada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VerParticipante extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
    private JScrollPane scrollPane;
	private String cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                String cpf = "";
                VerParticipante dialog = new VerParticipante(cpf);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e11) {
                e11.printStackTrace();
            }
        });
    }

	/**
	 * Create the dialog.
	 */
	public VerParticipante(String id) {
		this.cpf = id;
		
		setTitle("Ingressos");
        setBounds(100, 100, 450, 285);
        getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 192);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código", "Telefone" }));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        okButton.setActionCommand("OK");
        okButton.setBounds(335, 214, 89, 23);
        getContentPane().add(okButton);

        try {
			carregarIngressosParticipante(cpf);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    }

    private void carregarIngressosParticipante(String cpf) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            ArrayList<Ingresso> ingressos = Fachada.listarIngressosParticipante(cpf);

            if (ingressos.isEmpty()) {
                throw new Exception("O participante não possui ingressos.");
            } else {
                for (Ingresso ingresso : ingressos) {
                    model.addRow(new Object[] { ingresso.getCodigo(), ingresso.getTelefone() });
                }
            }
        } catch (Exception even) {
            even.getMessage();
        }
    }

}
