# NaiveCollegeFootballRanking
A naive ranking system for college football based on their "resumes"

This project is a rather trivial ranking system for college football teams that I created for personal experiemntal inquiry.

To run the program, execute the Ranking.java class.
By default, the Ranking class uses the 2017 season.
To select a different season, 2 text files - preseason[yearNum].txt and season[yearNum].txt - must be created.
Next, the class constant at the top of the Ranking class must be changed to match that year number.
Finally, if the number of teams that season is different than 130 (as in in the 2017 season), the class constant at the top of the Team class must be changed accordingly.

The preseason[yearNum].txt files are formatted as so:
[team1Abbr] [team1Name]
[team2Abbr] [team2Name]
[team3Abbr] [team3Name]
etc.

The season[yearNum].txt files are formatted as so, with a * or - separating the different weeks in the season:
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
...
[* OR -]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
...
[* OR -]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
[winningTeamAbbr] [losingTeamAbbr] [homefieldChar]
...
[-]

A '*' or '-' separates weeks.
A '*' does not re-order the rankings, and a '-' does re-order the rankings.
It is recommended to use the '*' following the first couple of weeks and following the Army-Navy week (usually just 1 game) so that rankings are not distorted based on just a few results.
Following the majority of weeks, a '-' is recommended to use.
It is mandatory that the last line of the file is a '-', however, so that the final rankings are taking into account all of the data.
