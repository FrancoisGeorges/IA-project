package application;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;

import java.awt.GridLayout;


public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldDepart;
	private JTextField textFieldArrivee;
	private int DEFAULT_DIMENSION = 6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**** PARTIE GENERALE ****/
		// Déclaration des modèles pour les spinners (valeurs possibles)
		
		SpinnerNumberModel modelDimensions = new SpinnerNumberModel(DEFAULT_DIMENSION, 0.0, 10.0, 1.0);
		SpinnerNumberModel modelRecompenseArrivee = new SpinnerNumberModel(5.0, 0.0, 20.0, 0.5);
		SpinnerNumberModel modelRecompenseMarecage = new SpinnerNumberModel(-1.0, -20.0, 0.0, 0.5);
		SpinnerNumberModel modelCaseX = new SpinnerNumberModel(0.0, 0.0, null, 1.0);/*a completer*/
		SpinnerNumberModel modelCaseY = new SpinnerNumberModel(0.0, 0.0, null, 1.0);/*a completer*/
		
		/**** PARTIE Configuration ****/
		JLabel lblConfiguration = new JLabel("Configuration");
		lblConfiguration.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguration.setBounds(80, 9, 87, 16);
		contentPane.add(lblConfiguration);
		
		JSeparator separatorConfiguration = new JSeparator();
		separatorConfiguration.setBounds(0, 20, 250, 10);
		contentPane.add(separatorConfiguration);
		
		JLabel lblDepart = new JLabel("Départ :");
		lblDepart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepart.setBounds(28, 53, 76, 16);
		contentPane.add(lblDepart);
		
		JSpinner spinnerDepartX = new JSpinner(modelCaseX);
		spinnerDepartX.setBounds(116, 47, 46, 28);
		contentPane.add(spinnerDepartX);
		
		JSpinner spinnerDepartY = new JSpinner(modelCaseY);
		spinnerDepartY.setBounds(162, 47, 46, 28);
		contentPane.add(spinnerDepartY);
		
		/*textFieldDepart = new JTextField();
		lblDepart.setLabelFor(textFieldDepart);
		textFieldDepart.setBounds(116, 47, 93, 28);
		contentPane.add(textFieldDepart);
		textFieldDepart.setColumns(10);*/
		
		JLabel lblArrivee = new JLabel("Arrivée :");
		lblArrivee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArrivee.setBounds(43, 93, 61, 16);
		contentPane.add(lblArrivee);
		
		JSpinner spinnerArriveeX = new JSpinner(modelCaseX);
		spinnerArriveeX.setBounds(116, 87, 46, 28);
		contentPane.add(spinnerArriveeX);
		
		JSpinner spinnerArriveeY = new JSpinner(modelCaseY);
		spinnerArriveeY.setBounds(162, 87, 46, 28);
		contentPane.add(spinnerArriveeY);
		
		/*textFieldArrivee = new JTextField();
		lblArrivee.setLabelFor(textFieldArrivee);
		textFieldArrivee.setColumns(10);
		textFieldArrivee.setBounds(116, 87, 93, 28);
		contentPane.add(textFieldArrivee);*/
		
		/**** PARTIE Recompenses ****/
		
		JLabel lblRecompenses = new JLabel("Récompenses");
		lblRecompenses.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecompenses.setBounds(80, 251, 87, 16);
		contentPane.add(lblRecompenses);
		
		JSeparator separatorRecompenses = new JSeparator();
		separatorRecompenses.setBounds(0, 262, 250, 10);
		contentPane.add(separatorRecompenses);
		
		JLabel lblRecompenseArrivee = new JLabel("Arrivée :");
		lblRecompenseArrivee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecompenseArrivee.setBounds(43, 279, 61, 28);
		contentPane.add(lblRecompenseArrivee);
		
		JSpinner spinnerRecompenseArrivee = new JSpinner(modelRecompenseArrivee);
		lblRecompenseArrivee.setLabelFor(spinnerRecompenseArrivee);
		spinnerRecompenseArrivee.setBounds(116, 279, 93, 28);
		contentPane.add(spinnerRecompenseArrivee);
		
		JLabel lblRecompenseMarecage = new JLabel("Marécage :");
		lblRecompenseMarecage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecompenseMarecage.setBounds(28, 325, 76, 16);
		contentPane.add(lblRecompenseMarecage);
		
		JSpinner spinnerRecompenseMarecage = new JSpinner(modelRecompenseMarecage);
		lblRecompenseMarecage.setLabelFor(spinnerRecompenseMarecage);
		spinnerRecompenseMarecage.setBounds(116, 319, 93, 28);
		contentPane.add(spinnerRecompenseMarecage);
		
		/***** PARTIE Dimensions ****/
		
		JLabel lblDimensions = new JLabel("Dimensions");
		lblDimensions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDimensions.setBounds(80, 121, 87, 16);
		contentPane.add(lblDimensions);
		
		JSeparator separatorDimensions = new JSeparator();
		separatorDimensions.setBounds(0, 132, 250, 10);
		contentPane.add(separatorDimensions);
		
		JLabel lblDimensionX = new JLabel("X :");
		lblDimensionX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDimensionX.setBounds(28, 149, 76, 28);
		contentPane.add(lblDimensionX);
		
		
		
		
		
		JSpinner spinnerDimensionX = new JSpinner(modelDimensions);
		spinnerDimensionX.setBounds(116, 149, 93, 28);
		contentPane.add(spinnerDimensionX);
		
		JLabel lblDimensionY = new JLabel("Y :");
		lblDimensionY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDimensionY.setBounds(28, 195, 76, 16);
		contentPane.add(lblDimensionY);
		
		JSpinner spinnerDimensionY = new JSpinner(modelDimensions);
		spinnerDimensionY.setBounds(116, 189, 93, 28);
		contentPane.add(spinnerDimensionY);
		
		panel = new JPanel();
		panel.setBounds(300, 20, 333, 397);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(DEFAULT_DIMENSION, DEFAULT_DIMENSION, 0, 0));
		
		/*JButton test = new JButton("Test");
		JButton test2 = new JButton("Test2");
		panel.add(test);
		panel.add(test2);
		for (int i = 0; i < 2; i++) {
			JButton test3 = new JButton("Test2");
			panel.add(test3);
			spinnerArriveeX.setValue(4);
		}*/
		//System.out.println("bonsoir");
	    for (int row = 0; row < DEFAULT_DIMENSION; row++) {
	    	//System.out.println("bonsoiir");
		      for (int col = 0; col < DEFAULT_DIMENSION; col++) {
		    	  System.out.println("[" + Integer.toString(row+1) + ", " + Integer.toString(col+1) + "]");
		        JButton btn = new JButton("[" + Integer.toString(row+1) + ", " + Integer.toString(col+1) + "]");
		        panel.add(btn);
		      }
		    }
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void createButtons() {
		System.out.println("");

	}
}
