
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static Schedule diffSchedule;
	static Schedule ratioSchedule;
	static List<ScheduleItem> los = new ArrayList<ScheduleItem>();
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File jobs = new File("jobs.txt");
			readFile(jobs);
			
			System.out.println("Scheduling Items according to their difference");
			System.out.println("The raw cost is");
			diffSchedule = new DifferenceSchedule(los);
			System.out.println(diffSchedule.calculateCost());
			Schedule sorted = diffSchedule.sortJobs();
			System.out.println("The cost is" + sorted.calculateCost());
			
			System.out.println("Scheduling Items according to their ratio");
			System.out.println("The raw cost is");
			ratioSchedule = new RatioSchedule(los);
			System.out.println(ratioSchedule.calculateCost());
			Schedule ratiosorted = ratioSchedule.sortJobs();
			System.out.println("The cost is" + ratiosorted.calculateCost());
			
			System.out.println("Resorting the difference schedule: ");
			Schedule diffratio = new RatioSchedule(sorted.getScheduleItems());
			Schedule resorted = diffratio.sortJobs();
			System.out.println("The cost is" + resorted.calculateCost());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void readFile(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] result = line.split("\\s");
			ScheduleItem si = new ScheduleItem(Integer.parseInt(result[0]), Integer.parseInt(result[1]));
			los.add(si);
		}
	 
		br.close();
	}

}
