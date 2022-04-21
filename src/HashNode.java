class HashNode {
    String id;
    String firstName;
    String lastName;
    String phone;
    int hashCode;
    int hashedTime;

    // Constructor
    public HashNode() {
        this.id = "";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
    }

    public HashNode(String id, String firstName, String lastName, String phone, int hashCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hashCode = hashCode;
    }

    public void printNode() {
        if (this == null) {
            System.out.println("There is no user");
            return;
        }
        System.out.println(id + " " + firstName + " " + lastName + " " + phone);
    }
}