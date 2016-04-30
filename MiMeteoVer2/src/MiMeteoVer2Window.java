import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.FlowLayout;
import javax.swing.JEditorPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.TitledBorder;

import com.panamahitek.*;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.TextArea;
public class MiMeteoVer2Window extends JFrame {

	List<String> listadoPuertosArduino=null;

	PanamaHitek_Arduino miArduino=new PanamaHitek_Arduino();
	PanamaHitek_multiMessage miMultimensaje= new PanamaHitek_multiMessage(5, miArduino);
	private String lecturaDirectaTemperaturaAgua=null;
	private String lecturaDirectaTemperaturaAire=null;
	private String lecturaDirectaPresionAtmosferica=null;
	private String lecturaDirectaHumedadRelativa=null;
	float PrimerNivelTemperaturaAgua=0, PrimerNivelTemperaturaAire=0, PrimerNivelHumedadRelativa=0;
	int PrimerNivelPresionAtmosferica=0;
	private Timer temporizador;
	private byte horaActual;
	private byte minutoActual;
	private byte segundoActual;
	private byte diaActual;
	private byte mesActual;
	private int annoActual;
	private Sensores miSensorPrimerNivel, miSensorSegundoNivel, miSensorTercerNivel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiMeteoVer2Window frame = new MiMeteoVer2Window();
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
	
	// Función 
	
	public MiMeteoVer2Window() {
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jLabelLecturaInicialTemperaturaAire, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabelLecturaInicialTemperaturaAgua, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLecturaInicialTemperaturaAgua, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblLecturaInicialTemperaturaAire, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(jLabelLecturaInicialPresionAtmosferica, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblLecturaInicialPresionAtmosferica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(jLabelLecturaInicialHumedadRelativa, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblLecturaInicialHumedadRelativa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(100))
		);
		lblLecturaInicialTemperaturaAgua.setPreferredSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAgua.setMinimumSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAgua.setMaximumSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAgua.setSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAire.setPreferredSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAire.setMinimumSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAire.setMaximumSize(new Dimension(60, 14));
		lblLecturaInicialTemperaturaAire.setSize(new Dimension(60, 14));
		lblLecturaInicialPresionAtmosferica.setPreferredSize(new Dimension(60, 14));
		lblLecturaInicialPresionAtmosferica.setMinimumSize(new Dimension(60, 14));
		lblLecturaInicialPresionAtmosferica.setMaximumSize(new Dimension(60, 14));
		lblLecturaInicialPresionAtmosferica.setSize(new Dimension(60, 14));
		lblLecturaInicialHumedadRelativa.setPreferredSize(new Dimension(60, 14));
		lblLecturaInicialHumedadRelativa.setMinimumSize(new Dimension(60, 14));
		lblLecturaInicialHumedadRelativa.setMaximumSize(new Dimension(60, 14));
		lblLecturaInicialHumedadRelativa.setSize(new Dimension(60, 14));
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabelLecturaInicialTemperaturaAgua, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(1)
							.addComponent(lblLecturaInicialTemperaturaAgua, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLecturaInicialTemperaturaAire, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabelLecturaInicialTemperaturaAire, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLecturaInicialPresionAtmosferica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabelLecturaInicialPresionAtmosferica, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLecturaInicialHumedadRelativa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabelLecturaInicialHumedadRelativa, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addGap(27))
		);

