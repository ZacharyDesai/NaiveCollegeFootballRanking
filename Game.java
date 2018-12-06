/**
 * Class that represents a game between two teams
 * @author Zachary Desai
 */
public class Game
{
	// instance variables
	private String opponentAbbr;
	private char homefield;
	private boolean victory;
	
	/**
	 * Constructs a game with the given information
	 * @param opponentAbbr the abbreviation of the opponent's team name
	 * @param homefield 'h' for home, 'a' for away, 'n' for neutral
	 * @param victory true for a win, false for a loss
	 */
	public Game(String opponentAbbr, char homefield, boolean victory) {
		this.opponentAbbr = opponentAbbr;
		this.homefield = homefield;
		this.victory = victory;
	}
	
	/**
	 * Returns the abbreviation of the opponent's team name
	 * @return the opponent's team name abbreviation
	 */
	public String getOpponentAbbr() {
		return opponentAbbr;
	}
	
	/**
	 * Returns whether the game was a home game, an away game,
	 * or a neutral site game for the team (not the opponent)
	 * @return 'h' for home, 'a' for away, 'n' for neutral
	 */
	public char getHomefield() {
		return homefield;
	}
	
	/**
	 * Returns whether or not the team (not the opponent) won the game
	 * @return true for a win, false for a loss
	 */
	public boolean getVictory() {
		return victory;
	}
}
  