import java.util.LinkedList;
import java.util.*;

public class StudentDatabase {
    /*
    *   TODO: Enroll student            DONE
    *   TODO: Batch enroll              DONE
    *   TODO: Remove Student
    *   TODO: Modify Student course
    *   TODO: See pending request
    *   TODO: Pending Student List
    *   TODO: ACTUAL student list       DONE
    *   TODO: SEARCH BY COURSE          DONE
    *   TODO: SEARCH BY ID NUM          DONE
    *   TODO: VIEW STUDENT              DONE
    * */
    int studentCount;
    int makeStudentID;
    boolean isApprover;
    LinkedList<Student> studentList = new LinkedList<>();
    public StudentDatabase () {
        studentCount = 0;
        makeStudentID = 2300;
    }

    public boolean getApprover() {
        return isApprover;
    }

    public void setApprover(boolean approver) {
        this.isApprover = approver;
    }

    public void enrollStudent (Scanner sc) {
        int choice = 0;
        Student newStudent = new Student (makeStudentID);
        sc.nextLine();
        newStudent.setLastName(sc);
        newStudent.setFirstName(sc);
        newStudent.setMiddleName(sc);
        //newStudent.setAge(sc); TODO:DO THIS ASAP
        newStudent.setContactNumber(sc);
        newStudent.setEmailAddress(sc);
        newStudent.setAddress(sc);
        newStudent.setSex(sc);
        newStudent.setCourse(sc);

        //finalize decision
        System.out.println("\nPlease confirm information:");
        displayIndividual(newStudent);

        System.out.println("\nConfirm details?:");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        System.out.print("Enter choice: ");
        try {
            choice = sc.nextInt();
            if (choice < 1 || choice > 2) {
                System.out.println("Please enter a valid choice.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please enter a valid choice.");
            enrollStudent(sc);
        }

        if (choice == 2) {
            System.out.println("Enter details again");
            enrollStudent(sc);
        }

        if (getApprover()) {
            System.out.println("Student enrolled successfully.");
            studentList.add(newStudent);
            makeStudentID++;
        } else {
            //TODO: Add to pending request
            System.out.println("Used enroller account. Added to pending request");
        }
    }

    public void batchEnroll (Scanner sc) {
        int num = Util.getUserChoice("Enter number of students to enroll: ",10,sc);

        for (int i = 0; i < num; i++) {
            enrollStudent(sc);
        }
    }

    public void viewAllStudents () {
        System.out.println("ID NUMBER LAST NAME      FIRST NAME     MIDDLE NAME     AGE  SEX  COURSE    YEAR LEVEL CONTACT INFORMATION   EMAIL ADDRESS            ADDRESS");

        // TODO: ADD CONDITIONAL IF ACTIVE STUDENT
        //LOOP SA THROUGH STUDENTS
        for (Student i : studentList) {
            if (i.getIsActive()) {
                displayIndividualTable(i);
            }
        }
    }

    public void displayIndividual(Student student) {
        System.out.println(
                "Name: " + student.getLastName() + ", " +
                student.getFirstName() + " " +
                student.getMiddleName() + "\n" +
                "Age: AgePuhon" + "\n" +//+ student.getAge
                "Gender: " + student.getSex() + "\n" +
                "Course: " + student.getCourse() + " " +
                student.getYearLevel() + "\n" +
                "Contact Number: " + student.getContactNumber() + "\n" +
                "Address: " + student.getAddress() + "\n" +
                "Email Address: " + student.getEmailAddress()
        );
    }

    public void displayIndividualTable(Student student) {

        System.out.printf("%-10d" +
                        "%-15s" +
                        "%-15s" +
                        "%-16s" +
                        "20   " +
                        "%-5s" +
                        "%-10s" +
                        "%-11d" +
                        "%-22s" +
                        "%-25s" +
                        "%s\n",
                student.getStudentID(),
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                //TODO: GETAGE MAKE AGE YAWA
                student.getSex(),
                student.getCourse(),
                student.getYearLevel(),
                student.getContactNumber(),
                student.getEmailAddress(),
                student.getAddress());
    }

    public void searchViaID (Scanner sc) {
        int studentIdInput = Util.getUserChoice("Enter ID number: ",9999,sc);
        Student toDisplay = null;
        boolean found = false;

        for (Student i : studentList) {
            if (i.getStudentID() == studentIdInput) {
                found = true;
                toDisplay = i;
                break;
            }
        }

        if (found) {
            System.out.println("Student found!");
            displayIndividual(toDisplay);
        } else {
            System.out.println("Student not found.");
        }

    }

    public void searchByCourse(Scanner sc) {
        String course = Util.inputCourse(sc);
        System.out.println("\nID NUMBER LAST NAME      FIRST NAME     MIDDLE NAME     AGE  SEX  COURSE    YEAR LEVEL CONTACT INFORMATION   EMAIL ADDRESS            ADDRESS");
        for (Student i : studentList) {
            if (Objects.equals(i.getCourse(), course)) {
                displayIndividualTable(i);
            }
        }
    }

}
