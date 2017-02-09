package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bus.Bus;
import bus.BusHub;

public class BusGUI extends JFrame{
	private BusHub busHub;
	private JLabel[] left;
	private JLabel[] right;
	private JPanel mainPane;
	private List<JPanel> busPanelList;
	
	public BusGUI(BusHub bh) {
		busHub = bh;
		busPanelList = new ArrayList<JPanel>();
		left = new JLabel[busHub.getBusList().size()];
		right = new JLabel[left.length];
		mainPane = new JPanel();
		mainPane.setLayout(null);
		mainPane.setPreferredSize(new Dimension(300, 300));
		add(mainPane);
		//this.addFrames();
		pack();
		setTitle("BusApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addFrames() {
		int index = 0;
		for(Bus bus : busHub.getBusList()) {
			left[index] = new JLabel(bus.getName());
			right[index] = new JLabel();
			System.out.println(left[index].isDisplayable());
			System.out.println(left[index].isEnabled());
			//right[index].setText(bus.getSchedule());
			//add(left[index]);
		}
	}
}
