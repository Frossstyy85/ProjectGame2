import model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.printf("%s is waken up in the middle of the night by a sudden noise coming from the hall%n", resident.getRole());
        gameLoop();
    }


    static Scanner scanner = new Scanner(System.in);
    static Resident resident = Entity.createResident();
    static Burglar burglar = Entity.createBurglar();
    static boolean hasWeapon = false;


    private static int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Must enter a number");
                scanner.nextLine();
            }
        }
    }


    private static void gameLoop() {

        while (true) {
            System.out.println(
                    """
                            Where do you want to go?
                            1. Office
                            2. Kitchen
                            3. Bedroom
                            4. Hall
                            5. Exit""");

            switch (getUserInput()) {
                case 1 -> officePrompt();
                case 2 -> kitchenPrompt();
                case 3 -> bedroomPrompt();
                case 4 -> hallPrompt();
                case 5 -> System.exit(0);
            }
        }

    }

    private static void kitchenPrompt() {

        while (true) {
            if (!hasWeapon) {
                System.out.println("""
                        You searched the kitchen and found a pan
                        what do you want to do?
                        1. Pick up pan
                        2. Exit kitchen""");
                switch (getUserInput()) {
                    case 1 -> {
                        hasWeapon = true;
                        resident.increaseDamage(3);
                        System.out.println("You picked up the pan\n+3 damage\n");
                        do {
                            System.out.println("""
                                    What do you want to do?
                                    1. Exit kitchen""");
                        } while (getUserInput() != 1);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                }
            } else {
                while (true) {
                    System.out.println("""
                            You searched the kitchen but it was empty
                            What do you want to do?
                            1. Exit bedroom""");
                    if (getUserInput() == 1) {
                        return;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }
        }
    }


    private static void bedroomPrompt() {
        do {
            System.out.println("""
                    You searched the bedroom but found nothing
                    What do you want to do?
                    1. Exit bedroom""");
        } while (getUserInput() != 1);

    }


    private static void officePrompt() {
        while (true) {
            if (!burglar.isConscious()) {
                System.out.println("""
                        You looked inside the office and found a phone
                        What do you want to do?
                        1. Call the police
                        2. Exit office""");
                switch (getUserInput()) {
                    case 1 -> {
                        System.out.println("The police arrived and arrested the intruder");
                        System.exit(0);

                    }
                    case 2 -> {
                        return;
                    }
                }
            }
            else {
                do {
                    System.out.println("""
                        You searched the office but found nothing
                        What do you want to do?
                        1. Exit office""");
                } while (getUserInput() != 1);
                return;
            }

        }
    }



    private static void hallPrompt() {

        while (true){


            do {
                System.out.println("""
                        Choose what you want to do
                        1. Attack""");
            } while (getUserInput() != 1);

            resident.punch(burglar);
            System.out.printf("%s hits %s for %d damage%n%n",resident.getRole(), burglar.getRole(), resident.getDamage());

            if (burglar.isConscious()){
                burglar.punch(resident);
                if (!resident.isConscious()){
                    System.out.println(burglar.getRole()+" knocks "+resident.getRole()+" out");
                    System.exit(0);
                }
                else {
                    System.out.println(burglar.getRole()+" hits "+resident.getRole()+" for "+ burglar.getDamage()+" damage");
                }
            }
            else {
                System.out.printf("%s knocks %s out%n",resident.getRole(),burglar.getRole());
                return;
            }
        }

    }

}



















