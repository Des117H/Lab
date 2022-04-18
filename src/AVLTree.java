import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

//https://www.javatpoint.com/avl-tree-program-in-java
//https://www.geeksforgeeks.org/avl-tree-set-1-insertion/

public class AVLTree {
    Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
        int left, right;
        left = height(N.left);
        right = height(N.right);

        return Math.max(left,right) + 1;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node root) {
//        System.out.println("p");
        Node p = root.left;
        if (p != null) {
            root.left = p.right;

            // Perform rotation
            p.right = root;
        }
        else
            return root;
//        // Return new root
        return p;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node root) {
//        System.out.println("t");
        Node p = root.right;
        if (p != null) {
            root.right = p.left;

            // Perform rotation
            p.left = root;
        }
        else
            return root;
        // Return new root
        return p;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public boolean checkEmpty() {
        return root == null;
    }

    Node insert(Node node, String id, String firstName, String lastName, String phone) {
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(id, firstName, lastName, phone));

        if (node.id.compareTo(id) > 0) {
            node.left = insert(node.left, id, firstName, lastName, phone);
        }
        else if (node.id.compareTo(id) < 0) {
            node.right = insert(node.right, id, firstName, lastName, phone);
        }
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

        /* 3. Get the balance factor of this ancestor node to check whether this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && node.left.id.compareTo(id) > 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && node.right.id.compareTo(id) < 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && node.left.id.compareTo(id) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && node.right.id.compareTo(id) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    Node search(Node node, String id) {
        if (node == null)
            return null;

        if (node.id.compareTo(id) > 0){
            return search(node.left, id);
        }

        else if (node.id.compareTo(id) < 0){
            return search(node.right, id);
        }
        else
            return node;
    }

    private boolean isInTree (String key, Node node){
        if (node == null)
            return false;

        if (key.compareTo (node.id) < 0)
            return isInTree (key, node.left);

        if (key.compareTo (node.id) > 0)
            return isInTree (key, node.right);
        return true;
    }

    public String[] getBestMatches(String word) {
        if (isInTree(word.toLowerCase(), root))
            return new String[] { word };

        if (word.length() <= 1)
            return new String[0];

        // strip off last letter and search for first node that "starts with" the term
        String searchTerm = word;
        Node bestPartialMatchedNode = null;

        do {
            searchTerm = searchTerm.substring(0, word.length()).toUpperCase();
            bestPartialMatchedNode = getBestPartialMatch(searchTerm, root);
            System.out.println(searchTerm.length());
        }
        while (searchTerm.length() > 1 && bestPartialMatchedNode == null);

        if (bestPartialMatchedNode == null)
            return new String[0];            // nothing to suggest

        LinkedList<String> bestMatches = new LinkedList<String>();
        LinkedList<Node> currentLevel = new LinkedList<Node>();
        LinkedList<Node> nextLevel = new LinkedList<Node>();
        currentLevel.push(bestPartialMatchedNode);

        while(!currentLevel.isEmpty() && bestMatches.size() < 10){
            Node node = currentLevel.pollLast();

            if (node != null) {
                if (node.id.startsWith(searchTerm))
                    bestMatches.add(node.id);

                if(node.left != null)
                    nextLevel.push(node.left);

                if(node.right != null)
                    nextLevel.push(node.right);

                if (currentLevel.isEmpty()) {
                    LinkedList<Node> temp = nextLevel;
                    nextLevel = currentLevel;
                    currentLevel = temp;
                }
            }
        }
        Collections.sort(bestMatches);
        return bestMatches.toArray(new String[bestMatches.size()]);
    }

    private Node getBestPartialMatch(String key, Node node) {
        if (node == null)
            return null;

        if (node.id.startsWith(key))
            return node;

        if (key.compareTo (node.id) < 0)
            return getBestPartialMatch (key, node.left);

        if (key.compareTo (node.id) > 0)
            return getBestPartialMatch (key, node.right);

        return null;
    }

    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every node
    void preOrder(Node node) {
        if (node != null) {
            node.printNode();
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}