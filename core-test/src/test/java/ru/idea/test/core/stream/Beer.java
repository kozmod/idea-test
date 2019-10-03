package ru.idea.test.core.stream;

import java.math.BigDecimal;
import java.util.StringJoiner;

public final class Beer {

    private String name;
    private BigDecimal alcohol;

    public String getName() {
        return name;
    }

    public Beer setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getAlcohol() {
        return alcohol;
    }

    public Beer setAlcohol(BigDecimal alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public Beer setAlcohol(double alcohol) {
        this.alcohol = BigDecimal.valueOf(alcohol);
        return this;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Beer.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("alcohol=" + alcohol)
                .toString();
    }
}
