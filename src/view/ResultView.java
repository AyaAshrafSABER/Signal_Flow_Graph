package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.IDrawingSignalFlowGraph;
import graph.analysis.Mason;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ResultView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField OverAllTF;
	private JTextField GeneralDelta;
	private JList<String> forwardpaths;
	private JList<String> Loops;
	private JList<String> smallDelta;
	private JList<String> NonTouchingLoops;

	/**
	 * Create the frame.
	 */
	public ResultView(IDrawingSignalFlowGraph drawSignalFlowGraph) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JButton returnToFirstpage = new JButton("New Graph");
		returnToFirstpage.setBackground(new Color(250, 235, 215));
		returnToFirstpage.addActionListener(new ActionListener() {
			StartWindow frame = new StartWindow();

			public void actionPerformed(ActionEvent arg0) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		Mason mason = drawSignalFlowGraph.displayReslut();
		String[] allLoops = dispLoops(mason.getAllLoops());
		String[] forwardPaths = dispFP(mason.getFPs());
		String[] smallDelta2 = dispSmallDelta(mason.getPathDelta());
		mason.getnonTouchingLoops();
		String[] nonTouchingLoops = dispNonTouching(mason.getnonTouchingLoops());
		String generalDelta = mason.getGeneralDelta();
		double overTF = mason.getOATF();

		forwardpaths = new JList<String>(forwardPaths);
		forwardpaths.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		forwardpaths.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		forwardpaths.setBackground(SystemColor.activeCaption);
		Loops = new JList<String>(allLoops);
		Loops.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		Loops.setBackground(SystemColor.desktop);
		Loops.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Loops.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		Loops.setBackground(SystemColor.activeCaption);

		smallDelta = new JList<String>(smallDelta2);
		smallDelta.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		smallDelta.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		smallDelta.setBackground(SystemColor.activeCaption);
		JLabel lblNewLabel = new JLabel("Over All\r\n Transfer Function");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 228, 181));

		OverAllTF = new JTextField();
		OverAllTF.setBackground(Color.WHITE);
		OverAllTF.setEditable(false);
		OverAllTF.setHorizontalAlignment(SwingConstants.CENTER);
		OverAllTF.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		OverAllTF.setColumns(10);
		OverAllTF.setText(Double.toString(overTF));

		NonTouchingLoops = new JList<String>(nonTouchingLoops);
		NonTouchingLoops.setBackground(SystemColor.activeCaption);

		JLabel lblNewLabel_1 = new JLabel("Forward Paths");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));

		JLabel lblNewLabel_2 = new JLabel("Loops");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));

		JLabel lblNewLabel_3 = new JLabel("Small Delta ");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_4 = new JLabel("Non Touching Loops");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));

		JLabel lblBigDelta = new JLabel("Big Delta");
		lblBigDelta.setHorizontalAlignment(SwingConstants.CENTER);
		lblBigDelta.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));

		GeneralDelta = new JTextField();
		GeneralDelta.setBackground(Color.WHITE);
		GeneralDelta.setEditable(false);
		GeneralDelta.setColumns(10);
		GeneralDelta.setText(generalDelta);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(forwardpaths, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Loops, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(smallDelta, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 140,
										GroupLayout.PREFERRED_SIZE)
								.addGap(36)))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(NonTouchingLoops, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
								.addComponent(returnToFirstpage, GroupLayout.PREFERRED_SIZE, 120,
										GroupLayout.PREFERRED_SIZE)
								.addGap(65))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(4).addComponent(OverAllTF).addGap(57))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblBigDelta, GroupLayout.DEFAULT_SIZE, 123,
														Short.MAX_VALUE)
												.addGap(2))
										.addComponent(GeneralDelta, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
								.addGap(62))
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(41)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(32).addGroup(gl_contentPane
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
								.createSequentialGroup().addGap(76).addComponent(lblBigDelta)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(GeneralDelta, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(137)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addGap(72)
								.addComponent(OverAllTF, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(25, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(forwardpaths, GroupLayout.DEFAULT_SIZE, 438,
														Short.MAX_VALUE)
												.addComponent(Loops, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
												.addComponent(smallDelta, GroupLayout.DEFAULT_SIZE, 438,
														Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(returnToFirstpage, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4)).addGap(15)
								.addComponent(NonTouchingLoops, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)))));
		contentPane.setLayout(gl_contentPane);
	}

	private String[] dispNonTouching(List<List<String>> getnonTouchingLoops) {
		ArrayList<String> non = new ArrayList<String>();
		for (int i = 0; i < getnonTouchingLoops.size(); i++) {
			if (!getnonTouchingLoops.get(i).isEmpty()) {
				non.add((i + 2) + "non touching Loops");
				String s = ""; 
				for (int j = 0; j < getnonTouchingLoops.get(i).size(); j++) {
					for (int k = 0; k < i +2 ; k++) {
						s += getnonTouchingLoops.get(i).get(j) + " ";
						j++;
					}
					non.add(s);
					s = "";
				}
				
			}else {
				break;
			}
		}

		String[] ret = new String[non.size()];
		for (int i = 0; i < non.size(); i++) {
			ret[i] = non.get(i).toString();
		}
		return ret;
	}

	private String[] dispSmallDelta(List<Double> smallDelta) {
		String[] ret = new String[smallDelta.size()];
		for (int i = 0; i < smallDelta.size(); i++) {
			ret[i] = smallDelta.get(i).toString();
		}
		return ret;

	}

	private String[] dispFP(List<String> forwardPaths) {
		String[] ret = new String[forwardPaths.size()];
		for (int i = 0; i < forwardPaths.size(); i++) {
			ret[i] = forwardPaths.get(i);
		}
		return ret;

	}

	private String[] dispLoops(List<String> allLoops) {
		String[] ret = new String[allLoops.size()];
		for (int i = 0; i < allLoops.size(); i++) {
			ret[i] = allLoops.get(i);
		}
		return ret;
	}
}
