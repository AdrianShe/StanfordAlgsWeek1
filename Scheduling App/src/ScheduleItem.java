
public class ScheduleItem {
	
	double weight;
	double length;
	double diffCost;
	double ratioCost;
	
	public ScheduleItem(double w, double l) {
		weight = w;
		length = l;
		diffCost = w - l;
		ratioCost =  (w / l);
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getLength() {
		return length;
	}

	public double getDiffCost() {
		return diffCost;
	}

	public double getRatioCost() {
		return ratioCost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleItem other = (ScheduleItem) obj;
		if (diffCost != other.diffCost)
			return false;
		if (length != other.length)
			return false;
		if (Double.doubleToLongBits(ratioCost) != Double
				.doubleToLongBits(other.ratioCost))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Weight" + this.getWeight() + "Length" + this.getLength();
	}

	
}
