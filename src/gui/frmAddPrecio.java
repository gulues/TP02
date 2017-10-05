package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class frmAddPrecio extends JFrame {

	private static final long serialVersionUID = 6661227783988811244L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtHabitantes;
	private JTextField txtLat;
	private JTextField txtLon;

	public frmAddPrecio() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 604);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 75, 20);
		contentPane.add(lblNombre);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(10, 46, 75, 20);
		contentPane.add(lblProvincia);

		JLabel lblCantidadDeHabitantes = new JLabel("Cantidad de Habitantes:");
		lblCantidadDeHabitantes.setBounds(10, 85, 117, 20);
		contentPane.add(lblCantidadDeHabitantes);

		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setBounds(10, 131, 75, 20);
		contentPane.add(lblLatitud);

		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(149, 131, 75, 20);
		contentPane.add(lblLongitud);

		JButton btnOk = new JButton("Agregar Ciudad");
		btnOk.setBounds(108, 204, 117, 56);
		contentPane.add(btnOk);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(235, 204, 117, 56);
		contentPane.add(btnCerrar);

		txtNombre = new JTextField();
		txtNombre.setBounds(139, 11, 200, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		@SuppressWarnings("rawtypes")
		JComboBox cmbPcia = new JComboBox();
		cmbPcia.setBounds(139, 46, 200, 20);
		contentPane.add(cmbPcia);

		txtHabitantes = new JTextField();
		txtHabitantes.setColumns(10);
		txtHabitantes.setBounds(139, 85, 200, 20);
		contentPane.add(txtHabitantes);

		txtLat = new JTextField();
		txtLat.setColumns(10);
		txtLat.setBounds(63, 131, 57, 20);
		contentPane.add(txtLat);

		txtLon = new JTextField();
		txtLon.setColumns(10);
		txtLon.setBounds(204, 131, 57, 20);
		contentPane.add(txtLon);
	}
}
