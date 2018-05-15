package view;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.IDrawingSignalFlowGraph;

public class Draw extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmSignalFlowGrapj;
	private IDrawingSignalFlowGraph drawSignalFlowGraph;

	public Draw(JFrame frmSignalFlowGrapj, IDrawingSignalFlowGraph drawSignalFlowGraph) {
		this.drawSignalFlowGraph = drawSignalFlowGraph;
		this.frmSignalFlowGrapj = frmSignalFlowGrapj;
	}

	public void paintComponent(Graphics g) {
		float xStart = frmSignalFlowGrapj.getAlignmentX();
		float yStart = frmSignalFlowGrapj.getAlignmentY();
		float width = frmSignalFlowGrapj.getWidth() - 10;
		float height = frmSignalFlowGrapj.getHeight() - 100;
		frmSignalFlowGrapj.getAlignmentX();
		super.paintComponents(g);
		drawSignalFlowGraph.addNodes(g, xStart, yStart, width, height);
	}

	public void drawEdge(Graphics g, JTextField from, JTextField to) {
		try {
			int source = Integer.parseInt(from.getText());
			int destination = Integer.parseInt(to.getText());
			int gain =Integer.parseInt(JOptionPane.showInputDialog("Please Enter the gain: "));
			if(source == drawSignalFlowGraph.getNumberOFNodes() - 1) {
				JOptionPane.showMessageDialog(new JFrame(), "Error !!! Output Node hss only incoming branches",
						"Dialog", JOptionPane.ERROR_MESSAGE);
				
			}
			else if (destination == 0){
				JOptionPane.showMessageDialog(new JFrame(), "Error !!! Input Node hss only outcoming branches",
						"Dialog", JOptionPane.ERROR_MESSAGE);
				
			} else {
				 drawSignalFlowGraph.addEdge(source, destination, gain);
				 drawSignalFlowGraph.refresh(g);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error !!!Please, check your input",
					"Dialog", JOptionPane.ERROR_MESSAGE);
		}
	}
}
