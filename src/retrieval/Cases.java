package retrieval;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Cases {
	
	public ArrayList<Case> classes = new ArrayList<Case>();
	
	protected void read(){
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("./resources/TRAVEL.csv"));
			while(reader.readNext() != null){
				classes.add(parseCase(reader));
				if(reader.readNext() != null){
					reader.readNext();
					reader.readNext();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String stripPunctuation(String value){
		String processedValue = value.replaceAll("[^a-zA-Z0-9]+$", "");
		processedValue = processedValue.replaceFirst("^[^a-zA-Z0-9]+", "");
		return processedValue;
	}
	
	protected Case parseCase(CSVReader reader) throws NumberFormatException, IOException{
		Case newCase = new Case();
//		newCase.defcase = Integer.parseInt(stripPunctuation(reader.readNext()[2]));
    	reader.readNext();
    	newCase.caseName = stripPunctuation(reader.readNext()[2]);
    	newCase.journeyCode = Integer.parseInt(stripPunctuation(reader.readNext()[2]));
    	newCase.holidayType = stripPunctuation(reader.readNext()[2]);
    	newCase.price = Integer.parseInt(stripPunctuation(reader.readNext()[2]));
    	newCase.numOfPersons = Integer.parseInt(stripPunctuation(reader.readNext()[2]));
    	newCase.region = stripPunctuation(reader.readNext()[2]);
    	newCase.transportation = stripPunctuation(reader.readNext()[2]);
    	newCase.duration = Integer.parseInt(stripPunctuation(reader.readNext()[2]));
    	newCase.season = stripPunctuation(reader.readNext()[2]);
    	newCase.accommodation = stripPunctuation(reader.readNext()[2]);
    	newCase.hotel = stripPunctuation(reader.readNext()[2]);
    	return newCase;
	}
	
	public class Case{
//		protected int defcase;
		protected String caseName;
		protected int journeyCode;
		protected String holidayType;
		protected int price;
		protected int numOfPersons;
		protected String region;
		protected String transportation;
		protected int duration;
		protected String season;
		protected String accommodation;
		protected String hotel;
	}

}
