package nu.ygge.baseball.warstats.model;

public final class PlayerYear {

    public final PlayerId id;
    public final int year, age;
    public final String name, team;
    public final WAR war;

    public PlayerYear(PlayerId id, String name, String team, int year, int age, String warString) {
        this.id = id;
        this.year = year;
        this.age = age;
        this.name = name;
        this.team = team;
        this.war = new WAR(warString);
    }
}
