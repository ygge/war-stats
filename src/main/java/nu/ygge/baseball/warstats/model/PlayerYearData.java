package nu.ygge.baseball.warstats.model;

public final class PlayerYearData {

    public final PlayerId id;
    public final int year, age;
    public final String name, team;
    public final WAR war;

    public PlayerYearData(PlayerId id, String name, String team, int year, int age, String warString) {
        this(id, name, team, year, age, WAR.create(warString));
    }

    public PlayerYearData(PlayerId id, String name, String team, int year, int age, WAR war) {
        this.id = id;
        this.year = year;
        this.age = age;
        this.name = name;
        this.team = team;
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
                war.add(playerYearData.war)
        );
    }

    @Override
    public String toString() {
        return "PlayerYear{" +
                "id=" + id +
                ", year=" + year +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", war=" + war +
                '}';
    }
}
