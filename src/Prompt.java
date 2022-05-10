import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Prompt {

    static Hash hashArray = new Hash();
    static Trie tree = new Trie();

    // Insert function
    // Best: n & Worst: n
    static void insertPrompt() {
        // Initialize scanner and data's container
        Scanner scanner = new Scanner(System.in);
        String[] detail = new String[4];

        // Get customer's ID
        System.out.println("Please enter customer's detail");
        System.out.print("Customer ID: ");
        detail[0] = scanner.nextLine().toUpperCase();

        // Check if the ID already exist or not
        // If not, continue get other data
        if (!tree.isInTrie(detail[0])) {
            // Get customer's first name
            System.out.print("First name: ");
            detail[1] = scanner.nextLine();
            // Get customer's last name
            System.out.print("Last name: ");
            detail[2] = scanner.nextLine();
            // Get customer's phone number
            System.out.print("Phone number: ");
            detail[3] = scanner.nextLine();

            // Insert customer's data into database
            // Best: N & Worst: N
            tree.insert(detail[0]);
            // Best: 1 & Worst: N
            hashArray.add(detail[0], detail[1], detail[2], detail[3]);
        }
        // If ID exist, notify user
        else
            System.out.println("User already exist!");
    }

    // Update function
    // Best: 1 & Worst: 1
    static void updatePrompt() {
        // Initialize scanner and data's container
        Scanner scanner = new Scanner(System.in);
        String detail;

        // Get customer's ID
        System.out.print("Enter ID to update:");
        String id = scanner.nextLine().toUpperCase();

        // Check if the ID already exist or not
        // If yes, continue get other data
        // Best: N & Worst: N
        if (tree.isInTrie(id)) {
            // Prompt user how to enter data
            System.out.println("Please enter customer's detail");
            System.out.println("If you want to remain the same data, please enter null");

            // Get customer's first name
            System.out.print("First name: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                hashArray.get(id).firstName = detail;
            }

            // Get customer's last name
            System.out.print("Last name: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                hashArray.get(id).lastName = detail;
            }

            // Get customer's phone number
            System.out.print("Phone number: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                hashArray.get(id).phone = detail;
            }
        }
        // If not, notify user
        else
            System.out.println("The ID do not exist!");
    }

    // Search full ID function
    // Best: 1 &  Worst: n
    static void searchFullPrompt() {
        //  Initialize scanner and data's container
        Scanner scanner = new Scanner(System.in);
        String id;

        // Get customer's ID
        System.out.print("Enter ID to search: ");
        id = scanner.nextLine().toUpperCase();

        // Check if ID is in database or not
        // If yes, print customer's data
        // Best: 1 & Worst: N
        if (tree.isInTrie(id))
            // Best: 1 & Worst: N
            hashArray.get(id).printNode();
            // If no, notify user
        else
            System.out.println("There is no customer with id: " + id);
    }

    // Search partial ID function
    // Best: n! & Worst: n!
    static void searchPartialPrompt() {
        //  Initialize scanner and data's container
        Scanner scanner = new Scanner(System.in);
        String id;

        // Get customer's partial ID
        System.out.print("Enter ID to search: ");
        id = scanner.nextLine().toUpperCase();

        // Get list of customer
        String[] listID = tree.searchPartial(id);

        // Check if the list contain customer
        // If yes, print customer's data
        if (listID != null) {
            for (String str : listID) {
                // If there are ID
                if (str != null)
                    // Print customer's data
                    hashArray.get(str).printNode();
            }
        }
        // If no, notify user
        else {
            System.out.println("There is no user.");
        }
    }

    // Function to get data from file
    static void readFile() {
        // Initialize trie
        tree.root = new TrieNode();
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src/customers.csv"));

            // Remove the first line
            String line;
            br.readLine();

            // Get data from second line till the end
            while ((line = br.readLine()) != null) {
                // Split the gotten line by the comma
                String[] customer = line.split(",");

                // Insert ID to trie
                tree.insert(customer[0]);
                // Insert data to hash array
                hashArray.add(customer[0], customer[1], customer[2], customer[3]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Check if string contain only number
    static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

    // UI of teh project
    static int prompt() {
        // Creating Scanner class object to get input from user
        Scanner scanner = new Scanner(System.in);
        // Initialize choice of action
        String choice;

        // Show operation's list
        System.out.println("\nSelect an operation:");
        System.out.println("1. Add a customer");
        System.out.println("2. Update a customer");
        System.out.println("3. Search full a customer");
        System.out.println("4. Search partial a customer");
        System.out.println("Enter \"E\" to exit");

        // Get choice from user
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();

        // Check if user want to quit
        // If yes, exit
        if (choice.toUpperCase().charAt(0) == 'E')
            return -1;
        // If not, check if user enter a number
        // If yes, return the number
        else if (isNumeric(choice))
            return Integer.parseInt(choice);

        // Return if user enter other character
        return -2;
    }

    static void runCase() {
        // Run the code
        while (true) {
            // Switch between 4 function
            switch (prompt()) {
                case 1 -> insertPrompt();
                case 2 -> updatePrompt();
                case 3 -> searchFullPrompt();
                case 4 -> searchPartialPrompt();
                case -1 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Wrong usage!!!");
            }
        }
    }

    public static void main(String[] args) {
        readFile();
        runCase();
    }
}
