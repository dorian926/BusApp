import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
//import java.util.Date;
import java.net.MalformedURLException;

public class Schedule {
	URL url;
	public Schedule(String link) {
		url = setLink(link);
	}
	
	public void getSchedule() throws IOException{
		BufferedReader xmlFile = getUpdatedSchedule();
		int count = 0;
		int hours1 = 0, minutes1 = 0, seconds1 = 0;
		int hours2 = 0, minutes2 = 0, seconds2 = 0;
		//Date day = null;
			String line;
			while((line = xmlFile.readLine()) != null) {
				if(line.contains("seconds")) {
					int index1 = 0, index2 = 0;
					if(count == 0) {
						index1 = line.indexOf("epochTime") + 11;
						index2 = index1 + 7;
						
						//day = new Date(Long.parseLong(line.substring(index1, index2)));
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
	}
	
	private void printArrivalTime(String b, int s, int m, int h) {
		System.out.print(b + " will arrive in: ");
		if(h != 0) {
			System.out.print(h + " Hours ");
		}
		if(m != 0) {
			System.out.print(m + " Minutes ");
		}
		System.out.println(s + " Seconds");
	}
	
	private URL setLink(String link) {
		try {
			return new URL(link);
		}catch(MalformedURLException e) {
			System.out.println("Error obtaining link. Check address as retry");
		}
		return null;
	}
	private BufferedReader getUpdatedSchedule() {
		try {
			return new BufferedReader(new InputStreamReader(url.openStream()));
		}catch(IOException ioe) {
			System.out.println("Error opening the url stream.");
		}
		return null;
	}
}
