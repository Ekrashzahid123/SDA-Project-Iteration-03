import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create Admin instance (Singleton)
        Admin admin = Admin.getInstance("1", "Admin");

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Users");
            System.out.println("2. Handle Disputes");
            System.out.println("3. View System Statistics");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();
            input.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Manage Users
                    admin.manageUsers();
                    break;
                case 2:
                    // Handle Disputes
                    admin.handleDisputes();
                    break;
                case 3:
                    // View System Statistics
                    admin.viewSystemStatistics();
                    break;
                case 4:
                    // Exit the program
                    System.out.println("Exiting...");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
