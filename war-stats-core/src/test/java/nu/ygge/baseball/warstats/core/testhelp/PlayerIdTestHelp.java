package nu.ygge.baseball.warstats.core.testhelp;

import nu.ygge.baseball.warstats.core.model.PlayerId;

public class PlayerIdTestHelp {

    public static PlayerId createPlayerId(int id) {
        return PlayerId.create(Integer.toString(id))
                .orElseThrow(() -> new RuntimeException(String.format("Player could not be created from id=%d", id)));
    }
}
