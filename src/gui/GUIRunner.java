package gui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import bus.BusHub;

public class GUIRunner {

	public static void main(String[] args) {
		BusHub busHub = new BusHub();
		
		busHub.addBus("J", "http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=chapel-hill&r=J&s=joneabbe");
		busHub.addBus("CW", "http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=chapel-hill&r=CW&s=davifide_o");
		createAndShowGUI(busHub);
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(busHub::getBusSchedule, 0, 1, TimeUnit.SECONDS);
	}
	
	public static void createAndShowGUI(BusHub busHub) {
		BusGUI busGUI = new BusGUI(busHub);
		busGUI.addFrames();
		//JLabel label = new JLabel
	}

}
