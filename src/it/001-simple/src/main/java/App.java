public class App {
    public static void main(String[] args) {
        System.out.printf("%s %s!%n",
                System.getProperty("testapp.greeting", "Hello"),
                args.length > 0 ? args[0] : "world");
    }
}
