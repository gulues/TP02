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
import javax.swing.JLabel;

public class MainGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel panelContenedor;
	private JPanel contentPane;
	private JTable tblCiudades;
	private JTable tblConexiones;
	private static DefaultTableModel mdlTablaCiudades;
	private static DefaultTableModel mdlTablaConexiones;
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
		panelBotones.setBounds(0, 0, 329, 635);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		JButton btnCiudad = new JButton("Agregar Ciudad");
		btnCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddCiudad fnew = new frmAddCiudad();
				fnew.setVisible(true);
			}
		});

		btnCiudad.setBounds(75, 11, 132, 34);
		panelBotones.add(btnCiudad);

		JButton btnConexion = new JButton("Agregar Conexion");
		btnConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//
				frmAddConexion newForm = new frmAddConexion(listaCiudades);
				newForm.setVisible(true);
			}
		});
		btnConexion.setBounds(75, 56, 132, 34);
		panelBotones.add(btnConexion);

		// Tabla Ciudades
		JScrollPane scrTablaCiudades = new JScrollPane();
		scrTablaCiudades.setBounds(10, 165, 309, 175);
		scrTablaCiudades.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mdlTablaCiudades = new DefaultTableModel();
		mdlTablaCiudades.addColumn("Nombre");
		mdlTablaCiudades.addColumn("Loc");
		mdlTablaCiudades.addColumn("Pcia");
		mdlTablaCiudades.addColumn("Lat");
		mdlTablaCiudades.addColumn("Lon");

		tblCiudades = new JTable(mdlTablaCiudades);
		tblCiudades.setFillsViewportHeight(true);
		tblCiudades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCiudades.setEditingRow(1);

		tblCiudades.setVisible(true);

		// Tabla Conexiones
				JScrollPane scrTablaConexiones= new JScrollPane();
				scrTablaConexiones.setBounds(10, 376, 309, 175);
				scrTablaConexiones.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				mdlTablaConexiones = new DefaultTableModel();
				mdlTablaConexiones.addColumn("Nombre");
				mdlTablaConexiones.addColumn("Loc");
				mdlTablaConexiones.addColumn("Pcia");
				mdlTablaConexiones.addColumn("Lat");
				mdlTablaConexiones.addColumn("Lon");

				tblConexiones = new JTable(mdlTablaConexiones);
				tblConexiones.setFillsViewportHeight(true);
				tblConexiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblConexiones.setEditingRow(1);

				tblConexiones.setVisible(true);
		
		
		
		
		
		
		JButton btnResolucion = new JButton("Resolucion");
		btnResolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//dibujarAristas();

			}
		});
		btnResolucion.setBounds(75, 101, 132, 34);
		panelBotones.add(btnResolucion);
		scrTablaCiudades.setViewportView(tblCiudades);
		scrTablaConexiones.setViewportView(tblConexiones);
		
		tblCiudades.setBounds(25, 332, 103, 141);
		tblConexiones.setBounds(25, 332, 103, 141);
		
		JLabel lblCiudades = new JLabel("Ciudades:");
		lblCiudades.setBounds(10, 140, 101, 14);
		panelBotones.add(lblCiudades);
		
		JLabel lblConexiones = new JLabel("Conexiones");
		lblConexiones.setBounds(10, 351, 159, 14);
		panelBotones.add(lblConexiones);
		
		panelBotones.add(scrTablaCiudades);
		panelBotones.add(scrTablaConexiones);
		
		
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
			mdlTablaCiudades.addRow(lst);
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