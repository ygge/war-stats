package nu.ygge.baseball.warstats.core.api.model;

import java.math.BigDecimal;

public final class WARAge {

    public final int age;
    public final BigDecimal percentage;
    public final WAR war;

    public WARAge(int age, WAR war, BigDecimal percentage) {
        this.age = age;
        this.war = war;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "WARAge{" +
                "age=" + age +
                ", percentage=" + percentage +
                ", war=" + war +
                '}';
    }
}
