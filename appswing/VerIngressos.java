package appswing;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Ingresso;
import regras_negocio.Fachada;

public class VerIngressos extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JScrollPane scrollPane;
    
    private int idEvento;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal window = new TelaPrincipal();
                    window.frame.setVisible(true);
                    int idEvento = 1;
                    VerIngressos dialog = new VerIngressos(idEvento);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    public VerIngressos(int id) {
        this.idEvento = id;
        
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
			carregarIngressosEvento(idEvento);
		} catch (Exception e2) {
			e2.getMessage();		}
    }

    private void carregarIngressosEvento(int codigoEvento) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            ArrayList<Ingresso> ingressos = Fachada.listarIngressosEvento(codigoEvento);

            if (ingressos.isEmpty()) {
                throw new Exception("O evento não possui ingressos.");
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