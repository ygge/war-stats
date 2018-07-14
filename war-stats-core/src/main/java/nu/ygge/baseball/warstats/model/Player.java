package nu.ygge.baseball.warstats.model;

public final class Player {

    public final PlayerId id;
    public final String name;

    public Player(PlayerId id, String name) {
        this.id = id;
        this.name = name;
    }
}
