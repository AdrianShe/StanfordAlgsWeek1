import java.util.ArrayList;
import java.util.List;




public class DifferenceSchedule extends Schedule {

	public DifferenceSchedule() {
		super();
	}
	
	public DifferenceSchedule(List<ScheduleItem> los) {
		super(los);
	}
	

	@Override
	public Schedule sortJobs() {
		List<ScheduleItem> sorted = sortByDiffCost(this.getScheduleItems());
		return new DifferenceSchedule(sorted);
	}
	
	// Quick-sort type algorithm to sort list of schedule items by 
	// cost of difference
	
	private List<ScheduleItem> sortByDiffCost(List<ScheduleItem> items) {
		if (items.size() <= 1) {
			return items;
		}
		else {
			// Pick an arbitrary pivot  
			ScheduleItem firstItem = items.get(0);
			double cost = firstItem.getDiffCost();
			double weight = firstItem.getWeight();
			
			// Partition the array
			items.remove(0);
			List<ScheduleItem> lesscost = new ArrayList<ScheduleItem>();
			List<ScheduleItem> morecost = new ArrayList<ScheduleItem>();
			for (ScheduleItem si: items) {
				double curCost = si.getDiffCost();
				double curWeight = si.getWeight();
				if (curCost > cost) {
					morecost.add(si);
				}
				else if (curCost == cost) {
					if (curWeight > weight) {
						morecost.add(si);
					}
					else {
						lesscost.add(si);
					}
				}
				else {
					lesscost.add(si);
				}
			}
			
			// Recursively sort partitions
			lesscost = this.sortByDiffCost(lesscost);
			morecost = this.sortByDiffCost(morecost);
			
			// Recombine partitions
			List<ScheduleItem> sorted = new ArrayList<ScheduleItem>();
			sorted.addAll(morecost);
			sorted.add(firstItem);
			sorted.addAll(lesscost);
			return sorted;
		}
	}
}
