package similarityMetrics;

import java.util.LinkedHashMap;

public class DecisionTable {
	double[][] similarityValues;
	LinkedHashMap<String, Integer> positions = new LinkedHashMap<String, Integer>();
	
	public DecisionTable(LinkedHashMap<String, Integer> types){
		this.positions = types;
		this.similarityValues = new double[types.size()][types.size()];
		for(int i = 0; i<types.size(); i++){
			similarityValues[i][i] = 1.0;
		}
	}
	
	public void printTableValues(){
		for(String type : positions.keySet()){
			System.out.print(type + "\t");
		}
		for(double[] row : similarityValues){
			System.out.println();
			for(Double value : row){
				System.out.print(value + "\t");
			}
		}
	}
	
	public boolean updateValue(String typeFrom, String typeTo, double value){
		if(this.positions.containsKey(typeFrom) && this.positions.containsKey(typeTo)){
			int positionFrom = this.positions.get(typeFrom);
			int positionTo = this.positions.get(typeTo);
			this.similarityValues[positionFrom][positionTo] = value;
			return true;
		}
		return false;
	}
	
	public boolean updateValue(int positionFrom, int positionTo, double value){
		if(positionFrom<this.positions.size() && positionTo<this.positions.size()){
			this.similarityValues[positionFrom][positionTo] = value;
			return true;
		}
		return false;
	}
	
	public double getValue(String typeFrom, String typeTo){
		if(this.positions.containsKey(typeFrom) && this.positions.containsKey(typeTo)){
			int positionFrom = this.positions.get(typeFrom);
			int positionTo = this.positions.get(typeTo);
			return this.similarityValues[positionFrom][positionTo];
		}
		return -1.0;
	}
}
