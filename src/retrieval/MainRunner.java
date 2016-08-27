package retrieval;

import similarityMetrics.DefaultMetrics;
import similarityMetrics.KNearestNeighbour;

public class MainRunner {
	public static void main(String[] args){
		Cases cases = new Cases();
		cases.read();
		
		DefaultMetrics defaultMetrics = new DefaultMetrics(cases);
		defaultMetrics.populateHolidayTypes();
		
		KNearestNeighbour similarityCalculator = new KNearestNeighbour(defaultMetrics);
	}
}