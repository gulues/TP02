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
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmAddConexion extends JFrame {

	private static final long serialVersionUID = 6661227783988811244L;
	private JPanel contentPane;
	private JTextField txtCostoKm;
	private JTextField txtCostoPlus;
	private JTextField txtCostoPciasDif;
	private double km = 0;
	@SuppressWarnings("unused")
	private double costoTotal = 0;
	private boolean pciasDistintas = false;
	private JLabel lblCostoTotal;

	public frmAddConexion(final ArrayList<ciudad> lista) {
		setTitle("Agregar Conexion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		costoTotal=0;
		JLabel lblNombre = new JLabel("Conexi\u00F3n 1");
		lblNombre.setBounds(10, 11, 117, 20);
		contentPane.add(lblNombre);

		JLabel lblCantidadDeHabitantes = new JLabel("Costo de conexi\u00F3n por Km:");
		lblCantidadDeHabitantes.setBounds(10, 103, 160, 20);
		contentPane.add(lblCantidadDeHabitantes);

		JLabel lblLatitud = new JLabel("Costo fijo por Pcias Distintas:");
		lblLatitud.setVerticalTextPosition(SwingConstants.TOP);
		lblLatitud.setVerticalAlignment(SwingConstants.TOP);
		lblLatitud.setBounds(10, 149, 147, 17);
		contentPane.add(lblLatitud);

		final JComboBox<String> cmbConexion1 = new JComboBox<String>();
		final JComboBox<String> cmbConexion2 = new JComboBox<String>();
		JButton btnOk = new JButton("Agregar Conexi\u00F3n");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selec1 = cmbConexion1.getSelectedIndex();
				int selec2 = cmbConexion2.getSelectedIndex();
				MainGui.dibujarAristas(lista.get(selec1),lista.get(selec2));
				dispose();
			}
		});
		btnOk.setBounds(77, 286, 127, 56);
		contentPane.add(btnOk);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setBounds(224, 286, 127, 56);
		contentPane.add(btnCerrar);

		cmbConexion2.setBounds(180, 42, 200, 20);
		contentPane.add(cmbConexion2);

		txtCostoKm = new JTextField();
		txtCostoKm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				costoTotal = chkCostoTotal();
				
			}

		});
		txtCostoKm.setText("0");
		txtCostoKm.setColumns(10);
		txtCostoKm.setBounds(180, 103, 86, 20);
		contentPane.add(txtCostoKm);

		txtCostoPlus = new JTextField();
		txtCostoPlus.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				costoTotal = chkCostoTotal();
			}
		});
		txtCostoPlus.setText("0");
		txtCostoPlus.setColumns(10);
		txtCostoPlus.setBounds(180, 191, 83, 20);
		contentPane.add(txtCostoPlus);

		txtCostoPciasDif = new JTextField();
		txtCostoPciasDif.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				costoTotal = chkCostoTotal();
			}
		});
		txtCostoPciasDif.setText("0");
		txtCostoPciasDif.setColumns(10);
		txtCostoPciasDif.setBounds(180, 146, 86, 20);
		contentPane.add(txtCostoPciasDif);

		JLabel lblConexin = new JLabel("Conexi\u00F3n 2");
		lblConexin.setBounds(10, 42, 117, 20);
		contentPane.add(lblConexin);

		JLabel lblNewLabel = new JLabel("<html>Costo Fijo por conexi\u00F3n <br>de mas de 200 km:</html>");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 194, 166, 39);
		contentPane.add(lblNewLabel);

		final JLabel lblDistancia = new JLabel("Distancia entre ciudades:");
		lblDistancia.setForeground(Color.RED);
		lblDistancia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDistancia.setBounds(10, 72, 370, 20);
		contentPane.add(lblDistancia);

		cmbConexion1.setBounds(180, 11, 200, 20);
		contentPane.add(cmbConexion1);

		lblCostoTotal = new JLabel("Costo Total: ");
		lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoTotal.setForeground(Color.RED);
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoTotal.setBounds(0, 244, 445, 20);
		contentPane.add(lblCostoTotal);
		cmbConexion1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == cmbConexion1) {
					int selec1 = cmbConexion1.getSelectedIndex();
					int selec2 = cmbConexion2.getSelectedIndex();
					lblDistancia.setText(chkCombo(lista, selec1, selec2));
				}

			}
		});
		cmbConexion2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == cmbConexion2) {
					int selec1 = cmbConexion1.getSelectedIndex();
					int selec2 = cmbConexion2.getSelectedIndex();
					lblDistancia.setText(chkCombo(lista, selec1, selec2));
				}
			}
		});
		for (ciudad ciudad : lista) {
			cmbConexion1.addItem(ciudad.get_nombre() + ", " + ciudad.get_localidad() + ", " + ciudad.getProvincia());
			cmbConexion2.addItem(ciudad.get_nombre() + ", " + ciudad.get_localidad() + ", " + ciudad.getProvincia());
		}

	}

	protected double chkCostoTotal() {
		costoTotal = km * Double.parseDouble(txtCostoKm.getText());
		if (km > 200)
			costoTotal = costoTotal + Double.parseDouble(txtCostoPlus.getText());
		if (pciasDistintas)
			costoTotal = costoTotal + Double.parseDouble(txtCostoPciasDif.getText());
		lblCostoTotal.setText(String.format("Costo Total: $%.0f",costoTotal));
		return costoTotal;
	}

	public String chkCombo(final ArrayList<ciudad> lista, int selec1, int selec2) {
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		if (selec1 != selec2 && selec1 >= 0 && selec2 >= 0) {
			y1 = lista.get(selec1).getLatitud();
			x1 = lista.get(selec1).getLongitud();

			y2 = lista.get(selec2).getLatitud();
			x2 = lista.get(selec2).getLongitud();
			
			km = ciudad.distancia(y1, x1, y2, x2);
			
			if (lista.get(selec1).getProvincia().equals(lista.get(selec2).getProvincia()))
				pciasDistintas = false;
			else
				pciasDistintas = true;
			return String.format("Distancia entre ciudades: %.0f", km) + " km";

		}
		return "Distancia entre ciudades:";
	}
}
