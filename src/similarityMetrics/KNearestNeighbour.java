package similarityMetrics;

import java.util.Comparator;
import java.util.PriorityQueue;

import retrieval.Cases;
import retrieval.TravelCase;

public class KNearestNeighbour {
	public DefaultMetrics metrics;
	public Cases cases;
	public double holidayType = 1;

	public KNearestNeighbour(DefaultMetrics defaultMetrics, Cases cases) {
		this.metrics  = defaultMetrics;
		this.cases = cases;
	}
	
	public PriorityQueue<TravelCase> getNearestNeighbours(TravelCase newCase){
		CaseComparator caseComparator = new CaseComparator();
		PriorityQueue<TravelCase> nearestNeighbours = new PriorityQueue<TravelCase>(caseComparator);
		for(TravelCase currentCase : cases.cases){
			double holidayTypeSimilarity = this.metrics.holidayTypeDecisionTable.getValue(newCase.holidayType, currentCase.holidayType);
			double localSimilarity = holidayTypeSimilarity * this.holidayType;
			double globalSimilarity = localSimilarity;
			currentCase.similarity = globalSimilarity;
//			System.out.println(currentCase.caseName + ": " + currentCase.holidayType + "\t" + currentCase.similarity);
			nearestNeighbours.add(currentCase);
		}
		return nearestNeighbours;
	}
	
	public class CaseComparator implements Comparator<TravelCase>{
		@Override
		public int compare(TravelCase o1, TravelCase o2) {
			double accuracy = 1000;
			return (int) (o2.similarity*accuracy - o1.similarity*accuracy);
		}
	}
	
}
