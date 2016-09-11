package retrieval;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Cases {
	
	public ArrayList<TravelCase> cases = new ArrayList<TravelCase>();
	
	public boolean caseName = false;
	public boolean journeyCode = false;
	public boolean holidayType = true;
	public boolean price = false;
	public boolean numOfPersons = false;
	public boolean region = false;
	public boolean transportation = false;
	public boolean duration = false;
	public boolean season = false;
	public boolean accommodation = false;
	public boolean hotel = false;
	
	public void read(){
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("./resources/TRAVEL.csv"));
			while(reader.readNext() != null){
				cases.add(parseCase(reader));
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
	
	protected TravelCase parseCase(CSVReader reader) throws NumberFormatException, IOException{
		TravelCase newCase = new TravelCase();
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
}
