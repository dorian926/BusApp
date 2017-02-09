package bus;
import java.io.IOException;

public class Bus {
	private String name;
	private Schedule schedule;
	public Bus(String n,String l) {
		name = n;
		schedule = new Schedule(l);
	}
	
	public String getName() {
		return name;
	}
	
	public void printSchedule(){
		System.out.println(name);
		try {
			schedule.getSchedule();
		} catch (IOException e) {
			System.out.println("Error obtaining schedule");
		}
	}
	/*
	public String getSchedule(){
		try {
			schedule.getSchedule();
		} catch (IOException e) {
			System.out.println("Error obtaining schedule");
		}
	}
	*/
	
}
