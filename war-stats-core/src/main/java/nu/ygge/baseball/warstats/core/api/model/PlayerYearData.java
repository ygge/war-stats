package nu.ygge.baseball.warstats.core.api.model;

import nu.ygge.baseball.warstats.core.util.Util;

public final class PlayerYearData {

    public final Player player;
    public final int year, age, games;
    public final String team;
    public final WAR war;
    public final Integer plateAppearances, inningsPitched;

    public PlayerYearData(PlayerId id, String name, String team, int year, int age, int games, Integer plateAppearances,
                          Integer inningsPitched, String warString) {
        this(id, name, team, year, age, games, plateAppearances, inningsPitched, WAR.create(warString));
    }

    private PlayerYearData(PlayerId id, String name, String team, int year, int age, int games, Integer plateAppearances,
                          Integer inningsPitched, WAR war) {
        this(new Player(id, name), team, year, age, games, plateAppearances, inningsPitched, war);
    }

    private PlayerYearData(Player player, String team, int year, int age, int games, Integer plateAppearances,
                           Integer inningsPitched, WAR war) {
        this.player = player;
        this.year = year;
        this.age = age;
        this.team = team;
        this.games = games;
        this.plateAppearances = plateAppearances;
        this.inningsPitched = inningsPitched;
        this.war = war;
    }

    PlayerYear getPlayerYear() {
        return new PlayerYear(player.id, year);
    }

    public PlayerYearData add(PlayerYearData playerYearData) {
        return new PlayerYearData(
                player,
                team,
                year,
                age,
                Math.max(games, playerYearData.games),
                Util.add(plateAppearances, playerYearData.plateAppearances),
                Util.add(inningsPitched, playerYearData.inningsPitched),
                war.add(playerYearData.war)
        );
    }

    @Override
    public String toString() {
        return "PlayerYearData{" +
                "id=" + player.id +
                ", name='" + player.name + '\'' +
                ", year=" + year +
                ", age=" + age +
                ", games=" + games +
                ", team='" + team + '\'' +
                ", war=" + war +
                '}';
    }
}
