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

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src/customers.csv"));

            // Remove the first line
            String line = br.readLine();

            while (line != null) {
                String[] customer = line.split(",");
                tree.root = tree.insert(tree.root, customer[0], customer[1], customer[2], customer[3]);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //creating Scanner class object to get input from user
        Scanner scanner = new Scanner(System.in);
        //initialize a character type variable to choice
        char choice;

        // perform operation of AVL Tree using switch
        do
        {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Insert a node");
            System.out.println("2. Search a node");
            System.out.println("3. Get total number of nodes in AVL Tree");
            System.out.println("4. Is AVL Tree empty?");
            System.out.println("5. Remove all nodes from AVL Tree");
            System.out.println("6. Display AVL Tree in Post order");
            System.out.println("7. Display AVL Tree in Pre order");
            System.out.println("8. Display AVL Tree in In order");

            //get choice from user
            System.out.print("Enter your choice: ");
            choice = (char) scanner.nextInt();
            switch (choice) {
                case 1 :
                    insertPrompt(tree);
                    break;
                case 2 :
//                    System.out.print("Enter ID to search: ");
//                    String id = scanner.nextLine();
//                    id = scanner.nextLine();
                    Node temp = tree.search(tree.root, "TAZ7897764");
                    if (temp != null)
                        temp.printNode();
                    else
                        System.out.println("null");
                    break;
                case 3 :
//                    System.out.println(tree.getTotalNumberOfNodes());
                    break;
                case 4 :
                    System.out.println(tree.checkEmpty());
                    break;
                case 5 :
//                    tree.removeAll();
                    System.out.println("\nTree Cleared successfully");
                    break;
                case 6 :
                    System.out.println("\nDisplay AVL Tree in Post order");
//                    tree.postorderTraversal();
                    break;
                case 7 :
                    System.out.println("\nDisplay AVL Tree in Pre order");
                    tree.preOrder(tree.root);
                    break;
                case 8 :
                    System.out.println("\nDisplay AVL Tree in In order");
//                    tree.inorderTraversal();
                    break;
                default :
                    System.out.println("\n ");
                    break;
            }
            System.out.println("\nPress 'y' or 'Y' to continue: ");
            choice = scanner.next().charAt(0);
        } while (choice == 'Y'|| choice == 'y');
    }
}
