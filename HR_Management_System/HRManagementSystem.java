import java.sql.*;
import java.util.Scanner;

public class HRManagementSystem {

    static final String URL = "jdbc:mysql://localhost:3306/hr_db";
    static final String USER = "root";
    static final String PASSWORD = "reshma@123"; // change if needed

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== HR MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addEmployee() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            sc.nextLine(); // clear buffer
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Role: ");
            String role = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            String query = "INSERT INTO employees (name, role, salary) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, role);
            ps.setDouble(3, salary);

            ps.executeUpdate();
            System.out.println("Employee added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void viewEmployees() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = "SELECT * FROM employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\nID | Name | Role | Salary");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("role") + " | " +
                        rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void updateEmployee() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            String query = "UPDATE employees SET salary = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Updated successfully!");
            else
                System.out.println("Employee not found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void deleteEmployee() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Deleted successfully!");
            else
                System.out.println("Employee not found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}