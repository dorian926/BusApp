package bus;

import java.util.ArrayList;
import java.util.List;

public class BusHub {
	private List<Bus> buses;
	public BusHub() {
		buses = new ArrayList<Bus>();
	}
/*
	public static void main(String[] args) {
		buses = new ArrayList<Bus>();
		buses.add(new Bus("J", "http://webservicess.nextbus.com/service/publicXMLFeed?command=predictions&a=chapel-hill&r=J&s=joneabbe"));
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(BusHub::getBusSchedule, 0, 1, TimeUnit.SECONDS);
	}
*/
	public void getBusSchedule() {
		buses.forEach(Bus::printSchedule);
	}
	
	public List<Bus> getBusList() {
		return buses;
	}
	public void addBus(String name, String link) {
		buses.add(new Bus(name, link));
	}

}