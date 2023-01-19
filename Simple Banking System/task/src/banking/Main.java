package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database(args[1]);
        UserInterface ui = new UserInterface(scanner,db);
        ui.start();

    }
}

