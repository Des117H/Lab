class HashNode {
    String id;
    String firstName;
    String lastName;
    String phone;
    int hashCode;

    // Constructor
    public HashNode(String id, String firstName, String lastName, String phone, int hashCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hashCode = hashCode;
    }

    public void printNode() {
        System.out.println(id + " " + firstName + " " + lastName + " " + phone);
    }
}