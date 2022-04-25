// Hash node
class HashNode {
    String id;
    String firstName;
    String lastName;
    String phone;

    // Constructor
    public HashNode(String id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public void printNode() {
        System.out.println(id + " " + firstName + " " + lastName + " " + phone);
    }
}