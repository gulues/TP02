package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewer.ZOOM_BUTTON_STYLE;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
//import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import ciudades.ciudad;
import java.awt.Dimension;

public class MainGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel panelContenedor;
	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel modeloTabla;
	public static ArrayList<ciudad> listaCiudades = new ArrayList<ciudad>();
	private static JMapViewer mapa = new JMapViewer();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public MainGui() {
		setMinimumSize(new Dimension(800, 600));

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Generador conexiones telefonicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 817, 614);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBotones.setBounds(0, 0, 329, 857);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		JButton btnNewButton = new JButton("Agregar Ciudad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddCiudad fnew = new frmAddCiudad();
				fnew.setVisible(true);
			}
		});

		btnNewButton.setBounds(10, 11, 132, 34);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Configurar Costos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//
				frmAddConexion newForm = new frmAddConexion(listaCiudades);
				newForm.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 64, 132, 34);
		panelBotones.add(btnNewButton_1);

		table = new JTable();
		// Tabla Socios
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(10, 252, 309, 288);
		scrollTabla.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Loc");
		modeloTabla.addColumn("Pcia");
		modeloTabla.addColumn("Lat");
		modeloTabla.addColumn("Lon");

		table = new JTable(modeloTabla);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEditingRow(1);

		table.setVisible(true);

		JButton btnDistancias = new JButton("Distancias");
		btnDistancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//dibujarAristas();

			}
		});
		btnDistancias.setBounds(10, 120, 132, 34);
		panelBotones.add(btnDistancias);
		scrollTabla.setViewportView(table);
		table.setBounds(25, 332, 103, -141);
		// panel.add(srollTabla);
		panelBotones.add(scrollTabla);

		panelContenedor = new JPanel();
		panelContenedor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelContenedor.setBounds(330, 0, 1248, 857);
		contentPane.add(panelContenedor);

		panelContenedor.setBackground(Color.WHITE);
		mapa.setVisible(true);
		panelContenedor.setLayout(new BorderLayout(0, 0));
		mapa.setZoomButtonStyle(ZOOM_BUTTON_STYLE.HORIZONTAL);
		panelContenedor.add(mapa, BorderLayout.CENTER);

		mapa.setLayout(new BorderLayout());
		mapa.setAutoscrolls(true);
		mapa.setZoom(10); // set some zoom level (1-18 are valid)
		mapa.setDisplayPositionByLatLon(-34.541333, -58.6859658, 10);
		mapa.setPreferredSize(null);
		mapa.setMinimumSize(null);

	}


	public static void mostrarCiudades(ArrayList<ciudad> arrayCiudades) {
		String lst[] = new String[5];

		for (ciudad ciudad : arrayCiudades) {
			mapa.addMapMarker(new MapMarkerDot(ciudad.getLatitud(), ciudad.getLongitud()));
			lst[0] = ciudad.get_nombre();
			lst[1] = ciudad.get_localidad();
			lst[2] = ciudad.getProvincia();
			lst[3] = ciudad.getLatitud() + "";
			lst[4] = ciudad.getLongitud() + "";
			modeloTabla.addRow(lst);
			listaCiudades.add(ciudad);
			
		}

	}

	private static double distancia(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static void dibujarAristas(ciudad c1, ciudad c2) {
		double x1 = 0;
		double x2 = 0;
		double y2 = 0;
		double y1 = 0;
		
		
		Coordinate coord1 = new Coordinate(c1.getLatitud(),c1.getLongitud());
		Coordinate coord2 = new Coordinate(c2.getLatitud(),c2.getLongitud());
		List<Coordinate> route = new ArrayList<Coordinate>();
		route.add(coord1);
		route.add(coord2);
		route.add(coord2);
		mapa.addMapPolygon(new MapPolygonImpl(route));
		mapa.repaint();
		System.out.println(distancia(y1, x1, y2, x2));
	}

}