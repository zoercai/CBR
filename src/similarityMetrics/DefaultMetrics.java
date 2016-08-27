package similarityMetrics;

import java.util.LinkedHashMap;

import retrieval.Cases;
import retrieval.Cases.Case;

public class DefaultMetrics {
	public Cases cases;
	
	public LinkedHashMap<String, Integer> holidayTypes;
	public DecisionTable holidayTypeDecisionTable;
	
	public DefaultMetrics(Cases cases){
		this.cases = cases;
	}
	
	public void populateHolidayTypes(){
		// Get all types and add them to a linked hashmap
		this.holidayTypes = new LinkedHashMap<String, Integer>();
		int size = 0;
		for(Case currentCase : cases.cases){
			String currentHolidayType = currentCase.holidayType;
			if(!this.holidayTypes.containsKey(currentHolidayType)){
				this.holidayTypes.put(currentHolidayType, size++);
			}
		}
		// Create decision table and populate it with random values
		this.holidayTypeDecisionTable = new SymmetricalDecisionTable(this.holidayTypes);
		for(int i = 0; i<this.holidayTypes.size(); i++){
			for(int j = i+1; j<this.holidayTypes.size(); j++){
				this.holidayTypeDecisionTable.updateValue(i, j, Math.random());
			}
		}
		this.holidayTypeDecisionTable.printTableValues();
		System.out.println();
	}
}
