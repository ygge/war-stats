package nu.ygge.baseball.warstats.core.api.model;

import nu.ygge.baseball.warstats.core.util.Util;

public final class PlayerYearData {

    public final PlayerId id;
    public final int year, age, games;
    public final String name, team;
    public final WAR war;
    public final Integer plateAppearances, inningsPitched;

    public PlayerYearData(PlayerId id, String name, String team, int year, int age, int games, Integer plateAppearances,
                          Integer inningsPitched, String warString) {
        this(id, name, team, year, age, games, plateAppearances, inningsPitched, WAR.create(warString));
    }

    public PlayerYearData(PlayerId id, String name, String team, int year, int age, int games, Integer plateAppearances,
                          Integer inningsPitched, WAR war) {
        this.id = id;
        this.year = year;
        this.age = age;
        this.name = name;
        this.team = team;
        this.games = games;
        this.plateAppearances = plateAppearances;
        this.inningsPitched = inningsPitched;
        this.war = war;
    }

    public PlayerYear getPlayerYear() {
        return new PlayerYear(id, year);
    }

    public PlayerYearData add(PlayerYearData playerYearData) {
        return new PlayerYearData(
                id,
                name,
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
                "id=" + id +
                ", year=" + year +
                ", age=" + age +
                ", games=" + games +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", war=" + war +
                '}';
    }
}
