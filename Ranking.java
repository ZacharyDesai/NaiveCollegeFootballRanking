import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * THIS IS THE DRIVER CLASS FOR THE PROGRAM.
 * This is a naive ranking algorithm built for college football based on the
 * concept of ranking teams based on their "resumes", which analyzes the
 * strengths of their wins and losses based on the strength of the team
 * they played against and where the game was played (homefield advantage).
 * Running this class - provided the correct text files are supplied - will
 * generate and print out the season rankings with the number of points
 * each team has based on their season results.
 * @author Zachary Desai
 */
public class Ranking
{
	// class constants
	// NOTE: MUST CHANGE TO SELECT SEASON
	private static final int YEAR = 2017;
	
	// class constants
	private static final int WIN_PTS = 100;
	private static final int AWAY_PTS = 10;
	private static final int NEUTRAL_PTS = 5;
	private static final char HOME_GAME = 'h';
	private static final char AWAY_GAME = 'a';
	private static final int BYE_PTS = 141;
	
	/**
	 * Runs the ranking program, and generates and prints out the rankings
	 * @throws FileNotFoundException if the files for the season do not exist
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// scans the preseason file to generate the initial rankings
		List<Team> ranking = new ArrayList<Team>();
		File preFile = new File("preseason" + YEAR + ".txt");
		Scanner preScan = new Scanner(preFile);
		int rank = 0;
		while (preScan.hasNextLine()) {
			rank++;
			String abbr = preScan.next();
			String name = preScan.nextLine();
			name = name.substring(1, name.length());
			ranking.add(new Team(name, abbr, rank));
		}
		preScan.close();
		
		// scans the season file to update the rankings throughout the season
		boolean[] played = new boolean[ranking.size()];
		File file = new File("season" + YEAR + ".txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String next = scan.next();
			
			// at the end of the week, add a bye week for the teams who did not play that week
			if (next.equals("-") || next.equals("*")) {
				for (int index = 0; index < ranking.size(); index++) {
					if (!played[index]) {
						Team team = ranking.get(index);
  						team.addByeWeek();
						ranking.set(index, team);
					}
				}
				played = new boolean[ranking.size()];
				score(ranking);
				
				// only re-rank the teams if a '-' instead of a '*' is read
				if (next.equals("-")) {
					Collections.sort(ranking, new Comparator<Team>() {
						@Override
						public int compare(Team teamA, Team teamB) {
							if (teamA.getPoints() > teamB.getPoints()) {
								return -1;
							}
							else if (teamA.getPoints() < teamB.getPoints()) {
								return 1;
							}
							else {
								return 0;
							}
						}
					});
				}
			}
			
			// for each line of input representing a game, add the game to the team information
			else {
				String winner = next;
				String loser = scan.next();
				String winnerHome = scan.next();
				char winHome = winnerHome.charAt(0);
				char loseHome;
				
				// set the homefield character for both teams
				if (winHome == HOME_GAME) {
					loseHome = AWAY_GAME;
				}
				else if (winHome == AWAY_GAME) {
					loseHome = HOME_GAME;
				}
				else {
					loseHome = winHome;
				}
				
				Team win = null;
				Team lose = null;
				int winIndex = -1;
				int loseIndex = -1;
				
				// find the wninning and losing teams in the current rankings
				for (int index = 0; index < ranking.size(); index++) {
					if (ranking.get(index).getAbbr().equals(winner)) {
						win = ranking.get(index);
						winIndex = index;
						played[winIndex] = true;
					}
					if (ranking.get(index).getAbbr().equals(loser)) {
						lose = ranking.get(index);
						loseIndex = index;
						played[loseIndex] = true;
					}
				}
				
				// only adds the game to teams who are in the FBS (and, thus, in these rankings)
				if (!winner.equals("FCS")) {
					win.addGame(loser, winHome, true);
					ranking.set(winIndex, win);
				}
				if (!loser.equals("FCS")) {
					lose.addGame(winner, loseHome, false);
					ranking.set(loseIndex, lose);
				}
			}
		}
		scan.close();
		
		// prints out the final rankings
		for (int index = 0; index < ranking.size(); index++) {
			Team team = ranking.get(index);
			System.out.println("#" + (index + 1) + " " + team.getName() + " (" + team.getRecord() + ") - " + team.getPoints());
		}
	}
	
	/**
	 * Updates the team's number of points based on their updated schedule
	 * and the current rankings
	 * @param ranking the current ranked order of teams
	 */
	public static void score(List<Team> ranking) {
		// traverses through the teams in the rankings
		for (int index = 0; index < ranking.size(); index++) {
			Team team = ranking.get(index);
			int points = 0;
			List<Game> schedule = team.getSchedule();
			
			// traverse through the games in the team's schedule
			for (Game game : schedule) {
				String opponentAbbr = game.getOpponentAbbr();
				
				// find the number of points to award the team based on the strength of their opponent
				// (will award no points if the opponent is in the FCS)
				if (!opponentAbbr.equals("FCS")) {
					for (int oppInd = 0; oppInd < ranking.size(); oppInd++) {
						if (ranking.get(oppInd).getAbbr().equals(opponentAbbr)) {
							points += ranking.size() - oppInd;
						}
					}
				}
				
				// awards 100 points if the team won
				if (game.getVictory()) {
					points += WIN_PTS;
				}
				// awards 10 points if the team played on the road
				if (game.getHomefield() == AWAY_GAME) {
					points += AWAY_PTS;
				}
				// awards 5 points if the team played at a neutral site
				else if (game.getHomefield() != HOME_GAME) {
					points += NEUTRAL_PTS;
				}
			}
			
			// adds a constant amount of points for the number of bye weeks the team has
			points += team.getByeWeeks() * BYE_PTS;
			
			// updates the team's number of points
			team.setPoints(points);
			ranking.set(index, team);
		}
	}
}
