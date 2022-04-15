import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Prompt {
    static void insertPrompt(AVLTree tree) {
        Scanner scanner = new Scanner(System.in);
        String[] detail = new String[4];

        System.out.println("Please enter customer's detail");
        System.out.print("Customer ID: ");
        detail[0] = scanner.nextLine();
        System.out.print("First name: ");
        detail[1] = scanner.nextLine();
        System.out.print("Last name: ");
        detail[2] = scanner.nextLine();
        System.out.print("Phone number: ");
        detail[3] = scanner.nextLine();

        tree.insert(tree.root, detail[0], detail[1], detail[2], detail[3]);
    }

    static AVLTree readFile(String path){
        AVLTree tree = new AVLTree();
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Remove the first line
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] customer = line.split(",");
                tree.root = tree.insert(tree.root, customer[0], customer[1], customer[2], customer[3]);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return tree;
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    static int prompt() {
        //creating Scanner class object to get input from user
        Scanner scanner = new Scanner(System.in);
        //initialize a character type variable to choice
        String choice;

        // perform operation of AVL Tree using switch
        System.out.println("\nSelect an operation:");
        System.out.println("1. Insert a node");
        System.out.println("2. Search a node");
        System.out.println("3. Get total number of nodes in AVL Tree");
        System.out.println("4. Is AVL Tree empty?");
        System.out.println("5. Remove all nodes from AVL Tree");
        System.out.println("6. Display AVL Tree in Post order");
        System.out.println("7. Display AVL Tree in Pre order");
        System.out.println("8. Display AVL Tree in In order");
        System.out.println("Enter \"E\" to exit: ");

        //get choice from user
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();
        if (choice.toUpperCase().charAt(0) == 'E')
            return -1;
        else if (isNumeric(choice))
            return Integer.parseInt(choice);

        return -2;
    }

    static void runCase(AVLTree tree) {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            switch (prompt()) {
                case 1:
                    insertPrompt(tree);
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    String id = scanner.nextLine();
                    id = scanner.nextLine();
                    Node temp = tree.search(tree.root, id);
                    temp.printNode();
                    break;
                case 3:
//                    System.out.println(tree.getTotalNumberOfNodes());
                    break;
                case 4:
                    System.out.println(tree.checkEmpty());
                    break;
                case 5:
//                    tree.removeAll();
                    System.out.println("Tree Cleared successfully");
                    break;
                case 6:
                    System.out.println("Display AVL Tree in Post order");
//                    tree.postorderTraversal();
                    break;
                case 7:
                    System.out.println("Display AVL Tree in Pre order");
                    tree.preOrder(tree.root);
                    break;
                case -1:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Wrong usage!!!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        runCase(readFile("src/test.csv"));
    }
}
