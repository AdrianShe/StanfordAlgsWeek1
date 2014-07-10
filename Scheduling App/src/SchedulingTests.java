import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SchedulingTests {
	
	Schedule s;
	ScheduleItem s1;
	ScheduleItem s2;
	ScheduleItem s3;
	ScheduleItem s4;
	ScheduleItem s5;
	ScheduleItem s6;
	List<ScheduleItem> los = new ArrayList<ScheduleItem>();
	
	/*
	 * Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference (weight - length). 
	 * Recall from lecture that this algorithm is not always optimal. 
	 * IMPORTANT: if two jobs have equal difference (weight - length), 
	 * you should schedule the job with higher weight first. 
	 * Beware: if you break ties in a different way, you are likely to get the wrong
	 *  answer. 
	 *  You should report the sum of weighted completion times of the resulting schedule 
	 *  --- a positive integer --- in the box below.

	 */
	@Before
	public void onBefore() {
		 s1 = new ScheduleItem(5, 2); // 3 2.5
		 s2 = new ScheduleItem(6, 5); // 1 1.2
		 s3 = new ScheduleItem(7, 2); // 5 3.5
		 s4 = new ScheduleItem(7, 6); // 1 1.16
		 s5 = new ScheduleItem(8, 4); // 4 2
		 s6 = new ScheduleItem(6, 3); // 3 2
		 los.add(s1);
		 los.add(s2);
		 los.add(s3);
		 los.add(s4);
		 los.add(s5);
		 los.add(s6);
	}
	
	@Test
	public void testItem() {
		assertEquals(5, s1.getWeight(), 0.1);
		assertEquals(2, s1.getLength(), 0.1);
		assertEquals(3, s1.getDiffCost(), 0.1);
		assertEquals(2.5, s1.getRatioCost(), 0.01);
	}
	
	@Test
	public void testDifferenceScheduling() {
		s = new DifferenceSchedule(los);
		Schedule sorted = s.sortJobs();
		List<ScheduleItem> items = sorted.getScheduleItems();
		assertEquals(items.get(0), s3);
		assertEquals(items.get(1), s5);
		assertEquals(items.get(2), s6);
		assertEquals(items.get(3), s1);
		assertEquals(items.get(4), s4);
		assertEquals(items.get(5), s2);
		int cost = 14 + 48 + 54 + 55 + (17 * 7) + (22 * 6);
		assertEquals(sorted.calculateCost(), cost, 0.1);
	}
	
	@Test
	public void testRatioScheduling() {
		s = new RatioSchedule(los);
		Schedule sorted = s.sortJobs();
		List<ScheduleItem> items = sorted.getScheduleItems();
		for (ScheduleItem si: items) {
			System.out.println(si);
		}
		assertEquals(items.get(0), s3);
		assertEquals(items.get(1), s1);
		assertEquals(items.get(2), s5);
		assertEquals(items.get(3), s6);
		assertEquals(items.get(4), s2);
		assertEquals(items.get(5), s4);
		int cost = 14 + 20 + 64 + 66 + 96 + (22 * 7);
		assertEquals(sorted.calculateCost(), cost, 0.1);
	}

}
