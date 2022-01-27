package app;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import amostra.Amostra;
import redes_bayes.BN;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Interface2 {
	
	BN bn;

	private JFrame frame;
	private JTextField txtInput;
	private JTextField txtOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface2 window = new Interface2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		//ler a rede de bayes do disco
		
		JButton btnreset = new JButton("Reset");
		btnreset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtInput.setText("Input"); //pomos a string entre aspas
				txtOutput.setText("Output");
			}
		});
		
		btnreset.setBounds(322, 25, 89, 23);
		frame.getContentPane().add(btnreset);
		
		JButton btnrun = new JButton("Calculate");
		btnrun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//String dm = txtInput.getText();
				String tb = txtInput.getText();
				String exp0 = tb.concat(",0");
				String exp1 = tb.concat(",1");
				
				//txtOutput.setText(exp);
				
				//falta dar a rede de bayes
				
				int[] tb1 = Arrays.stream(exp0.split(",")).mapToInt(Integer::parseInt).toArray();
				int[] dm1 = Arrays.stream(exp1.split(",")).mapToInt(Integer::parseInt).toArray();
				
				
				if (bn.prob(dm1)>bn.prob(tb1)) {
					txtOutput.setText("Ups");
				}
				else{
					txtOutput.setText("Tass bem");
				}
				
				
				
			}
		});
		btnrun.setBounds(166, 166, 89, 23);
		frame.getContentPane().add(btnrun);
		
		JButton btnNewButton = new JButton("Read Object");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser2 = new JFileChooser();
				fileChooser2.setDialogTitle("Object to open");   

				int r = fileChooser2.showOpenDialog((Component)e.getSource());

				if (r == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser2.getSelectedFile();
					FileInputStream fileIn; //o que e isto
					try {
						fileIn = new FileInputStream(fileToSave);
						ObjectInputStream objectIn = new ObjectInputStream(fileIn);
						bn = (BN)objectIn.readObject();
						objectIn.close();
						fileIn.close();

					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblNewLabel.setText("Sucess");
				}
				else lblNewLabel.setText("Error");
			}
		});
		btnNewButton.setBounds(26, 29, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		txtInput = new JTextField();
		txtInput.setText("Input");
		//como e que sabe o tamanho do vetor que tem que receber?
		//le a amostra antes?
		//como le a amostra?
		txtInput.setBounds(79, 66, 101, 23);
		frame.getContentPane().add(txtInput);
		txtInput.setColumns(10);
		
		txtOutput = new JTextField();
		txtOutput.setText("Output");
		txtOutput.setBounds(169, 216, 86, 20);
		frame.getContentPane().add(txtOutput);
		txtOutput.setColumns(10);
	}
}
