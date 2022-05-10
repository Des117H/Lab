import static java.lang.Math.*;

// Class to represent entire hash table
class Hash {
    // Size of array
    final int SIZE = 10000000;
    // Array is used to store value
    public HashNode[] hashList;
    // Number of element in array
    private int elementNumber = 0;

    // Constructor
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
    // Best: 1
    // Worst: n
    public HashNode get(String id) {
        // Set the hashed time
        int hashedTime = 0;
        // Hash first time
        int hashedID = hashCode(id);
        // Check for collision
        while (true) {
            // If there is no collision, return the data
            if (hashList[hashedID].id.compareTo(id) == 0) {
                return hashList[hashedID];
            }
            // Else, double hash
            else {
                hashedID = (hashCode(id) + (++hashedTime) * hashCode2(id)) % SIZE;
            }
        }
    }

    // Adds a key value pair to hash
    // Best: 1
    // Worst: n
    public void add(String id, String firstName, String lastName, String phone) {
        // Set the hashed time
        int hashedTime = 0;
        // Hash first time
        int hashedID = hashCode(id);

        // Check if key is already contain data
        while (true) {
            // If yes, add data into the node
            if (hashList[hashedID] == null) {
                hashList[hashedID] = new HashNode(id, firstName, lastName, phone);
                elementNumber++;
                return;
            }
            // If no, double hash
            else
                hashedID = (hashCode(id) + (++hashedTime) * hashCode2(id));
            // If the array is full, notify user and exit
            if (elementNumber > SIZE) {
                System.out.println("Array is full");
                return;
            }
        }
    }
}