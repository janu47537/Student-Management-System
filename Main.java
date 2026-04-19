import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadFromFile(); // Load existing data

        while (true) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    students.add(new Student(roll, name, marks));
                    saveToFile();
                    System.out.println("Student Added!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No records found!");
                    } else {
                        for (Student s : students) {
                            System.out.println(s.rollNo + " " + s.name + " " + s.marks);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll No to Search: ");
                    int searchRoll = sc.nextInt();

                    boolean found = false;

                    for (Student s : students) {
                        if (s.rollNo == searchRoll) {
                            System.out.println("Found: " + s.rollNo + " " + s.name + " " + s.marks);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to Update: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();

                    boolean updated = false;

                    for (Student s : students) {
                        if (s.rollNo == updateRoll) {

                            System.out.print("Enter New Name: ");
                            s.name = sc.nextLine();

                            System.out.print("Enter New Marks: ");
                            s.marks = sc.nextDouble();

                            saveToFile();
                            System.out.println("Student Updated!");
                            updated = true;
                        }
                    }

                    if (!updated) {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Roll No to Delete: ");
                    int deleteRoll = sc.nextInt();

                    boolean removed = false;

                    Iterator<Student> it = students.iterator();

                    while (it.hasNext()) {
                        Student s = it.next();
                        if (s.rollNo == deleteRoll) {
                            it.remove();
                            saveToFile();
                            System.out.println("Student Deleted!");
                            removed = true;
                        }
                    }

                    if (!removed) {
                        System.out.println("Student not found!");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // SAVE DATA TO FILE
    static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("students.txt");

            for (Student s : students) {
                fw.write(s.rollNo + "," + s.name + "," + s.marks + "\n");
            }

            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    // LOAD DATA FROM FILE
    static void loadFromFile() {
        try {
            File file = new File("students.txt");

            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                ));
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading file!");
        }
    }
}