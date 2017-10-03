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




import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.FlowLayout;

public class MainGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	private JPanel contentPane;;
	private JTable table;
	private static DefaultTableModel modeloTabla;

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
				frmAddCiudad fnew= new frmAddCiudad();
				fnew.setVisible(true);
			}
		});

		

		btnNewButton.setBounds(10, 11, 132, 34);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Configurar Costos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAddPrecio newForm= new frmAddPrecio();
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
		
		JPanel panel = new JPanel();
		panel.setBounds(176, 11, 1186, 704);
		contentPane.add(panel);
		panel.setLayout(null);

		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 0, 1147, 700);
		panel.add(panelContenedor);

		panelContenedor.setBackground(Color.WHITE);
		FlowLayout fl_panelContenedor = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panelContenedor.setAlignOnBaseline(true);
		panelContenedor.setLayout(fl_panelContenedor);
		

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
}