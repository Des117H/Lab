class HashNode<I, F, L, P> {
    I id;
    F firstName;
    L lastName;
    P phone;
    final int hashCode;

    // Reference to next node
    HashNode<I, F, L, P> next;

    // Constructor
    public HashNode(I id, F firstName, L lastName, P phone, int hashCode)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hashCode = hashCode;
    }
}