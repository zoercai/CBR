package retrieval;

import java.util.PriorityQueue;

import similarityMetrics.DefaultMetrics;
import similarityMetrics.KNearestNeighbour;

public class MainRunner {
	public static void main(String[] args){
		Cases cases = new Cases();
		cases.read();
		
		DefaultMetrics defaultMetrics = new DefaultMetrics(cases);
		defaultMetrics.populateHolidayTypes();
		
		KNearestNeighbour similarityCalculator = new KNearestNeighbour(defaultMetrics, cases);
		
		TravelCase testCase = new TravelCase();
		testCase.holidayType = "Recreation";
		
		PriorityQueue<TravelCase> nearestNeighbours = similarityCalculator.getNearestNeighbours(testCase);
		while(!nearestNeighbours.isEmpty()){
			TravelCase currentNeighbour = nearestNeighbours.poll();
			System.out.println(currentNeighbour.caseName + ": " + currentNeighbour.holidayType);
		}
	}
}