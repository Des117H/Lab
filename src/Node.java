public class Node {
    String id;
    String firstName;
    String lastName;
    String phone;
    int height;
    Node left, right;

    public Node(String id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        left = right = null;
        height = 1;
    }
    public Node() {
        this.id = "";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        left = right = null;
        height = 1;
    }

    void printNode() {
        if (this != null)
//            (id.charAt(0) - 'A' + 1)
            System.out.println( id + " " + firstName + " " + lastName + " " + phone);
        else
            System.out.println("null");
    }
}