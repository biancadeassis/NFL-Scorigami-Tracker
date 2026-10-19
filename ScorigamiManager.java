import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




// class needs to handle reading the CSV file, storing Scorigami object and analyses//

public class ScorigamiManager {

	
	HashMap<String, Scorigami>  scores =  new HashMap<String, Scorigami>();  			// Creating HashMap that takes in String key , and value type scorigami//
																							//declaring and initilizing Hashmap//
	
	
												
	
	
	
	
	
	//create method to read file. ( scanner) and store unique values inide of a hashmap//
	//
	public void loadCSV() {
		try {
			File file = new File("nfl_scores.csv");				//file we want to open//
			Scanner reader = new Scanner(file);					// lets us read what's inside file//
																//try/catch just in case file not found for prgram to not crash!//
		
			reader.nextLine(); 								//skip first column cuz thats the header//
		
			while ( reader.hasNextLine()) {						// Loop to read every game until no more in CVS file//
				
				String line = reader.nextLine();
				
				
				String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);   		// since the file has 2 kinds of commas, we wanna split at commas but but commas that are inside of " " so we only have 9 indexes// 
				
				int homeScore = Integer.parseInt(columns[5]);   // since CVS reads as String but Scorigami constructor needs these ones in int we have to convert to int.//
				int awayScore = Integer.parseInt(columns[6]);
				
				// these ones are Strings and our constructor expecting String for these so don't need to parse//
				
				String date = columns[0];
				String team1 = columns[3];
				String team2 = columns[4];
				String stadium = columns[8];
				
				String key;									//declare key as string//
				
				
				if(homeScore < awayScore) {
															// if homescore is smaller than away then homescore comes first in key.
					key = homeScore + "-" + awayScore;
				}
				else {
					
					key = awayScore + "-" + homeScore;			//if home score is bigger we still want smaller score first in key//
					
				}
				
				if (scores.containsKey(key)) {
					
					Scorigami existing = scores.get(key);
					
					existing.updateLast(date);										//call updateLast() on existing object passing date//
					
				}
				else {
					// create new object//
					
					Scorigami newScore = new Scorigami( homeScore, awayScore, team1, team2, date, stadium );
					
					// store newScore into hashmap//
					
					scores.put(key,newScore);				
					
				}
				
			}
			
			
			
			reader.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("Could Not find CSV file :( ");
			
		}
		
		
	}
	
	// method to lookup score ( menu option 1)//
	// returns a scorigami if score already in hashmap, returns null if not// 
	// when use this method in demo later need to first make sure not null//
	public Scorigami scorelookup(int score1, int score2) {
		
		String key;
		
		if (score1 < score2 ) {
			key = score1 + "-" + score2 ;  
			
		}
		else {
			
			key = score2 + "-" + score1;
			
		}
		
		if(scores.containsKey(key)) {
			
			return scores.get(key);
			
		}
			return null;
		
	}
	
	//Method to find Team with most Scorigamis//
	// use Hashmap to store team name and how many scorigamis it has//
	// key: team name . value: number of scoris //
	public HashMap<String, Integer> countByTeam ( ){
			
	
	HashMap<String, Integer> teamCount = new HashMap< String, Integer>();
	
	

	//loop through every scori object from HashMap, current, 
	for ( Scorigami current : scores.values() ) {			
		
	
		String team1 = current.getTeam1();
		
		String team2 = current.getTeam2();
		
		if(teamCount.containsKey(team1)) {
			 
		teamCount.put(team1, teamCount.get(team1)+1);
			
		}
		else {
			
		teamCount.put(team1, 1);
		
		}
		
		if( teamCount.containsKey(team2)) {
			teamCount.put(team2, teamCount.get(team2)+1);
			
		}
		else {
			teamCount.put(team2, 1);
			
		}
		
		
	
	}
	
	return teamCount;
	
	
}
	
	
	//Method that counts how many scoires happen each month//
	
	public HashMap<String, Integer> CountByMonth() {
		HashMap<String, Integer> monthlyCount = new HashMap<String, Integer> ();
		
		for(Scorigami current : scores.values() )	{
			
			String date = current.getFirst();
			
			String month = date.substring(0,2);
			
			if(monthlyCount.containsKey(month) ) {
			
				monthlyCount.put(month, monthlyCount.get(month)+1);
				
				
			}
			else {
				
				monthlyCount.put(month, 1);
			}
			
		}
		
			return monthlyCount; 
	
	}
	
	
	
	
	// method to count how many Scories first happen in each stadium//
	public HashMap<String, Integer>  countByStadium(){
		HashMap<String, Integer> stadiumCount =  new HashMap<String, Integer> ();
		
		
		for(Scorigami current : scores.values()) {
			String stadium = current.getStadium();
			
			if(stadiumCount.containsKey(stadium) ){
				stadiumCount.put(stadium, stadiumCount.get(stadium)+1);
			}
			
			else {
				
				stadiumCount.put(stadium,1);
			}
			
			
		}
		
		return stadiumCount;
	}
	
	
	
	
	
	
	
	
	
	
}
