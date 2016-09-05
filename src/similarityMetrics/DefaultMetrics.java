package similarityMetrics;

import java.util.LinkedHashMap;

import retrieval.Cases;
import retrieval.TravelCase;

public class DefaultMetrics {
	public Cases cases;
	
	public LinkedHashMap<String, Integer> holidayTypes;
	public SymmetricalDecisionTable holidayDecisionTable;
	
	public LinkedHashMap<String, Integer> transportTypes;
	public DecisionTable transportDecisionTable;
	
	public double priceThreshold = 0.25;
	public double peopleThreshold = 1.0/3;
	public double durationThreshold = 1.0/3;
	
	public DefaultMetrics(Cases cases){
		this.cases = cases;
	}
	
	public void populateHolidayTable(){
		// Get all types and add them to a linked hashmap
		this.holidayTypes = new LinkedHashMap<String, Integer>();
		int size = 0;
		for(TravelCase currentCase : cases.cases){
			String currentHolidayType = currentCase.holidayType;
			if(!this.holidayTypes.containsKey(currentHolidayType)){
				this.holidayTypes.put(currentHolidayType, size++);
			}
		}
		// Create decision table and populate it with random values
		this.holidayDecisionTable = new SymmetricalDecisionTable(this.holidayTypes);
		for(int i = 0; i<this.holidayTypes.size(); i++){
			for(int j = i+1; j<this.holidayTypes.size(); j++){
				this.holidayDecisionTable.updateValue(i, j, Math.random());
			}
		}
		this.holidayDecisionTable.printTableValues();
		System.out.println();
	}
	
	public void populateTransportTable(){
		// Get all types and add them to a linked hashmap
		this.transportTypes = new LinkedHashMap<String, Integer>();
		int size = 0;
		for (TravelCase currentCase : cases.cases) {
			String currentTransportType = currentCase.transportation;
			if (!this.transportTypes.containsKey(currentTransportType)) {
				this.transportTypes.put(currentTransportType, size++);
			}
		}
		// Create decision table and populate it with random values
		this.transportDecisionTable = new DecisionTable(this.transportTypes);
		for (int i = 0; i < this.transportTypes.size(); i++) {
			for (int j = 0; j < this.transportTypes.size(); j++) {
				if(i!=j){
					this.transportDecisionTable.updateValue(i, j, Math.random());
				}
			}
		}
		this.transportDecisionTable.printTableValues();
		System.out.println();
	}

	public double calculatePriceSimilarity(int newPrice, int existingPrice){
		if(existingPrice < newPrice){
			return 1.0;
		} else if(existingPrice > newPrice * (1+priceThreshold)){
			return 0.0;
		}
		return Math.cos((existingPrice - newPrice)/(this.priceThreshold * newPrice * Math.PI)) / 2 + 0.5;
	}

	public double calculatePeopleSimilarity(int newPeople, int existingPeople){
		if(newPeople > existingPeople){
			// if less people than what we need
			return 0.0;
		} else if(newPeople * (1+peopleThreshold) < existingPeople){
			// if more people than threshold
			return 0.0;
		}
		
		return 1.0-((double)(existingPeople-newPeople))/((double)newPeople*peopleThreshold+1);
	}

	public double calculateDurationSimilarity(int newDuration, int existingDuration){
		if(newDuration * (1+durationThreshold) <= existingDuration || (double)newDuration * (1-durationThreshold) >= existingDuration){
			// if less days or more days than threshold
			return 0.0;
		} else if(newDuration > existingDuration){
			// if less days than what we need
			return (1/(newDuration*durationThreshold))*(double)(existingDuration-newDuration) + 1;
		}
		// if more days than we need
		return (-1/(newDuration*durationThreshold))*(double)(existingDuration-newDuration) + 1;
	}
}
