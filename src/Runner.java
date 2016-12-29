import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {
	private static List<Bus> buses;
	public static void main(String[] args) {
		buses = new ArrayList<Bus>();
		buses.add(new Bus("J", "http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=chapel-hill&r=J&s=joneabbe"));
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(Runner::getBusSchedule, 0, 1, TimeUnit.SECONDS);
	}
	private static void getBusSchedule() {
		buses.forEach(Bus::printSchedule);
	}
	public static void getRouteSchedule() {
		URL url = null;
		try {
			url = new URL("http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=chapel-hill&r=J&s=joneabbe");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(url == null) {
			System.out.println("URL failed");
			System.exit(-1);
		}
		
		BufferedReader xmlFile = getBReader(url);
		if(xmlFile == null) {
			System.out.println("Error: did not obtain");
			System.exit(-1);
		}
		int count = 0;
		int hours1 = 0, minutes1 = 0, seconds1 = 0;
		int hours2 = 0, minutes2 = 0, seconds2 = 0;
		Date day = null;
		try {
			String line;
			while((line = xmlFile.readLine()) != null) {
				if(line.contains("seconds")) {
					int index1 = 0, index2 = 0;
					if(count == 0) {
						index1 = line.indexOf("epochTime") + 11;
						index2 = index1 + 7;
						
						day = new Date(Long.parseLong(line.substring(index1, index2)));
					}
					count++;
					if(count == 1) {
						index1 = line.indexOf("seconds") + 9;
						index2 = line.indexOf("minutes") - 2;
						seconds1 = Integer.parseInt(line.substring(index1,index2)) % 60;
						
						minutes1 = Integer.parseInt(line.substring(index1,index2)) / 60 % 60;
						
						hours1 = Integer.parseInt(line.substring(index1,index2)) / 60 / 60;
						
						printArrivalTime("Bus1", seconds1, minutes1, hours1);
					} else
					if (count == 2) {
						index1 = line.indexOf("seconds") + 9;
						index2 = line.indexOf("minutes") - 2;
						seconds2 = Integer.parseInt(line.substring(index1,index2)) % 60;
						
						minutes2 = Integer.parseInt(line.substring(index1,index2)) / 60 % 60;
						
						hours2 = Integer.parseInt(line.substring(index1,index2)) / 60 / 60;
						
						printArrivalTime("Bus2", seconds2, minutes2, hours2);
						break;
					}
				}
			}
			xmlFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static BufferedReader getBReader(URL url) {
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				return br;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

	}
	
	public static void setArrivalTime(int index1, int index2, String l, int s, int m, int h ) {
		index1 = l.indexOf("seconds") + 9;
		index2 = l.indexOf("minutes") - 2;
		s = Integer.parseInt(l.substring(index1,index2)) % 60;
		System.out.println(s);
		m = Integer.parseInt(l.substring(index1,index2)) / 60 % 60;
		
		h = Integer.parseInt(l.substring(index1,index2)) / 60 / 60;
	}
	
	public static void printArrivalTime(String b, int s, int m, int h) {
		System.out.print(b + " will arrive in: ");
		if(h != 0) {
			System.out.print(h + " Hours ");
		}
		if(m != 0) {
			System.out.print(m + " Minutes ");
		}
		System.out.println(s + " Seconds");
	}

}
