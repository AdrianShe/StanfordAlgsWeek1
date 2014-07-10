import java.util.ArrayList;
import java.util.List;


public class RatioSchedule extends Schedule {

	public RatioSchedule() {
		super();
	}
	
	public RatioSchedule(List<ScheduleItem> los) {
		super(los);
	}

	@Override
	public Schedule sortJobs() {
		List<ScheduleItem> sorted = sortByRatioCost(this.getScheduleItems());
		return new RatioSchedule(sorted);
	}

	private List<ScheduleItem> sortByRatioCost(List<ScheduleItem> items) {
		if (items.size() <= 1) {
			return items;
		}
		else {
			// Mergesort type algorithm
			int size = items.size();
			List<ScheduleItem> leftitems = new ArrayList<ScheduleItem>();
			List<ScheduleItem> rightitems = new ArrayList<ScheduleItem>();
			for (int i = 0; i < size/2; i++) {
				leftitems.add(items.get(i));
			}
			for (int i = size/2; i < size; i++) {
				rightitems.add(items.get(i));
			}
			List<ScheduleItem> leftsorted = sortByRatioCost(leftitems);
			List<ScheduleItem> rightsorted = sortByRatioCost(rightitems);
			List<ScheduleItem> sorted = new ArrayList<ScheduleItem>();
			// merge subroutine
			while (leftsorted.size() > 0 || rightsorted.size() > 0) {
				if (leftsorted.size() == 0) {
					sorted.addAll(rightsorted);
					rightsorted.clear();
				}
				else if (rightsorted.size() == 0) {
					sorted.addAll(leftsorted);
					leftsorted.clear();
				}
				else {
					ScheduleItem leftitem = leftsorted.get(0);
					ScheduleItem rightitem = rightsorted.get(0);
					if (leftitem.getRatioCost() < rightitem.getRatioCost()) {
						sorted.add(rightitem);
						rightsorted.remove(0);
					}
					else {
						sorted.add(leftitem);
						leftsorted.remove(0);
					}
				}
			}
			
			return sorted;
			
	}
 }

}
