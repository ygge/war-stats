package nu.ygge.baseball.warstats.core.api.model;

public final class PlayerTotalWAR {

    public final Player player;
    public final WAR totalWAR;

    public PlayerTotalWAR(Player player, WAR totalWAR) {
        this.player = player;
        this.totalWAR = totalWAR;
    }

    @Override
    public String toString() {
        return "PlayerTotalWAR{" +
                "player=" + player +
                ", totalWAR=" + totalWAR +
                '}';
    }
}
