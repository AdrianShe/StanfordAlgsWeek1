
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Schedule implements Iterable<ScheduleItem> {
	
	List<ScheduleItem> schedule;
	
	public Schedule() {
		schedule = new ArrayList<ScheduleItem>();
	}
	
	public Schedule(List<ScheduleItem> los) {
		schedule = los;
	}
	
	public abstract Schedule sortJobs();
	
	public void addItem(ScheduleItem si) {
		schedule.add(si);
	}
	
	public double calculateCost() {
		double cost = 0;
		double length = 0;
		for (ScheduleItem si: schedule) {
			length += si.getLength();
			cost = cost + (si.getWeight() * length);
		}
		return cost;
	}

	public Iterator<ScheduleItem> iterator() {
		return schedule.iterator();
	}
	
	public List<ScheduleItem> getScheduleItems() {
		return schedule;
	}
	

}
