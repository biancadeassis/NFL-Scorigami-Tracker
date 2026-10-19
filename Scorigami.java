
public class Scorigami {

	private int score1; 
	
	private int score2;
	
	private String team1; 
	
	private String team2;
	
	private String first; 
	
	private String last; 
	
	private String stadium;
	
	
	
	
	
	public Scorigami ( int score1, int score2, String team1, String team2, String first, String stadium ) {
		
		
		this.score1 = score1; 
		this.score2 = score2;
		this.team1 = team1; 
		this.team2 = team2; 
		this.first = first;
		this.last = first;
		this.stadium = stadium;
		
		
	}
	
	
	
	
	//since instance variables are private but manager class needs to access the info we need getter methods//
	
	public int getScore1() {
		return score1;
		
	}
	
	public int getScore2() {
		return score2;
		
	}
	public String getTeam1() {
		return team1;
		
	}
	public String getTeam2() {
		return team2;
		
	}
	public String getFirst() {
		return first;
		
	}
	public String getLast() {
		return last;
		
	}
	public String getStadium() {
		return stadium;
		
	}
	

	
	// retrive existing object and update lastest occurence// 
	
	
	public void updateLast ( String newDate) {
		
		this.last = newDate;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
