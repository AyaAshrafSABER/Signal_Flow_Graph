package view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controller.IDrawingSignalFlowGraph;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Canvas;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmSignalFlowGrapj;
	private JLabel lblDestination;
	private IDrawingSignalFlowGraph drawSignalFlowGraph;
	private JLabel label;
	public Canvas canvas_1;
	private JTextField source;
	private JTextField destination;
	private JLabel lblNewLabel;
	private JLabel lblNewLabe2;
	private Draw draw;

	public JFrame getFrame() {
		return this.frmSignalFlowGrapj;

	}

	/**
	 * Create the application.
	 * 
	 * @param drawSignalFlowGraph
	 */
	public GUI(IDrawingSignalFlowGraph drawSignalFlowGraph) {
		initialize();
		this.drawSignalFlowGraph = drawSignalFlowGraph;
		this.draw = new Draw(frmSignalFlowGrapj, drawSignalFlowGraph);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignalFlowGrapj = new JFrame();
		frmSignalFlowGrapj.getContentPane().setForeground(new Color(204, 255, 255));
		frmSignalFlowGrapj.setTitle("Signal Flow Graph");
		frmSignalFlowGrapj.getContentPane().setBackground(new Color(135, 206, 235));

		canvas_1 = new Canvas();
		canvas_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				draw.paintComponent(canvas_1.getGraphics());
				drawSignalFlowGraph.refresh(canvas_1.getGraphics());
			}
		});

		canvas_1.setBackground(new Color(255, 255, 240));

		lblNewLabel = new JLabel("Source");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 15));

		lblNewLabe2 = new JLabel("Distination");
		lblNewLabe2.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		source = new JTextField();
		source.setColumns(10);

		destination = new JTextField();
		destination.setColumns(10);

		JButton btnNewButton_1 = new JButton("Add Edge ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				draw.drawEdge(canvas_1.getGraphics(), source, destination);
			}
		});
		
		JButton btnFinish = new JButton("Solve SFG");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSignalFlowGrapj.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ResultView frame = new ResultView(drawSignalFlowGraph);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSignalFlowGrapj.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(source, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabe2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(destination, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton_1)
					.addGap(60)
					.addComponent(btnFinish, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(175, Short.MAX_VALUE))
				.addComponent(canvas_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(source, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabe2)
						.addComponent(destination, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFinish))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(canvas_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frmSignalFlowGrapj.getContentPane().setLayout(groupLayout);
		frmSignalFlowGrapj.setBounds(100, 100, 739, 497);
		frmSignalFlowGrapj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton addEdge = new JButton("Add Edge");
		addEdge.setForeground(new Color(0, 0, 128));
		addEdge.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addEdge.setToolTipText("");
		addEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				draw.paintComponent(canvas_1.getGraphics());

			}
		});
		addEdge.setBackground(new Color(135, 206, 235));

		lblDestination = new JLabel("Destination ");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestination.setForeground(Color.BLUE);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUndo.setForeground(new Color(0, 0, 128));
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUndo.setBackground(new Color(135, 206, 235));

		JButton btnNewButton = new JButton("Redo");
		btnNewButton.setBackground(new Color(135, 206, 235));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setForeground(new Color(0, 0, 128));

		label = new JLabel("source");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
	}
}
