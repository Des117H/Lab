import static java.lang.Math.*;

// Class to represent entire hash table
class Hash {
    // Size of array
    static final int SIZE = 10000000;
    // Array is used to store value
    public HashNode[] hashList;

    // Constructor: Initializes capacity, size and empty chains.
    public Hash() {
        hashList = new HashNode[SIZE];
    }

    // Hash function
    int hashCode(String str) {
        long converted = 0;
        for (int i = 0; i < str.length(); i++) {
            converted += str.charAt(i) * pow(37, i);
        }
        return toIntExact(converted % SIZE);
    }

    int hashCode2(String str) {
        long converted = 0;
        for (int i = 0; i < str.length(); i++) {
            converted += str.charAt(i) * pow(97, i);
        }
        return toIntExact(converted % SIZE);
    }

    // Returns value for a key
    public HashNode get(String id) {
        int hashedTime = 0;
        int hashedID = hashCode(id) % SIZE;
        while (true) {
            if (hashList[hashedID].id.compareTo(id) == 0) {
                return hashList[hashedID];
            }
            else {
                hashedID = (hashCode(id) + (++hashedTime) * hashCode2(id)) % SIZE;
            }
        }
    }

    // Adds a key value pair to hash
    public void add(String id, String firstName, String lastName, String phone) {
        int hashedTime = 0;
        int hashedID = hashCode(id);

        // Check if key is already present
        while (true) {
            if (hashList[hashedID] == null) {
                hashList[hashedID] = new HashNode(id, firstName, lastName, phone, hashedID);
                return;
            }
            else
                hashedID = (hashCode(id) + (++hashedTime) * hashCode2(id));
        }
    }
}