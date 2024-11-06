import model.*;

import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Resident resident = Entity.createResident();
    private static final Burglar burglar = Entity.createBurglar();
    private static boolean hasWeapon = false;

    public static void main(String[] args) {
        gameLoop();
    }

    private static void gameLoop() {

        while (true) {
            System.out.println(
                    """
                            Where do you want to go?
                            1. Office
                            2. Kitchen
                            3. Hall
                            4. Bedroom
                            5. Exit""");

            switch (requestInput()) {
                case 1 -> officePrompt();
                case 2 -> kitchenPrompt();
                case 3 -> hallPrompt();
                case 4 -> bedroomPrompt();
                case 5 -> System.exit(0);
            }
        }
    }

    //helper method för att kolla efter inputMismatch
    private static int requestInput() {
        while (true) {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            else {
                System.out.println("Must enter a number");
                scanner.next();
            }
        }
    }

    private static void officePrompt() {
        while (true) {
            if (!burglar.isConscious()) {
                System.out.println("""
                        You looked inside the office and found a phone.
                        What do you want to do?
                        1. Call the police
                        2. Exit office""");
                switch (requestInput()) {
                    case 1 -> {
                        System.out.println("The police arrived and arrested the intruder.");
                        System.exit(0);
                    }
                    case 2 -> {
                        return;
                    }
                }
            } else {
                do {
                    System.out.println("""
                            You took a look inside the office but could not find anything.
                            What do you want to do?
                            1. Exit office""");
                } while (requestInput() != 1);
                return;
            }
        }
    }

    private static void kitchenPrompt() {
        while (true) {
            if (!hasWeapon) {
                System.out.println("""
                        You searched the kitchen and found a pan.
                        What do you want to do?
                        1. Pick up pan
                        2. Exit kitchen""");
                switch (requestInput()) {
                    case 1 -> {
                        hasWeapon = true;
                        resident.increaseDamage(3);
                        System.out.println("You picked up the pan\n+3 damage\n");
                        do {
                            System.out.println("""
                                    What do you want to do?
                                    1. Exit kitchen""");
                        } while (requestInput() != 1);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                }
            } else {
                while (true) {
                    System.out.println("""
                            You searched the kitchen but it was empty.
                            What do you want to do?
                            1. Exit kitchen""");
                    if (requestInput() == 1)
                        return;
                }
            }
        }
    }

    private static void hallPrompt() {
        if (burglar.isConscious()) {
            while (true) {
                do {
                    System.out.println("""
                            Choose what you want to do:
                            1. Attack""");
                } while (requestInput() != 1);

                resident.punch(burglar);
                System.out.printf("%s hits %s for %d damage%n", resident.getRole(), burglar.getRole(), resident.getDamage());

                if (burglar.isConscious()) {
                    burglar.punch(resident);
                    if (!resident.isConscious()) {
                        System.out.println(burglar.getRole() + " knocks " + resident.getRole() + " out.");
                        System.exit(0);
                    } else {
                        System.out.println(burglar.getRole() + " hits " + resident.getRole() + " for " + burglar.getDamage() + " damage.");
                    }
                } else {
                    System.out.printf("%s knocks %s out.%n", resident.getRole(), burglar.getRole());
                    return;
                }
            }
        } else {
            System.out.println("Intruder is knocked out on the floor.");
        }
    }

    private static void bedroomPrompt() {
        do {
            System.out.println("""
                    You searched the bedroom but found nothing.
                    What do you want to do?
                    1. Exit bedroom""");
        } while (requestInput() != 1);
    }
}