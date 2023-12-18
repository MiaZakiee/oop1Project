import java.util.*;
public class Util {
    public static boolean containsDigits(String input) {
        return input.matches(".*[0-9].*");
    }
    public static boolean containsAlphabet(String input) {
        return input.matches("[a-zA-Z]");
    }
    public static int getUserChoice(String options, int maxChoice, Scanner sc) {
        int choice = 0;
        while (choice < 1 || choice > maxChoice) {
            System.out.println("\n" + options);
            try {
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > maxChoice) {
                    System.out.println("Please enter a valid choice (1, 2, ..., " + maxChoice + ").");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear the buffer
            }
        }
        return choice;
    }
    public static String getStringUserChoice(String prompt, int condition, Scanner sc) {
        String choice;
        while (true) {
            System.out.print("\n" + prompt);
            choice = sc.nextLine();
            if (condition == 1) {
                if (containsDigits(choice)) {
                    System.out.println("Please enter a valid choice.");
                    getStringUserChoice(prompt,condition,sc);
                } else {
                    break;
                }
            }
            else if (condition == 2) {
                if (containsAlphabet(choice)) {
                    System.out.println("Please enter a valid choice.");
                    getStringUserChoice(prompt,condition,sc);
                } else {
                    break;
                }
            }
            else {
                break;
            }
        }
        return choice;
    }

    public static String inputCourse (Scanner sc) {
        String courseInput;
        List<String> validCourse = Arrays.asList("BSCS","BSIT","BSHM","BSAE","BSCE","BSN");
        System.out.println("""
                \nBSCS - Bachelor of Science in Computer Science
                BSIT - Bachelor of Science in Information Technology
                BSHM - Bachelor of Science in Hotel Management
                BSAE - Bachelor of Science Aeronautical Engineering
                BSCE - Bachelor of Science in Civil Engineering
                BSN  - Bachelor of Science in Nursing
                """);

        do {
            System.out.print("Enter course: ");
            courseInput = sc.nextLine();

            if (!validCourse.contains(courseInput)) {
                System.out.println("Please enter an available course:");
            }
        } while (!validCourse.contains(courseInput));
        return courseInput;
    }
    public static int getUserChoiceWithPrompt(String prompt, int maxChoice, Scanner sc) {
        int choice = 0;
        while (choice < 1 || choice > maxChoice) {
            try {
                System.out.print("\n" + prompt);
                choice = sc.nextInt();
                if (choice < 1 || choice > maxChoice) {
                    System.out.println("Please enter a valid choice (1, 2, ..., " + maxChoice + ").");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine(); // Clear the buffer
            }
        }
        return choice;
    }

}
