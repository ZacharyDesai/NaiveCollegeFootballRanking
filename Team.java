// import statements
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a team
 * @author Zachary Desai
 */
public class Team
{
	// class constants
	// NOTE: MUST CHANGE IF NUMBER OF TEAMS CHANGE
	private static final int NUM_TEAMS = 130;
	
	// instance variables
	private String teamName;
	private String teamAbbr;
	private List<Game> schedule;
	private int byeWeeks;
	private String record;
	private int points;
	
	/**
	 * Constructs a team with the given information
	 * @param name the team name
	 * @param abbr the team name's abbreviation
	 * @param start the initial number of points for the team
	 */
	public Team(String name, String abbr, int start) {
		teamName = name;
		teamAbbr = abbr;
		schedule = new ArrayList<Game>();
		byeWeeks = 0;
		record = "0-0";
		points = NUM_TEAMS + 1 - start;
	}
	
	/**
	 * Returns the name of the team
	 * @return the team name
	 */
	public String getName() {
		return teamName;
	}
	
	/**
	 * Returns the abbreviation of the name of the team
	 * @return the team name's abbreviation
	 */
	public String getAbbr() {
		return teamAbbr;
	}
	
	/**
	 * Returns the schedule of the team
	 * @return the team schedule
	 */
	public List<Game> getSchedule() {
		return schedule;
	}
	
	/**
	 * Returns the name of the team
	 * @return the team name
	 */
	public int getByeWeeks() {
		return byeWeeks;
	}
	
	/**
	 * Returns the record of the team
	 * @return the team record
	 */
	public String getRecord() {
		return record;
	}
	
	/**
	 * Returns the points for the team
	 * @return the team's points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Adds a game to the team's schedule
	 * @param abbr the abbreviation of the opponent's team name
	 * @param homefield 'h' for home, 'a' for away, 'n' for neutral
	 * @param victory true for a win, false for a loss
	 */
	public void addGame(String abbr, char homefield, boolean victory) {
		schedule.add(new Game(abbr, homefield, victory));
		setRecord();
	}
	
	/**
	 * Removes the last game from the team's schedule
	 */
	public void removeGame() {
		schedule.remove(schedule.size() - 1);
		setRecord();
	}
	
	/**
	 * Resets the team's schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Game>();
		setRecord();
	}
	
	/**
	 * Adds a bye week to the team's schedule
	 */
	public void addByeWeek() {
		byeWeeks++;
	}
	
	/**
	 * Removes a bye week from the team's schedule
	 */
	public void removeByeWeek() {
		byeWeeks--;
	}
	
	/**
	 * Resets the team's number of bye weeks
	 */
	public void resetByeWeeks() {
		byeWeeks = 0;
	}
	
	/**
	 * Sets the team's record by traversing through its schedule
	 */
	public void setRecord() {
		int numWins = 0;
		int numLosses = 0;
		
		for (int index = 0; index < schedule.size(); index++) {
			Game currGame = schedule.get(index);
			if (currGame.getVictory()) {
				numWins++;
			}
			else {
				numLosses++;
			}
		}
		
		record = numWins + "-" + numLosses;
	}
	
	/**
	 * Sets the number of points the team has
	 */
	public void setPoints(int num) {
		points = num;
	}
}

