package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewer.ZOOM_BUTTON_STYLE;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class MainGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] ciudad = new JLabel[100];
	private int indice = 0;
	private JPanel panelContenedor;
	private JPanel contentPane;
	private int posX;
	private int posY;
	private static boolean addRuta = false;
	private JTable table;
	private static Point select = new Point(-1, -1);
	private static DefaultTableModel modeloTabla;
	private JTextField textField;

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
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				//mapViewer.setBounds(0, 0, panelContenedor.WIDTH, panelContenedor.HEIGHT);
				
				
			}
		});

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 817, 614);
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
				frmAddPrecio fnew= new frmAddPrecio();
				fnew.show();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//
//				ciudad[indice] = new JLabel() {
//					private static final long serialVersionUID = 1L;
//
//					public void paint(Graphics g) {
//						super.paint(g);
//						double width = this.getSize().getWidth();
//						double height = this.getSize().getHeight();
//						g.setColor(Color.red);
//						for (int i = 0; i < width; i += height) {
//							g.drawOval(i, 0, 40, 40);
//
//						}
//					}
//				};
//				ciudad[indice].setVisible(true);
//				ciudad[indice].setAlignmentX(CENTER_ALIGNMENT);
//				ciudad[indice].setAlignmentY(BOTTOM_ALIGNMENT);
//				;
//				ciudad[indice].setText("    " + indice);
//				ciudad[indice].setFont(new Font("Tahoma", Font.BOLD, 15));
//				if (indice != 0)
//					ciudad[indice].setBounds(0, ciudad[indice - 1].getY() + 50,
//							50, 50);
//				else
//					ciudad[indice].setBounds(0, 0, 50, 50);
//
//				addActionListener(indice);
//				panelContenedor.add(ciudad[indice++]);
//				repaint();
//
//			}

			private void addActionListener(int indice) {
				ciudad[indice].addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (addRuta) {

							if (select.x == -1)
								select.x = indice;
							else {
								select.y = indice;
								addRuta = false;
							}
							if (select.x != -1 && select.y != -1) {

								String[] arreglo = new String[2];
								arreglo[0] = select.x + "";
								arreglo[1] = select.y + "";
								modeloTabla.addRow(arreglo);

								drawline(ciudad[select.x].getX(),
										ciudad[select.x].getY(),
										ciudad[select.y].getX(),
										ciudad[select.y].getY());
								select.x = -1;
								select.y = -1;

							}
						}
					}

				});

				ciudad[indice].addMouseMotionListener(new MouseAdapter() {
					public void mouseDragged(MouseEvent e) {
						posX = ciudad[indice].getX() + e.getX()
								- (ciudad[indice].getWidth() / 2);
						posY = ciudad[indice].getY() + e.getY()
								- (ciudad[indice].getHeight() / 2);
						ciudad[indice].setBounds(posX, posY, 50, 50);

					}
				});

			}
		});

		btnNewButton.setBounds(10, 11, 117, 23);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar Ruta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRuta = !addRuta;
			}
		});
		btnNewButton_1.setBounds(10, 174, 117, 23);
		panelBotones.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(10, 208, 117, 23);
		panelBotones.add(btnNewButton_2);

		table = new JTable();
		// Tabla Socios
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(10, 252, 144, 288);
		scrollTabla.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Ruta A");
		modeloTabla.addColumn("Ruta B");
		table = new JTable(modeloTabla);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEditingRow(1);

		table.setVisible(true);
		scrollTabla.setViewportView(table);
		table.setBounds(25, 332, 103, -141);
		// panel.add(srollTabla);
		panelBotones.add(scrollTabla);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(82, 143, 44, 20);
		panelBotones.add(textField);

		JLabel label = new JLabel("Desde:");
		label.setBounds(10, 84, 58, 14);
		panelBotones.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(82, 81, 45, 20);
		panelBotones.add(comboBox);

		JLabel label_1 = new JLabel("Hasta:");
		label_1.setBounds(10, 115, 34, 17);
		panelBotones.add(label_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(82, 112, 45, 20);
		panelBotones.add(comboBox_1);

		JLabel label_2 = new JLabel("Distancia:");
		label_2.setBounds(10, 146, 58, 17);
		panelBotones.add(label_2);

		JLabel lblRutas = new JLabel("Rutas:");
		lblRutas.setBounds(10, 56, 58, 14);
		panelBotones.add(lblRutas);
		
		JPanel panel = new JPanel();
		panel.setBounds(176, 11, 1157, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 0, 950, 700);
		panel.add(panelContenedor);

		panelContenedor.setBackground(Color.WHITE);
		FlowLayout fl_panelContenedor = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panelContenedor.setAlignOnBaseline(true);
		panelContenedor.setLayout(fl_panelContenedor);
		

	}

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
}