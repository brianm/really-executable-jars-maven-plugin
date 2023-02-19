package org.test;

public class App {
    public static void main(String[] args) {
        String salutation = System.getProperty("app.greeting");
        if (salutation == null) {
            salutation = "Hello";
        }
        String addressee = args.length > 0 ? args[0] : "world";

        System.out.printf("%s %s!%n", salutation, addressee);
    }
}
