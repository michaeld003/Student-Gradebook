import java.util.Arrays;
import java.util.Scanner;

public class XX007 {
    public static void main(String[] args) {
        // This is in order to get the scanner open and have the user put their inputs
        try (Scanner scanner = new Scanner(System.in)) {
            // This will ask the user to provide the number of students that have enrolled in the module 
            System.out.print("State how many students are enrolled in the module: ");
            int numberofstudnts = scanner.nextInt();

            // Print the total number of students entered
            System.out.println("The total number of students: " + numberofstudnts);

            // Making an array to store Student objects
            Student[] Students = new Student[numberofstudnts];

            // The information of the students is collected through a loop
            for (int i = 0; i < numberofstudnts; i++) { // loops until the condition is met which in this case is until the number of students info is put by the user
                System.out.println("Enter the information of student " + (i+1) + ":"); // will be printed on the console with the number the user has inputed
                System.out.print("First Name: "); // user puts the first name
                String FSTNAME = scanner.next();
                System.out.print("SRNAME: "); // user puts the surname
                String SRNAME = scanner.next();
                int[] HomeworkMarks = new int[5];
                for (int j = 0; j < 5; j++) {
                    System.out.print("What are the marks For The Homework " + (j+1) + ": ");
                    HomeworkMarks[j] = scanner.nextInt();
                }
                System.out.print("What are the marks For The Interim Test: "); //user inputs the marks for the interim test marks
                int INTERIMMARK = scanner.nextInt();
                System.out.print("What are the marks For The Exam: "); //user inputs the marks for the exam marks
                double EXAMMARKS = scanner.nextDouble();

                // Create a new Student object with the entered information
                Students[i] = new Student(FSTNAME, SRNAME, HomeworkMarks, INTERIMMARK, EXAMMARKS);
            }

            // Print a table that will consist of the student grades
            System.out.println("\nNumber Of Students: " + numberofstudnts);
            System.out.println("---------------------------------------------------------");
            System.out.println("ID\tName\tSRNAME\tCoursework\tExam\tFinalMark"); // puts it in a table format with those columns
            System.out.println("--------------------------------------------------------");

            // Calculate the grade for each student in a loop
            for (Student student : Students) {
                // It will get the best three homework marks
                int[] BestThreehomeworkMarks = getBestThreeHomeworkMarks(student.getHOMEWORK());
                // Work out the coursework mark (the average of the best three homework marks * 0.6 + interim test mark * 0.4)
                double CWMark = (Arrays.stream(BestThreehomeworkMarks).average().orElse(0) * 0.6) + (student.getITMark() * 0.4);
                // Works out the final mark (coursework mark * 0.4 + exam mark * 0.6)
                double FMark = CWMark * 0.4 + student.getEXAMMARKS() * 0.6;

                // It will give you the student's information and grades
                System.out.printf("%-8s %-8s %-8s %-8.2f%% %-8.2f%% %.2f%%%n", student.getId(), student.getFNAME(), student.getSNAME(), CWMark, student.getEXAMMARKS(), FMark);
            }
        }
    }

    // Collect the best three homework marks
    public static int[] getBestThreeHomeworkMarks(int[] HOMEWORK) {
        Arrays.sort(HOMEWORK);
        return new int[] {HOMEWORK[2], HOMEWORK[3], HOMEWORK[4]};
    }

    // Student class to store information about each student
    public static class Student {
        private static int count = 1; // Static variable to keep track of the number of students
        private String id; // Unique ID
        private String FSTNAME;
        private String SRNAME;
        private int[] HOMEWORK;
        private int INTERIMMARK;
        private double EXAMMARKS;

        public Student(String FNAME, String SNAME, int[] HOMEWORK, int ITMark, double EMark) {
            this.id = String.valueOf(count);
            count++;
            this.FSTNAME = FNAME;
            this.SRNAME = SNAME;
            this.HOMEWORK = HOMEWORK;
            this.INTERIMMARK = ITMark;
            this.EXAMMARKS = EMark;
        }

        public String getId() {
            return id;
        }

        public String getFNAME() {
            return FSTNAME;
        }

        public String getSNAME() {
            return SRNAME;
        }

        public int[] getHOMEWORK() {
            return HOMEWORK;
        }

        public int getITMark() {
            return INTERIMMARK;
        }

        public double getEXAMMARKS() {
            return EXAMMARKS;
        }
    }
    }
