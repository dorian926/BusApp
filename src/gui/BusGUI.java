package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bus.Bus;
import bus.BusHub;

public class BusGUI extends JFrame{
	private BusHub busHub;
	private JLabel[] left;
	private JLabel[] right;
	
	public BusGUI(BusHub bh) {
		busHub = bh;
		
		left = new JLabel[busHub.getBusList().size()];
		right = new JLabel[left.length];
		this.addFrames();
		
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
			add(left[index]);
		}
	}
}
