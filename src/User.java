import java.util.Scanner;

public class User {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Accessors                         
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    // Mutators
    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Mehods
    public void manageProfile() {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Managing profile for user: " + name);
        System.out.println("1. Update Name");
        System.out.println("2. Update Email");
        System.out.println("3. Update Phone");
        System.out.println("4. Update Password");
        System.out.println("Enter your choice: ");

        choice = input.nextInt();

        switch(choice) {
            case 1:
                System.out.print("Enter new name: ");
                this.name = input.nextLine();
                break;
            case 2:
                System.out.print("Enter new email: ");
                this.email = input.nextLine();
                break;
            case 3:
                System.out.print("Enter new phone: ");
                this.phone = input.nextLine();
                break;
            case 4:
                System.out.print("Enter new password: ");
                this.password = input.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        input.close();

        System.out.println("Successfully Updated");
    }
}
//commit