package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DrawingSignalFlowGraph;
import Controller.IDrawingSignalFlowGraph;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private boolean begin;
	private int numberOFNodes;
	private IDrawingSignalFlowGraph drawSignalFlowGraph;
	/**
	 * Create the frame.
	 */
	public StartWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Enter Total Number of Nodes");
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));

		textField = new JTextField();
		textField.setColumns(10);

		btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					numberOFNodes = Integer.parseInt(textField.getText());
					drawSignalFlowGraph = new DrawingSignalFlowGraph(numberOFNodes);
					dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {

								GUI window = new GUI(drawSignalFlowGraph);
								window.getFrame().setVisible(true);								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(),  "Not allawed to enter charecters \n Enter only numbers ", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(51).addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(110)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField, Alignment.TRAILING))))
				.addContainerGap(95, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28).addComponent(lblNewLabel).addGap(18)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(32)
						.addComponent(btnNewButton).addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * @return order to end the start windo and begin the gui
	 */
	public boolean SartGui() {
		return begin;
	}
}
