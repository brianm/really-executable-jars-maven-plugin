package org.test;

import java.util.Objects;

public class App {
    public static void main(String[] args) {
        String salutation = Objects.requireNonNullElse(System.getProperty("app.greeting"), "Hello");
        String addressee = args.length > 0 ? args[0] : "world";

        System.out.printf("%s %s!%n", salutation, addressee);
    }
}
