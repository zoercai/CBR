package similarityMetrics;

import java.util.Comparator;
import java.util.PriorityQueue;

import retrieval.Cases;
import retrieval.TravelCase;

public class KNearestNeighbour {
	public DefaultMetrics metrics;
	public Cases cases;
	public double holidayWeight = 0;
	public double transportWeight = 0;
	public double priceWeight = 0;
	public double peopleWeight = 0;
	public double durationWeight = 1;

	public KNearestNeighbour(DefaultMetrics defaultMetrics, Cases cases) {
		this.metrics  = defaultMetrics;
		this.cases = cases;
	}
	
	public PriorityQueue<TravelCase> getNearestNeighbours(TravelCase newCase){
		CaseComparator caseComparator = new CaseComparator();
		PriorityQueue<TravelCase> nearestNeighbours = new PriorityQueue<TravelCase>(caseComparator);
		for(TravelCase currentCase : cases.cases){
			double holidayTypeSimilarity = this.metrics.holidayDecisionTable.getValue(newCase.holidayType, currentCase.holidayType);
			double transportTypeSimilarity = this.metrics.transportDecisionTable.getValue(newCase.transportation, currentCase.transportation);
			double priceSimilarity = this.metrics.calculatePriceSimilarity(newCase.price, currentCase.price);
			double peopleSimilarity = this.metrics.calculatePeopleSimilarity(newCase.numOfPersons, currentCase.numOfPersons);
			double durationSimilarity = this.metrics.calculateDurationSimilarity(newCase.duration, currentCase.duration);
			
			double globalSimilarity = holidayTypeSimilarity * this.holidayWeight + 
					transportTypeSimilarity * this.transportWeight + 
					priceSimilarity * this.priceWeight + 
					peopleSimilarity * this.peopleWeight +
					durationSimilarity * this.durationWeight;
			
			currentCase.similarity = globalSimilarity;
			
			nearestNeighbours.add(currentCase);
		}
		return nearestNeighbours;
	}
	
	public class CaseComparator implements Comparator<TravelCase>{
		@Override
		public int compare(TravelCase o1, TravelCase o2) {
			double accuracy = 10000;
			return (int) (o2.similarity*accuracy - o1.similarity*accuracy);
		}
	}
	
}
