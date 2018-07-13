package nu.ygge.baseball.warstats.model;

public class PlayerYear {

    public final int id, year, age;
    public final String name, team;
    public final WAR war;

    public PlayerYear(int id, String name, String team, int year, int age, String warString) {
        this.id = id;
        this.year = year;
        this.age = age;
        this.name = name;
        this.team = team;
        this.war = new WAR(warString);
    }
}