package nu.ygge.baseball.warstats.parser;

import nu.ygge.baseball.warstats.model.PlayerId;
import nu.ygge.baseball.warstats.model.PlayerYear;
import nu.ygge.baseball.warstats.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class DataParser {
    
    private DataParser() {}

    public static List<PlayerYear> parse(InputStream... streams) {
        List<PlayerYear> data = new ArrayList<>();
        for (InputStream stream : streams) {
            data.addAll(parseStream(stream));
        }
        return data;
    }

    private static Collection<PlayerYear> parseStream(InputStream stream) {
        List<PlayerYear> data = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String row;
        try {
            Map<String, Integer> headers = parseColumnHeaders(in.readLine());
            while ((row = in.readLine()) != null) {
                parseRow(headers, row).map(data::add);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private static Map<String, Integer> parseColumnHeaders(String row) {
        Map<String, Integer> headers = new HashMap<>();
        int index = 0;
        for (String name : splitRow(row)) {
            headers.put(name, index++);
        }
        return headers;
    }

    private static Optional<PlayerYear> parseRow(Map<String, Integer> headers, String row) {
        String[] columns = splitRow(row);
        Optional<PlayerId> playerId = PlayerId.create(columns[headers.get("mlb_ID")]);
        Integer year = Util.parseIntegerSafe(columns[headers.get("year_ID")]);
        Integer age = Util.parseIntegerSafe(columns[headers.get("age")]);
        if (year == null || age == null) {
            return Optional.empty();
        }
        return playerId.map(id -> new PlayerYear(
                id,
                columns[headers.get("name_common")],
                columns[headers.get("team_ID")],
                year,
                age,
                columns[headers.get("WAR")]
        ));
    }

    private static String[] splitRow(String row) {
        return row.split(",");
    }
}
