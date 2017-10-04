package gui;


import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

import javax.swing.JTable;

import ciudades.ciudad;

import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.awt.event.MouseEvent;

import org.openstreetmap.gui.jmapviewer.Style;

import java.awt.Font;

import org.openstreetmap.gui.jmapviewer.JMapViewer.ZOOM_BUTTON_STYLE;

import java.awt.BorderLayout;

public class MainGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel panelContenedor;
	private JPanel contentPane;;
	private JTable table;
	private static DefaultTableModel modeloTabla;

	private static JMapViewer mapa = new JMapViewer();
	/**
	 * @wbp.nonvisual location=127,189
	 */
	private final Coordinate coordinate = new Coordinate(0.0, 0.0);
	/**
	 * @wbp.nonvisual location=54,169
	 */
	private final Style style = new Style();

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
	public MainGui() {
		style.setFont(new Font("Arial Black", Font.PLAIN, 12));
		style.setColor(Color.RED);
		coordinate.setLon(-58.6859658);
		coordinate.setLat(-34.541333);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 817, 614);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panelBotones.setBounds(0, 0, 166, 857);
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
				frmAddPrecio newForm = new frmAddPrecio();
				newForm.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 64, 132, 34);
		panelBotones.add(btnNewButton_1);

		table = new JTable();
		// Tabla Socios
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(10, 252, 144, 288);
		scrollTabla.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Latitud");
		modeloTabla.addColumn("Longitud");
		modeloTabla.addColumn("Localidad");
		modeloTabla.addColumn("Provincia");

		table = new JTable(modeloTabla);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEditingRow(1);

		table.setVisible(true);

		JButton btnDistancias = new JButton("Distancias");
		btnDistancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<MapMarker> lsts = mapa.getMapMarkerList();
				boolean band = true;
				double x1 = 0;
				double x2 = 0;
				double y2 = 0;
				double y1 = 0;
				for (MapMarker puntos : lsts) {
					if (band) {
						x1 = puntos.getLon();
						y1 = puntos.getLat();
					} else {
						x2 = puntos.getLon();
						y2 = puntos.getLat();
					}
					band = !band;
					
				}
				Coordinate c1= new Coordinate(y1, x1);
				Coordinate c2= new Coordinate(y2, x2);
				List<Coordinate> route = new ArrayList<Coordinate>();
				route.add(c1);
				route.add(c2);
				route.add(c2);
				mapa.addMapPolygon(new MapPolygonImpl(route));
				mapa.repaint();
				System.out.println(distancia(y1, x1, y2, x2));

			}
		});
		btnDistancias.setBounds(10, 120, 132, 34);
		panelBotones.add(btnDistancias);
		scrollTabla.setViewportView(table);
		table.setBounds(25, 332, 103, -141);
		// panel.add(srollTabla);
		panelBotones.add(scrollTabla);

		JPanel panel = new JPanel();
		panel.setBounds(176, 11, 1186, 704);
		contentPane.add(panel);
		panel.setLayout(null);

		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 0, 1147, 700);
		panel.add(panelContenedor);

		panelContenedor.setBackground(Color.WHITE);
		mapa.setVisible(true);
		panelContenedor.setLayout(new BorderLayout(0, 0));
		mapa.setZoomButtonStyle(ZOOM_BUTTON_STYLE.HORIZONTAL);
		panelContenedor.add(mapa, BorderLayout.CENTER);

		new DefaultMapController(mapa) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String lst[] = new String[4];
				mapa.getAttribution().handleAttribution(e.getPoint(), true);
				ICoordinate position = mapa.getPosition(e.getPoint());
				double x = position.getLon();
				double y = position.getLat();

				mapa.addMapMarker(new MapMarkerDot(y, x));

				lst[0] = y + "";
				lst[1] = x + "";

				// lst[3]= (distancia(x,y,));
				// mapa.addMapMarker(new MapMarkerDot(y,x));
				// lst[3]=mapa.getLocale().getCountry();
				modeloTabla.addRow(lst);

			}
		};

		mapa.setLayout(new BorderLayout());
		mapa.setAutoscrolls(true);
		mapa.setZoom(10); // set some zoom level (1-18 are valid)
		mapa.setDisplayPositionByLatLon(coordinate.getLat(),
				coordinate.getLon(), 10);
		mapa.setPreferredSize(null);
		mapa.setMinimumSize(null);
		// mapa.addJMVListener((JMapViewerEventListener) this);
		mapa.repaint(); // if already visible trigger a repaint here

	}

	@SuppressWarnings("unused")
	private void drawline(double x1, double y1, double x2, double y2) {
		Graphics g = getGraphics();
		Graphics2D ga = (Graphics2D) g;
		ga.setColor(Color.BLUE);

		// creates a solid stroke with line width is 2
		Stroke stroke = new BasicStroke(3f);
		ga.setStroke(stroke);
		ga.draw(new Line2D.Float((int) x1 + 196, (int) y1 + 61, (int) x2 + 196,
				(int) y2 + 61));
		// ga.drawLine((int)x1+196, (int)y1+61, (int)x2+196,(int) y2+61);

	}

	@SuppressWarnings("null")
	public static void mostrarCiudades(ArrayList<ciudad> arrayCiudades) {

		List<MapMarker> listaCiudades = new ArrayList<MapMarker>();
		MapMarker punto = null;
		
		for (ciudad ciudad : arrayCiudades) {
			punto.setLon(ciudad.getLongitud());
			punto.setLat(ciudad.getLatitud());
			listaCiudades.add(punto);
		}
		mapa.setMapMarkerList(listaCiudades);
		mapa.setDisplayToFitMapMarkers();
		mapa.repaint();

	}
	private static double distancia(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}