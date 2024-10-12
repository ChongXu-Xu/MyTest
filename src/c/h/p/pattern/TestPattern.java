package c.h.p.pattern;

import java.util.regex.Pattern;

public class TestPattern {

    public static void main(String[] args) {

        boolean matches = !Pattern.matches("^[1]\\d{10}$", "13266828015");
        System.out.print(matches);
    }
}
