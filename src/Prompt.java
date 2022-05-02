import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Prompt {

    static Hash map = new Hash();
    static Trie tree = new Trie();

    // Best: 1
    // Worst: n
    static void insertPrompt() {
        Scanner scanner = new Scanner(System.in);
        String[] detail = new String[4];

        System.out.println("Please enter customer's detail");
        System.out.print("Customer ID: ");
        detail[0] = scanner.nextLine().toUpperCase();
        System.out.print("First name: ");
        detail[1] = scanner.nextLine();
        System.out.print("Last name: ");
        detail[2] = scanner.nextLine();
        System.out.print("Phone number: ");
        detail[3] = scanner.nextLine();

        tree.insert(detail[0]);
        map.add(detail[0], detail[1], detail[2], detail[3]);
    }

    // Best: 1
    // Worst: 1
    static void updatePrompt() {
        Scanner scanner = new Scanner(System.in);
        String detail;

        System.out.println("Please enter customer's detail\nIf you want to remain the same data, please enter null");
        System.out.print("Enter ID to update:");
        String id = scanner.nextLine().toUpperCase();
        if(tree.isInTrie(id)) {
            System.out.print("First name: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                map.get(id).firstName = detail;
            }
            System.out.print("Last name: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                map.get(id).lastName = detail;
            }
            System.out.print("Phone number: ");
            detail = scanner.nextLine();
            if (!Objects.equals(detail, "null")) {
                map.get(id).phone = detail;
            }
        }
    }

    // Best: 1
    // Worst: n
    static void searchFullPrompt() {
        Scanner scanner = new Scanner(System.in);
        String id;
        System.out.print("Enter ID to search: ");
        id = scanner.nextLine().toUpperCase();
        if (tree.isInTrie(id))
            map.get(id).printNode();
        else
            System.out.println("There is no customer with id " + id);
    }

    // Best: 1
    // Worst: n
    static void searchPartialPrompt() {
        Scanner scanner = new Scanner(System.in);
        String id;
        System.out.print("Enter ID to search: ");
        id = scanner.nextLine().toUpperCase();
        String[] listID = tree.searchPartial(id);
        if (listID != null) {
            for (String str : listID) {
//                if (str != null)
//                    map.get(str).printNode();
                System.out.println(str);
            }
        }
        else {
            System.out.println("There is no user.");
        }
    }

    static void readFile(String path){
        tree.root = new TrieNode();
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Remove the first line
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] customer = line.split(",");
                tree.insert(customer[0]);
                map.add(customer[0], customer[1], customer[2], customer[3]);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    static int prompt() {
        //creating Scanner class object to get input from user
        Scanner scanner = new Scanner(System.in);
        //initialize a character type variable to choice
        String choice;

        // perform operation of AVL Tree using switch
        System.out.println("\nSelect an operation:");
        System.out.println("1. Add a customer");
        System.out.println("1. Update a customer");
        System.out.println("3. Search full a customer");
        System.out.println("4. Search partial a customer");
        System.out.println("Enter \"E\" to exit");

        //get choice from user
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();
        if (choice.toUpperCase().charAt(0) == 'E')
            return -1;
        else if (isNumeric(choice))
            return Integer.parseInt(choice);

        return -2;
    }

    static void runCase() {
        while (true) {
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
        readFile("src/test.csv");
        runCase();
    }
}
