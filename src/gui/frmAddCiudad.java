package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import ciudades.ciudad;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class frmAddCiudad extends JFrame {
	private static final long serialVersionUID = -4754671575152617996L;
	private JTextField txtLocalidad;
	private JTextField txtHabitantes;
	private JTextField txtLat;
	private JTextField txtLon;
	public static DefaultTableModel mdlCiudades;
	private ArrayList<ciudad> arrayCiudades = new ArrayList<ciudad>();
	private JPanel contentPane;
	private JTable tblCiudades;
	private JTextField txtNombre;
	public JTextField txtPcia = new JTextField();

	public frmAddCiudad() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Agregar Ciudad");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 598);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Localidad:");
		lblNombre.setBounds(10, 42, 75, 20);
		contentPane.add(lblNombre);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(10, 77, 75, 20);
		contentPane.add(lblProvincia);

		JLabel lblCantidadDeHabitantes = new JLabel("Cantidad de Habitantes:");
		lblCantidadDeHabitantes.setBounds(10, 116, 117, 20);
		contentPane.add(lblCantidadDeHabitantes);

		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setBounds(10, 162, 75, 20);
		contentPane.add(lblLatitud);

		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(227, 162, 75, 20);
		contentPane.add(lblLongitud);

		JButton btnOk = new JButton("Agregar Ciudad");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ciudad c = new ciudad(txtNombre.getText(), txtLocalidad
						.getText(), txtPcia.getText(), Double.parseDouble(txtLat
						.getText()), Double.parseDouble(txtLon.getText()),
						Integer.parseInt(txtHabitantes.getText()));
				arrayCiudades.add(c);
				refreshTable(arrayCiudades);
				txtNombre.setText("");
				txtLocalidad.setText("");
				txtHabitantes.setText("");
				txtPcia.setText("");
				txtLat.setText("");
				txtLon.setText("");
				

			}
		});
		btnOk.setBounds(104, 196, 117, 56);
		contentPane.add(btnOk);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(232, 196, 117, 56);
		contentPane.add(btnCerrar);

		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(139, 42, 200, 20);
		contentPane.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		txtPcia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					String latLongs[] = getLatLongPositions(txtLocalidad.getText()+", " +txtPcia.getText());
					txtLat.setText(latLongs[0]);
					txtLon.setText(latLongs[1]);
					String[] direccion = latLongs[2].split(", ");
					txtLocalidad.setText(direccion[0]);
					txtPcia.setText(direccion[1].replace(" Province", ""));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		txtPcia.setBounds(139, 77, 200, 20);
		contentPane.add(txtPcia);

		txtHabitantes = new JTextField();
		txtHabitantes.setColumns(10);
		txtHabitantes.setBounds(139, 116, 200, 20);
		contentPane.add(txtHabitantes);

		txtLat = new JTextField();
		txtLat.setColumns(10);
		txtLat.setBounds(79, 162, 117, 20);
		contentPane.add(txtLat);

		txtLon = new JTextField();
		txtLon.setColumns(10);
		txtLon.setBounds(282, 162, 130, 20);
		contentPane.add(txtLon);

		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));

		scrollTabla.setBounds(0, 263, 445, 209);
		contentPane.add(scrollTabla);

		JLabel label = new JLabel("Nombre:");
		label.setBounds(10, 11, 75, 20);
		contentPane.add(label);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(139, 11, 200, 20);
		contentPane.add(txtNombre);
		mdlCiudades = new DefaultTableModel();
		mdlCiudades.addColumn("Nombre");
		mdlCiudades.addColumn("Localidad");
		mdlCiudades.addColumn("Provincia");
		mdlCiudades.addColumn("Cant Hab");
		mdlCiudades.addColumn("Lat");
		mdlCiudades.addColumn("Long");
		
		
		tblCiudades = new JTable(mdlCiudades);
		
		tblCiudades.setFillsViewportHeight(true);
		scrollTabla.setViewportView(tblCiudades);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMain.mostrarCiudades(arrayCiudades);
				dispose();
			}
		});
		btnAceptar.setBounds(66, 501, 130, 57);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(251, 501, 130, 57);
		contentPane.add(btnCancelar);
		tblCiudades.setDefaultEditor(Object.class, null);

	}

	public static void refreshTable(ArrayList<ciudad> listaCiudades) {
		for (int i = 0; i < mdlCiudades.getRowCount(); i++) {
			mdlCiudades.removeRow(i);
			i -= 1;
		}
		Object[] arreglo = new String[mdlCiudades.getColumnCount()];

		for (ciudad c : listaCiudades) {
			arreglo[0] = c.get_nombre();
			arreglo[1] = c.get_localidad();
			arreglo[2] = c.getProvincia();
			arreglo[3] = c.getCantidadHabitantes() + "";
			arreglo[4] = c.getLatitud() + "";
			arreglo[5] = c.getLongitud() + "";
			mdlCiudades.addRow(arreglo);
		}

	}
	
	public static String[] getLatLongPositions(String address) throws Exception
	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    System.out.println("URL : "+api);
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         //formatted_address
	    	  expr = xpath.compile("//formatted_address");
		     String direccion = (String)expr.evaluate(document, XPathConstants.STRING); 
	    	 expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         return new String[] {latitude, longitude,direccion};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    return null;
	  }
}
