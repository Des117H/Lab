import static java.lang.Math.pow;

// Class to represent entire hash table
class Map<I, F, L, P> {
    // bucketArray is used to store array of chains
    private LinkedList<HashNode<I, F, L, P>> bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    // Constructor (Initializes capacity, size and
    // empty chains.
    public Map() {
        bucketArray = new LinkedList<>();
        numBuckets = 10;
        size = 0;

        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.push(null);
    }

    static int hashCode(String str) {
        long result = 0, number = 0, character = 0, converted = 0;

        for (int i = 0; i < str.length(); i++) {
            converted += str.charAt(i) * pow(37, i);
        }

        result = converted % 10000000;

        return (int) result;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    // This implements hash function to find index
    // for a key
    private int getBucketIndex(I key) {
        int hashCode = hashCode((String) key);
        int index = hashCode % numBuckets;
        // key.hashCode() could be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }

    // Returns value for a key
    public HashNode get(I id) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(id);
        int hashCode = hashCode((String) id);

        HashNode<I, F, L, P> head = bucketArray.get(bucketIndex);

        // Search key in chain
        while (head != null) {
            if (head.id.equals(id) && head.hashCode == hashCode)
                return head;
            head = head.next;
        }

        // If key not found
        return null;
    }

    // Adds a key value pair to hash
    public void add(I id, F firstName, L lastName, P phone) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(id);
        int hashCode = hashCode((String) id);
        HashNode<I, F, L, P> head = bucketArray.get(bucketIndex);

        // Check if key is already present
        while (head != null) {
            if (head.id.equals(id) && head.hashCode == hashCode) {
                head.id = id;
                head.firstName = firstName;
                head.lastName = lastName;
                head.phone = phone;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<I, F, L, P> newNode
                = new HashNode<I, F, L, P>(id, firstName, lastName, phone, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= 0.7) {
            LinkedList<HashNode<I, F, L, P> > temp = bucketArray;
            bucketArray = new LinkedList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.push(null);



            for (HashNode<I, F, L, P> headNode : temp) {
                while (headNode != null) {
                    add(headNode.id, headNode.firstName, headNode.lastName, headNode.phone);
                    headNode = headNode.next;
                }
            }
        }
    }
}