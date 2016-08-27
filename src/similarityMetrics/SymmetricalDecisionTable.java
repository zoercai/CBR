package similarityMetrics;

import java.util.LinkedHashMap;

public class SymmetricalDecisionTable extends DecisionTable{
	
	public SymmetricalDecisionTable(LinkedHashMap<String, Integer> types){
		super(types);
	}
	
	@Override
	public boolean updateValue(String typeFrom, String typeTo, double value){
		if(this.positions.containsKey(typeFrom) && this.positions.containsKey(typeTo)){
			int positionFrom = this.positions.get(typeFrom);
			int positionTo = this.positions.get(typeTo);
			this.similarityValues[positionFrom][positionTo] = value;
			this.similarityValues[positionTo][positionFrom] = value;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateValue(int positionFrom, int positionTo, double value){
		if(positionFrom<this.positions.size() && positionTo<this.positions.size()){
			this.similarityValues[positionFrom][positionTo] = value;
			this.similarityValues[positionTo][positionFrom] = value;
			return true;
		}
		return false;
	}
}
