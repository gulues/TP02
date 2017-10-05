package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ciudades.ciudad;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class frmAddPrecio extends JFrame {

	private static final long serialVersionUID = 6661227783988811244L;
	private JPanel contentPane;
	private JTextField txtCosto;
	private JTextField txtLat;
	private JTextField txtLon;

	public frmAddPrecio(ArrayList<ciudad>lista) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Conexi\u00F3n 1");
		lblNombre.setBounds(10, 11, 117, 20);
		contentPane.add(lblNombre);

		JLabel lblCantidadDeHabitantes = new JLabel("Costo de conexi\u00F3n por Km:");
		lblCantidadDeHabitantes.setBounds(10, 85, 160, 20);
		contentPane.add(lblCantidadDeHabitantes);

		JLabel lblLatitud = new JLabel("Costo fijo por Pcias Distintas:");
		lblLatitud.setVerticalTextPosition(SwingConstants.TOP);
		lblLatitud.setVerticalAlignment(SwingConstants.TOP);
		lblLatitud.setBounds(10, 131, 147, 17);
		contentPane.add(lblLatitud);

		JButton btnOk = new JButton("Agregar Ciudad");
		btnOk.setBounds(76, 248, 117, 56);
		contentPane.add(btnOk);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnCerrar.setBounds(214, 248, 117, 56);
		contentPane.add(btnCerrar);

		JComboBox<String> cmbConexion2 = new JComboBox<String>();
		cmbConexion2.setBounds(180, 42, 200, 20);
		contentPane.add(cmbConexion2);

		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(180, 85, 86, 20);
		contentPane.add(txtCosto);

		txtLat = new JTextField();
		txtLat.setColumns(10);
		txtLat.setBounds(180, 173, 83, 20);
		contentPane.add(txtLat);

		txtLon = new JTextField();
		txtLon.setColumns(10);
		txtLon.setBounds(180, 128, 86, 20);
		contentPane.add(txtLon);
		
		
		JComboBox<String> cmbConexion1 = new JComboBox<String>();
		for (ciudad ciudad : lista) {
			cmbConexion1.addItem(ciudad.get_nombre());
			cmbConexion2.addItem(ciudad.get_nombre());
		}
		
		cmbConexion1.setBounds(180, 11, 200, 20);
		contentPane.add(cmbConexion1);
		
		JLabel lblConexin = new JLabel("Conexi\u00F3n 2");
		lblConexin.setBounds(10, 42, 117, 20);
		contentPane.add(lblConexin);
		
		JLabel lblNewLabel = new JLabel("<html>Costo Fijo por conexi\u00F3n <br>de mas de 200 km:</html>");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 176, 166, 39);
		contentPane.add(lblNewLabel);
	}
}
