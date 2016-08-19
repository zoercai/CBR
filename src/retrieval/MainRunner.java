package retrieval;

import retrieval.Cases.Case;

public class MainRunner {
	public static void main(String[] args){
		Cases cases = new Cases();
		cases.read();
		
		for(Case currentCase : cases.classes){
			System.out.println(currentCase.hotel);
		}
	}
}
