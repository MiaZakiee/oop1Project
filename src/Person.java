import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public abstract class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private char sex;
    private String contactNumber;
    private String emailAddress;
    private String address;
    private LocalDate dateOfBirth;

    //getters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public char getSex() {
        return sex;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress () {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public static int getAge(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth,today);

        return age.getYears();
    }

    //setters
    public void setLastName(Scanner sc) {
        this.lastName = this.middleName = Util.getStringUserChoice("Enter last name: ",1,sc);
    }

    public void setFirstName(Scanner sc) {
        this.firstName = this.middleName = Util.getStringUserChoice("Enter first name: ",1,sc);
    }

    public void setMiddleName(Scanner sc) {
        this.middleName = Util.getStringUserChoice("Enter middle name: ",1,sc);
    }

    public void setSex(Scanner sc) {
        char sexInput;
        System.out.print("\nEnter gender (M or F): ");
        sexInput = sc.nextLine().charAt(0);

        if ((sexInput != 'M') && (sexInput != 'm') && (sexInput != 'F') && (sexInput != 'f')) {
            System.out.println("Invalid input. Please try again.");
            setSex(sc);
        }
        this.sex = sexInput;
    }

    public void setContactNumber(Scanner sc) {
        this.contactNumber = Util.getStringUserChoice("Enter contact number: ",2,sc);
    }

    public void setEmailAddress(Scanner sc) {
        this.emailAddress = Util.getStringUserChoice("Enter Email address: ",0,sc);
    }

    public void setAddress(Scanner sc) {
        this.address = Util.getStringUserChoice("Enter address: ",0,sc);
    }

    public void setDateOfBirth(Scanner sc) {
        LocalDate dateOfBirth = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = Util.getStringUserChoice("Enter date of birth (yyyy-MM-dd): ", 0, sc);
                dateOfBirth = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                validInput = true;
            } catch (Exception e) {
                System.out.println("Incorrect date format. Please enter in format yyyy-MM-dd.");
            }
        }
        this.dateOfBirth = dateOfBirth;
    }
}

