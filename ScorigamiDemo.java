import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedList;



//create mananger object, Runs the program, displays the menu, processes user choices, and prints the results//
public class ScorigamiDemo {

	
	public static void main(String[] args ) {
		
		
	ScorigamiManager Manager = new ScorigamiManager();
		
	Manager.loadCSV();	//call loadCSV method//
	
	
	
	
	// 
	Scanner input = new Scanner(System.in);
	
	
	System.out.println("Welcome to Scorigamis!\n"+ "-----------------------------\n" );
	while (true) {
	System.out.println(
		    "\n1. Search by score\n" +
		    "2. Team with most scorigamis\n" +
		    "3. Scorigamis by month\n" +
		    "4. Scorigamis by stadium\n" +
		    "5. Quit\n" +
		    "Your choice?"
		);

		int choice = input.nextInt();
		switch(choice) {
		
		case 1:
			 
			System.out.println("Enter Score in the following format: Score1-Score2 (23-17, 7-33, 27-0, etc):");
			
			input.nextLine();
			String scoreInput = input.nextLine();
			
			if (scoreInput.matches("\\d+-\\d+")) {
				
				String[] parts = scoreInput.split("-");
				int score1 = Integer.parseInt(parts[0]);
				int score2 = Integer.parseInt(parts[1]);
				
				Scorigami result = Manager.scorelookup(score1, score2);
				
				if(result != null) {
					System.out.println("------------------------------------");
				    System.out.println(result.getTeam1() + " vs " + result.getTeam2());
				    System.out.println(result.getScore1() + "-" + result.getScore2());
				    System.out.println("Date: " + result.getFirst());
				    System.out.println(result.getStadium());
				    System.out.println("Last Occurance: " + result.getLast());
				    System.out.println("------------------------------------");
					
				}
				else {
					System.out.println("That Score has Not Happened Yet!!");
				}
			}
			else {
				
				System.out.println("Invalid Score Format (Must be Score1-Score2)");
			}
			
			break;
			
		
		case 2: 
			HashMap<String, Integer> topTeam = Manager.countByTeam();
			
			String winner = ""; 
			int high = 0;
			
			for( String team : topTeam.keySet()) {
				if( high < topTeam.get(team)){
					high = topTeam.get(team);
					winner = team;
					
				}
			}
			System.out.println(winner + ": " + high);
			
			
			break;
		
		case 3: 
			HashMap<String, Integer> monthCount = Manager.CountByMonth();
			System.out.println("Scorigamis by Month");
			System.out.println("-----------------------------");
			System.out.println("January: " + monthCount.get("01"));
			System.out.println("February: " + monthCount.get("02"));
			System.out.println("September: " + monthCount.get("09"));
			System.out.println("October: " + monthCount.get("10"));
			System.out.println("November: " + monthCount.get("11"));
			System.out.println("December: " + monthCount.get("12"));
			
			break;
			
		case 4:
			System.out.println("Scorigamis by Stadium");
			System.out.println("------------------------------");

			HashMap<String, Integer> stadiumCount = Manager.countByStadium();
			

			HashMap<Integer, LinkedList<String>> groupedStadiums = new HashMap<>();
			for( String stadium : stadiumCount.keySet()) {
				int count = stadiumCount.get(stadium);
			
				if(!groupedStadiums.containsKey(count)) {
					groupedStadiums.put(count, new LinkedList<String>());
				}
				groupedStadiums.get(count).add(stadium);
			}		
			
			
			for ( int count = 100; count >= 1; count--) {
				if (groupedStadiums.containsKey(count)) {
					System.out.println(count + " Scorigamis: " + groupedStadiums.get(count));
				}
			}
			
			break;
		
		
		case 5:
		    System.out.println("Goodbye!");
		    return;
		
		}
		
		
	}
	
	}
	
	
	
	
	

	
	
	
	
}
