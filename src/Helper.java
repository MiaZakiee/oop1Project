import java.util.*;

public class Helper {
    StudentDatabase database = new StudentDatabase();
    Scanner sc = new Scanner(System.in);

    //constructor
    public Helper() {
        startUp(sc);
    }
    //destructor
    public void close () {
        sc.close();
    }
    public void startUp (Scanner sc) {
        int choice = Util.getUserChoice("""
                Welcome to Ninin-U Enrollment System!
                Enter login credentials
                [1] Approver
                [2] Enroller
                [3] Exit Program
                """,3,sc);

        switch (choice) {
            case 1:
                database.setApprover(true);
                approverStart(sc);
                break;
            case 2:
                database.setApprover(false);
                enrollerStart(sc);
                break;
            case 3:
                break;
        }
    }

    public void approverStart (Scanner sc) {
        int choice = Util.getUserChoice("""
                Enter approver operation
                [1] See pending requests
                [2] Enroll student
                [3] View students
                [4] Back
                """,4,sc);

        switch (choice) {
            case 1:
                database.displayRequests(sc);
                approverStart(sc);
                break;
            case 2:
                enrollOptions(sc);
                break;
            case 3:
                viewStudents(sc);
                break;
            case 4:
                startUp(sc);
                break;
        }
    }

    public void enrollerStart (Scanner sc) {
        int choice = Util.getUserChoice("""
                Enter enroller operation
                [1] Enroll student
                [2] View students
                [3] Back
                """,3,sc);

        switch (choice) {
            case 1:
                enrollOptions(sc);
                break;
            case 2:
                viewStudents(sc);
                break;
            case 3:
                startUp(sc);
        }
    }

    public void enrollOptions (Scanner sc) {
        int choice = Util.getUserChoice("""
                Enter enrollment method
                [1] Single Enrollment
                [2] Batch Enrollment
                [3] Back
                """,3,sc);

        switch (choice) {
            case 1:
                database.enrollStudent(sc);
                enrollOptions(sc);
                break;
            case 2:
                database.batchEnroll(sc);
                enrollOptions(sc);
                break;
            case 3:
                if (database.getApprover()){
                    approverStart(sc);
                } else {
                    enrollerStart(sc);
                }
        }
    }

    public void viewStudents(Scanner sc) {
        int choice = Util.getUserChoice("""
                Enter search method
                [1] View all students
                [2] Search via student ID
                [3] Search via course
                [4] View all inactive students
                [5] Back
                """,5,sc);

        switch (choice) {
            case 1:
                database.viewAllStudents();
                viewStudents(sc);
                break;
            case 2:
                database.searchViaID(sc);
                viewStudents(sc);
                break;
            case 3:
                sc.nextLine();
                database.searchByCourse(sc);
                viewStudents(sc);
                break;
            case 4:
                database.viewAllInactiveStudents();
                viewStudents(sc);
                break;
            case 5:
                if (database.getApprover()){
                    approverStart(sc);
                } else {
                    enrollerStart(sc);
                }
                break;
        }
    }
}