		temporizador= new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar calendario= new GregorianCalendar();
				horaActual=(byte)calendario.get(calendario.HOUR_OF_DAY);
				minutoActual=(byte)calendario.get(calendario.MINUTE);
				segundoActual=(byte)calendario.get(calendario.SECOND);
				diaActual=(byte)calendario.get(calendario.DATE);
				mesActual=(byte)calendario.get(calendario.MONTH);
				annoActual=calendario.get(calendario.YEAR);
				lblHoraActual.setText(horaActual+":"+minutoActual+":"+segundoActual);
				lblFechaActual.setText(diaActual+"/"+mesActual+"/"+annoActual);
			}
		});
		ConstruirFrame();
		RellenarComboConexiones();
	    temporizador.start();
	}
	
	private void RellenarComboConexiones() {
		listadoPuertosArduino=miArduino.getSerialPorts();
		for (int i=0;i<listadoPuertosArduino.size();i++){
			jComboSeleccionPuertos.addItem(listadoPuertosArduino.get(i).toString());
		}
	}

	// Función para construir el panel
	
	private void ComunicacionConArduino() {
		// Método para la comunicación con el dispositivo Arduino
		try {
			miArduino.arduinoRX(jLabelNombrePuertoSeleccionado.getText().toString(),9600, new SerialPortEventListener() {
				int repeticiones=0;
				@Override
				public void serialEvent(SerialPortEvent arg0) {
					if (miMultimensaje.DataReceptionCompleted()){
						lecturaDirectaTemperaturaAgua=miMultimensaje.getMessage(0).toString();
						lecturaDirectaTemperaturaAire=miMultimensaje.getMessage(1).toString();
						lecturaDirectaPresionAtmosferica=miMultimensaje.getMessage(2).toString();
						lecturaDirectaHumedadRelativa=miMultimensaje.getMessage(3).toString();
						miMultimensaje.flushBuffer();
						rellenarLecturaDirecta();
						rellenarPrimerNivel();
						if ((repeticiones>=3 && rellenarSegundoNivel()) || (repeticiones<3)){
							repeticiones++;
						}
						if (repeticiones>=16){
							rellenarTercerNivel();
						}
					}
				}

				private void rellenarTercerNivel() {
					// TODO Auto-generated method stub
					
				}

				private boolean rellenarSegundoNivel() {
					// TODO Auto-generated method stub
					boolean rellenado=false;
					while(!rellenado){
						
					}
					return rellenado;
				}

				private void rellenarPrimerNivel() {
					// TODO Auto-generated method stub
					PrimerNivelTemperaturaAgua=Float.parseFloat(lecturaDirectaTemperaturaAgua.replaceAll("[a-zA-Z\\s:]", ""));
					System.out.println("Agua "+PrimerNivelTemperaturaAgua);
					PrimerNivelTemperaturaAire=Float.parseFloat(lecturaDirectaTemperaturaAire.replaceAll("[a-zA-Z\\s:]", ""));
					System.out.println("Aire "+PrimerNivelTemperaturaAire);
					PrimerNivelHumedadRelativa=Float.parseFloat(lecturaDirectaHumedadRelativa.replaceAll("[a-zA-Z%\\s:]", ""));
					System.out.println("Humedad Relativa "+PrimerNivelHumedadRelativa);
					PrimerNivelPresionAtmosferica=Integer.parseInt(lecturaDirectaPresionAtmosferica.replaceAll("[a-zA-Z\\s:]", ""));
					System.out.println(PrimerNivelPresionAtmosferica);
					lblLecturaInicialTemperaturaAgua.setText(Float.toString(PrimerNivelTemperaturaAgua));
					lblLecturaInicialTemperaturaAire.setText(Float.toString(PrimerNivelTemperaturaAire));
					lblLecturaInicialHumedadRelativa.setText(Float.toString(PrimerNivelHumedadRelativa));
					lblLecturaInicialPresionAtmosferica.setText(Float.toString(PrimerNivelPresionAtmosferica));
					miSensorPrimerNivel.setTemperaturaAgua(PrimerNivelTemperaturaAgua);
					miSensorPrimerNivel.setTemperaturaAire(PrimerNivelTemperaturaAire);
					miSensorPrimerNivel.setHumedadRelativa(PrimerNivelHumedadRelativa);
					miSensorPrimerNivel.setPresionAtmosferica(PrimerNivelPresionAtmosferica);
				}

				private void rellenarLecturaDirecta() {
					// TODO Auto-generated method stub
				    jTextAreaLecturasDirectas.append(lecturaDirectaTemperaturaAgua);
				    jTextAreaLecturasDirectas.append("\n");
					 jTextAreaLecturasDirectas.append(lecturaDirectaTemperaturaAire);
					 jTextAreaLecturasDirectas.append("\n");
					 jTextAreaLecturasDirectas.append(lecturaDirectaPresionAtmosferica);
					 jTextAreaLecturasDirectas.append("\n");
					 jTextAreaLecturasDirectas.append(lecturaDirectaHumedadRelativa);	
					 jTextAreaLecturasDirectas.append("\n\n");
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ConstruirFrame() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1800,1090);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 50));
		jPanelSensoresArduino.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SENSORES ARDUINO", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LECTURAS DIRECTAS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 102, 51));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblHora.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHoraActual.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFechaActual.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComunicacionConArduino();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 25));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 33));
		lblNewLabel_9.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.setBorder(new TitledBorder(null, "LECTURAS CALIBRADAS", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LECTURAS INICIALES", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_10)
							.addGap(22)
							.addComponent(label_14, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_9)
							.addGap(22)
							.addComponent(label_13, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_8)
							.addGap(22)
							.addComponent(label_12, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_7)
							.addGap(22)
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_19, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_23, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_20, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_24, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_21, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_25, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_22, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_26, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
											.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_23, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addComponent(label_24, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
											.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_20, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
								.addComponent(label_19, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(label_25, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_21, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_22, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_26, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(jPanelSensoresArduino, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
									.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblHoraActual)
											.addComponent(lblHora))
										.addPreferredGap(ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblFecha)
											.addComponent(lblFechaActual))))
								.addGap(18)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1312, Short.MAX_VALUE))
						.addGap(22))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(25)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblHora)
									.addComponent(lblFecha))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblHoraActual)
									.addComponent(lblFechaActual))
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addComponent(jPanelSensoresArduino, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
			);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setAutoscrolls(true);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
						.addContainerGap(49, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			jTextAreaLecturasDirectas.setWrapStyleWord(true);
			jTextAreaLecturasDirectas.setLineWrap(true);
			
			
			scrollPane.setViewportView(jTextAreaLecturasDirectas);
			panel_1.setLayout(gl_panel_1);
		jLabelLecturaInicialTemperaturaAgua.setFont(new Font("Arial", Font.PLAIN, 15));
		jLabelLecturaInicialTemperaturaAgua.setOpaque(true);
		jLabelLecturaInicialTemperaturaAgua.setBackground(new Color(204, 255, 255));
		jLabelLecturaInicialTemperaturaAire.setFont(new Font("Arial", Font.PLAIN, 15));
		jLabelLecturaInicialTemperaturaAire.setBackground(new Color(204, 255, 255));
		jLabelLecturaInicialTemperaturaAire.setOpaque(true);
		jLabelLecturaInicialPresionAtmosferica.setFont(new Font("Arial", Font.PLAIN, 15));
		jLabelLecturaInicialPresionAtmosferica.setOpaque(true);
		jLabelLecturaInicialPresionAtmosferica.setBackground(new Color(204, 255, 255));
		jLabelLecturaInicialHumedadRelativa.setFont(new Font("Arial", Font.PLAIN, 15));
		jLabelLecturaInicialHumedadRelativa.setOpaque(true);
		jLabelLecturaInicialHumedadRelativa.setBackground(new Color(204, 255, 255));
		lblLecturaInicialTemperaturaAgua.setOpaque(true);
		lblLecturaInicialTemperaturaAgua.setBackground(Color.WHITE);
		lblLecturaInicialTemperaturaAire.setOpaque(true);
		lblLecturaInicialTemperaturaAire.setBackground(Color.WHITE);
		lblLecturaInicialPresionAtmosferica.setOpaque(true);
		lblLecturaInicialPresionAtmosferica.setBackground(Color.WHITE);
		lblLecturaInicialHumedadRelativa.setOpaque(true);
		lblLecturaInicialHumedadRelativa.setBackground(Color.WHITE);
		label_26.setOpaque(true);
		label_26.setBackground(Color.WHITE);
		label_25.setOpaque(true);
		label_25.setBackground(Color.WHITE);
		label_24.setOpaque(true);
		label_24.setBackground(Color.WHITE);
		label_23.setOpaque(true);
		label_23.setBackground(Color.WHITE);
		label_22.setOpaque(true);
		label_22.setBackground(Color.WHITE);
		label_21.setOpaque(true);
		label_21.setBackground(Color.WHITE);
		label_20.setOpaque(true);
		label_20.setBackground(Color.WHITE);
		label_19.setOpaque(true);
		label_19.setBackground(Color.WHITE);
		label_18.setOpaque(true);
		label_18.setBackground(Color.WHITE);
		label_17.setOpaque(true);
		label_17.setBackground(Color.WHITE);
		label_16.setOpaque(true);
		label_16.setBackground(Color.WHITE);
		label_15.setOpaque(true);
		label_15.setBackground(Color.WHITE);
		label_14.setOpaque(true);
		label_14.setBackground(Color.WHITE);
		label_13.setOpaque(true);
		label_13.setBackground(Color.WHITE);
		label_12.setOpaque(true);
		label_12.setBackground(Color.WHITE);
		label_11.setOpaque(true);
		label_11.setBackground(Color.WHITE);
		label_10.setOpaque(true);
		label_10.setBackground(Color.WHITE);
		label_9.setOpaque(true);
		label_9.setBackground(Color.WHITE);
		label_8.setOpaque(true);
		label_8.setBackground(Color.WHITE);
		label_7.setOpaque(true);
		label_7.setBackground(Color.WHITE);
		label_6.setOpaque(true);
		label_6.setFont(new Font("Arial", Font.PLAIN, 15));
		label_6.setBackground(new Color(204, 255, 255));
		label_5.setOpaque(true);
		label_5.setFont(new Font("Arial", Font.PLAIN, 15));
		label_5.setBackground(new Color(204, 255, 255));
		label_1.setOpaque(true);
		label_1.setFont(new Font("Arial", Font.PLAIN, 15));
		label_1.setBackground(new Color(204, 255, 255));
		label.setOpaque(true);
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setBackground(new Color(204, 255, 255));
		panel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_3.setLayout(gl_panel_3);
		
		JButton btnNewButton_1 = new JButton("CONTINUAR");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 1290, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(466)
									.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
							.addGap(10))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel_9)
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CREP\u00DASCULOS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBorder(new TitledBorder(null, "LECTURAS CALIBRADAS EN TIEMPO REAL", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setBackground(new Color(204, 255, 255));
		lblTemperaturaDelAire.setOpaque(true);
		lblTemperaturaDelAire.setBackground(new Color(204, 255, 255));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblHumedadRelativa_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_14, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lblTemperaturaDelAire, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPresionAtmosfrica_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_15)
							.addComponent(label_31)
							.addComponent(label_32))
						.addComponent(label_33))
					.addGap(41)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_15))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTemperaturaDelAire, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_31))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPresionAtmosfrica_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_32))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHumedadRelativa_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_33)))
						.addComponent(panel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
					.addContainerGap())
		);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_16, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_16, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_7.setLayout(gl_panel_7);
		panel_6.setLayout(gl_panel_6);
		lblNewLabel_11.setFont(new Font("Arial", Font.PLAIN, 15));	
		lblNewLabel_12.setFont(new Font("Arial", Font.PLAIN, 15));		
		lblNewLabel_13.setOpaque(true);
		lblNewLabel_13.setBackground(new Color(153, 204, 255));
		label_27.setBackground(new Color(153, 204, 255));
		label_27.setOpaque(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		panel_2.setLayout(gl_panel_2);
		lblLabelLecturaDirecta.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLabelLecturaDirecta.setOpaque(true);
		lblLabelLecturaDirecta.setForeground(Color.WHITE);
		lblLabelLecturaDirecta.setBackground(Color.BLUE);
		lblLabelLecturaDirecta.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelConexiones.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelConexiones.setFont(new Font("Arial Black", Font.ITALIC, 16));
		jLabelSeleccionPuertos.setHorizontalAlignment(SwingConstants.CENTER);		
		jLabelPuertoSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);		
		jLabelNombrePuertoSeleccionado.setForeground(Color.RED);
		jLabelNombrePuertoSeleccionado.setFont(new Font("Arial Black", Font.ITALIC, 36));
		jLabelNombrePuertoSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
		gl_jPanelSensoresArduino.setHorizontalGroup(
			gl_jPanelSensoresArduino.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanelSensoresArduino.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelConexiones, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_jPanelSensoresArduino.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelSeleccionPuertos, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_jPanelSensoresArduino.createSequentialGroup()
					.addGap(95)
					.addComponent(jComboSeleccionPuertos, 0, 110, Short.MAX_VALUE)
					.addGap(95))
				.addGroup(Alignment.TRAILING, gl_jPanelSensoresArduino.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelPuertoSeleccionado, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_jPanelSensoresArduino.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelNombrePuertoSeleccionado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_jPanelSensoresArduino.setVerticalGroup(
			gl_jPanelSensoresArduino.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanelSensoresArduino.createSequentialGroup()
					.addGap(11)
					.addComponent(jLabelConexiones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(jLabelSeleccionPuertos, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(jComboSeleccionPuertos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jLabelPuertoSeleccionado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jLabelNombrePuertoSeleccionado)
					.addGap(107))
		);
		jComboSeleccionPuertos.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jComboSeleccionPuertosActionPerformed(e);
			}

			private void jComboSeleccionPuertosActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jLabelNombrePuertoSeleccionado.setText(jComboSeleccionPuertos.getSelectedItem().toString());
			}
		});
		jPanelSensoresArduino.setLayout(gl_jPanelSensoresArduino);
		contentPane.setLayout(gl_contentPane);
		lblNewLabel_16.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHumedadRelativa_1.setBackground(new Color(204, 255, 255));
		lblHumedadRelativa_1.setOpaque(true);
		lblPresionAtmosfrica_1.setBackground(new Color(204, 255, 255));
		lblPresionAtmosfrica_1.setOpaque(true);
	}
	// Declaraciones Elementos del panel
	
	private JPanel contentPane=new JPanel();
	private JLabel lblNewLabel = new JLabel("Mi Meteo. Ver 2");
	private JPanel jPanelSensoresArduino = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JLabel lblNewLabel_3 = new JLabel("Alertas Meteorol\u00F3gicas Locales");
	private JLabel lblHora = new JLabel("HORA");
	private JLabel lblFecha = new JLabel("FECHA");
	private JLabel lblHoraActual = new JLabel("New label");
	private JLabel lblFechaActual = new JLabel("New label");
	private JButton btnNewButton = new JButton("CONECTAR SENSORES");
	private JPanel panel_2 = new JPanel();
	private JLabel lblNewLabel_9 = new JLabel("CALIBRACI\u00D3N DEL SISTEMA");
	private JPanel panel_3 = new JPanel();
	private GroupLayout gl_panel_3 = new GroupLayout(panel_3);
	private JPanel panel_4 = new JPanel();
	private GroupLayout gl_contentPane = new GroupLayout(contentPane);
	private JPanel panel_5 = new JPanel();
	private JLabel lblLabelLecturaDirecta = new JLabel("HIPERTERMINAL");
	private JLabel jLabelConexiones = new JLabel("CONEXIONES");
	private JComboBox jComboSeleccionPuertos = new JComboBox();
	private JLabel jLabelSeleccionPuertos = new JLabel("SELECCIONE PUERTO DE COMUNICACIONES");
	private JLabel jLabelPuertoSeleccionado = new JLabel("PUERTO SELECCIONADO");
	private JLabel jLabelNombrePuertoSeleccionado = new JLabel("New label");
	private GroupLayout gl_jPanelSensoresArduino = new GroupLayout(jPanelSensoresArduino);
	private JLabel jLabelLecturaInicialTemperaturaAgua = new JLabel("Temperatura del agua");
	private JLabel jLabelLecturaInicialTemperaturaAire = new JLabel("Temperatura del aire");
	private JLabel jLabelLecturaInicialPresionAtmosferica = new JLabel("Presi\u00F3n Atmosf\u00E9rica");
	private JLabel jLabelLecturaInicialHumedadRelativa = new JLabel("Humedad Relativa");
	private JLabel lblLecturaInicialTemperaturaAgua = new JLabel("New label");
	private JLabel lblLecturaInicialTemperaturaAire = new JLabel("New label");
	private JLabel lblLecturaInicialPresionAtmosferica = new JLabel("New label");
	private JLabel lblLecturaInicialHumedadRelativa = new JLabel("New label");
	private GroupLayout gl_panel_4 = new GroupLayout(panel_4);
	private JLabel label = new JLabel("Temperatura del agua");
	private JLabel label_1 = new JLabel("Temperatura del agua");
	private JLabel label_5 = new JLabel("Temperatura del agua");
	private JLabel label_6 = new JLabel("Temperatura del agua");
	private JLabel label_7 = new JLabel("New label");
	private JLabel label_8 = new JLabel("New label");
	private JLabel label_9 = new JLabel("New label");
	private JLabel label_10 = new JLabel("New label");
	private JLabel label_11 = new JLabel("New label");
	private JLabel label_12 = new JLabel("New label");
	private JLabel label_13 = new JLabel("New label");
	private JLabel label_14 = new JLabel("New label");
	private JLabel label_15 = new JLabel("New label");
	private  JLabel label_16 = new JLabel("New label");
	private JLabel label_17 = new JLabel("New label");
	private JLabel label_18 = new JLabel("New label");
	private JLabel label_19 = new JLabel("New label");
	private JLabel label_20 = new JLabel("New label");
	private JLabel label_21 = new JLabel("New label");
	private JLabel label_22 = new JLabel("New label");
	private JLabel label_23 = new JLabel("New label");
	private JLabel label_24 = new JLabel("New label");
	private JLabel label_25 = new JLabel("New label");
	private JLabel label_26 = new JLabel("New label");
	private JLabel lblNewLabel_13 = new JLabel("New label");
	private JLabel label_27 = new JLabel("New label");
	private JPanel panel = new JPanel();
	private JPanel panel_6 = new JPanel();
	private JLabel lblPresionAtmosfrica_1 = new JLabel("Presi\u00F3n atmosf\u00E9rica");
	private JLabel lblHumedadRelativa_1 = new JLabel("Humedad relativa");
	private JLabel lblNewLabel_15 = new JLabel("New label");
	private JLabel label_31 = new JLabel("New label");
	private JLabel label_32 = new JLabel("New label");
	private JLabel label_33 = new JLabel("New label");
	private JPanel panel_7 = new JPanel();
	private JLabel lblNewLabel_16 = new JLabel("TENDENCIAS");
	private JLabel lblNewLabel_11 = new JLabel("VESPERTINO");
	private JLabel lblNewLabel_12 = new JLabel("MATUTINO");
	private JLabel lblNewLabel_14 = new JLabel("Temperatura del agua");
	private JLabel lblTemperaturaDelAire = new JLabel("Temperatura del aire");
	JTextArea jTextAreaLecturasDirectas = new JTextArea();
}
