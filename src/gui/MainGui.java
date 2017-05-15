package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
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

import javax.swing.JTable;

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

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panelBotones.setBounds(0, 0, 166, 540);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		JButton btnNewButton = new JButton("Agregar Ciudad");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ciudad[indice] = new JLabel() {
					private static final long serialVersionUID = 1L;

					public void paint(Graphics g) {
						super.paint(g);
						double width = this.getSize().getWidth();
						double height = this.getSize().getHeight();
						g.setColor(Color.red);
						if(select.x!=-1 && select.y!=-1){
						Graphics2D g2 = (Graphics2D) g;
						g2.drawLine(ciudad[select.x].getX()+100,
									ciudad[select.x].getY()+100,
									ciudad[select.y].getX()+100,
									ciudad[select.y].getY()+100);
						
						}
						for (int i = 0; i < width; i += height) {
							g.drawOval(i, 0, 40, 40);		
							
						}
					}
				};
				ciudad[indice].setVisible(true);
				ciudad[indice].setAlignmentX(CENTER_ALIGNMENT);
				ciudad[indice].setAlignmentY(BOTTOM_ALIGNMENT);;
				ciudad[indice].setText("    " + indice);
				ciudad[indice].setFont(new Font("Tahoma", Font.BOLD, 15));
				if (indice != 0)
					ciudad[indice].setBounds(0, ciudad[indice - 1].getY() + 50,
							50, 50);
				else
					ciudad[indice].setBounds(0, 0, 50, 50);

				addActionListener(indice);
				panelContenedor.add(ciudad[indice++]);
				repaint();

			}

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
								if (select.x != -1 && select.y != -1)
								{       
									
								    String[] arreglo = new String[2];
									arreglo[0] = select.x+"";
									arreglo[1] = select.y+"";
									modeloTabla.addRow(arreglo);
									
									drawline(ciudad[select.x].getX(), ciudad[select.x].getY(), ciudad[select.y].getX(), ciudad[select.y].getY());
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

		btnNewButton.setBounds(10, 26, 117, 23);
		panelBotones.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar Ruta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRuta = !addRuta;
			}
		});
		btnNewButton_1.setBounds(10, 60, 117, 23);
		panelBotones.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(10, 94, 117, 23);
		panelBotones.add(btnNewButton_2);

		table = new JTable();
		// Tabla Socios
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(10, 252, 144, 288);
		scrollTabla.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
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
		//panel.add(srollTabla);
		panelBotones.add(scrollTabla);

		// JLabel lblNewLabel = new JLabel("New label");

		// lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		// lblNewLabel.setBounds(34, 162, 46, 14);
		// panel.add(lblNewLabel);

		panelContenedor = new JPanel();
		
		panelContenedor.setBackground(Color.WHITE);
		panelContenedor.setBounds(176, 11, 598, 539);

		contentPane.add(panelContenedor);
	}
	private void drawline(double x1, double y1, double x2,double y2) {
		Graphics g=  getGraphics();
		 Graphics2D ga= (Graphics2D) g;
		 ga.setColor(Color.BLUE);

		// creates a solid stroke with line width is 2
		Stroke stroke = new BasicStroke(2f);
		ga.setStroke(stroke);
		ga.draw(new Line2D.Float((int)x1+196, (int)y1+61, (int)x2+196,(int) y2+61));
		// ga.drawLine((int)x1+196, (int)y1+61, (int)x2+196,(int) y2+61);
		
	}
}