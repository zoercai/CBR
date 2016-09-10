package similarityMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

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
	public double accomThreshold = 1.0/3;
	
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
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Active"), 0.1);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Education"), 0.1);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("City"), 0.3);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Recreation"), 0.7);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Wandering"), 0.65);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Language"), 0.3);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Bathing"), this.holidayTypes.get("Skiing"), 0.2);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("Education"), 0.2);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("City"), 0.2);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("Recreation"), 0.5);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("Wandering"), 0.75);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("Language"), 0.45);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Active"), this.holidayTypes.get("Skiing"), 1);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Education"), this.holidayTypes.get("City"), 0.8);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Education"), this.holidayTypes.get("Recreation"), 0.5);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Education"), this.holidayTypes.get("Wandering"), 0.5);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Education"), this.holidayTypes.get("Language"), 0.9);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Education"), this.holidayTypes.get("Skiing"), 0.45);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("City"), this.holidayTypes.get("Recreation"), 0.7);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("City"), this.holidayTypes.get("Wandering"), 0.9);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("City"), this.holidayTypes.get("Language"), 0.75);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("City"), this.holidayTypes.get("Skiing"), 0.15);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Recreation"), this.holidayTypes.get("Wandering"), 0.8);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Recreation"), this.holidayTypes.get("Language"), 0.4);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Recreation"), this.holidayTypes.get("Skiing"), 0.9);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Wandering"), this.holidayTypes.get("Language"), 0.65);
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Wandering"), this.holidayTypes.get("Skiing"), 0.45);
		
		this.holidayDecisionTable.updateValue(this.holidayTypes.get("Language"), this.holidayTypes.get("Skiing"), 0.3);
		
//		for(int i = 0; i<this.holidayTypes.size(); i++){
//			for(int j = i+1; j<this.holidayTypes.size(); j++){
//				this.holidayDecisionTable.updateValue(i, j, Math.random());
//			}
//		}
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
		
		this.transportDecisionTable.updateValue(this.transportTypes.get("Car"), this.transportTypes.get("Coach"), 0.5);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Car"), this.transportTypes.get("Plane"), 0.0);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Car"), this.transportTypes.get("Train"), 0.8);
		
		this.transportDecisionTable.updateValue(this.transportTypes.get("Coach"), this.transportTypes.get("Car"), 0.5);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Coach"), this.transportTypes.get("Plane"), 0.0);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Coach"), this.transportTypes.get("Train"), 0.7);
		
		this.transportDecisionTable.updateValue(this.transportTypes.get("Plane"), this.transportTypes.get("Car"), 0.0);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Plane"), this.transportTypes.get("Coach"), 0.0);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Plane"), this.transportTypes.get("Train"), 0.0);
		
		this.transportDecisionTable.updateValue(this.transportTypes.get("Train"), this.transportTypes.get("Car"), 0.3);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Train"), this.transportTypes.get("Coach"), 0.7);
		this.transportDecisionTable.updateValue(this.transportTypes.get("Train"), this.transportTypes.get("Plane"), 0.0);
		
//		for (int i = 0; i < this.transportTypes.size(); i++) {
//			for (int j = 0; j < this.transportTypes.size(); j++) {
//				if(i!=j){
//					this.transportDecisionTable.updateValue(i, j, Math.random());
//				}
//			}
//		}
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
	
	public double calculateSeasonSimilarity(String newSeason, String existingSeason){
		int newMonth = convertMonth(newSeason)+1;
		int existingMonth = convertMonth(existingSeason)+1;
		
		// if same month, return 1.0
		if(newMonth==existingMonth){
			return 1.0;
		}
		
		// if different, calculate season
		int seasonDifference = Math.abs((newMonth%12)/3 - (existingMonth%12)/3);
		
		if(seasonDifference==0){
			// if same season
			return 0.8;
		} else if(seasonDifference==1 || seasonDifference==3){
			// if season next to current season
			return 0.4;
		} else {
			// if opposite season
			return 0.0;
		}
	}
	
	private int convertMonth(String month){
		Date date = null;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int monthInt = cal.get(Calendar.MONTH);
	    return monthInt;
	}
	
	public double calculateAccommodationSimilarity(String newAccom, String existingAccom){
		int newAccomRating = convertAccom(newAccom);
		int existingAccomRating = convertAccom(existingAccom);
		
		int ratingDifference = Math.abs(newAccomRating - existingAccomRating);
		
		double result = -(accomThreshold)*ratingDifference + 1;
		if(result > 0){
			return result;
		}
		return 0.0;
	}

	private int convertAccom(String accom) {
		switch(accom){
			case "HolidayFlat": return 0;
			case "OneStar": return 1;
			case "TwoStars": return 2;
			case "ThreeStars": return 3;
			case "FourStars": return 4;
			case "FiveStars": return 5;
			default: return 6;
		}
	}
}
