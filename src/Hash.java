import static java.lang.Math.pow;

// Class to represent entire hash table
class Hash {
    // bucketArray is used to store array of chains
    public HashNode[] hashList;

    // Constructor: Initializes capacity, size and empty chains.
    public Hash() {
        hashList = new HashNode[10000000];
    }

    static int hashCode(String str, int hashedTime) {
        long result = 0, number = 0, character = 0, converted = 0;

        for (int i = 0; i < str.length(); i++) {
            converted += str.charAt(i) * pow(37, i);
        }

        result = converted % 10000000;

        return (int) result + hashedTime;
    }

    // Returns value for a key
    public HashNode get(String id) {
        int hashedTime = 0;
        int hashCode = hashCode(id, hashedTime);
        while (true) {
            if (hashList[hashCode].id.compareTo(id) == 0) {
                return hashList[hashCode];
            }
            else {
                hashedTime++;
                hashCode = hashCode(id, hashedTime);
            }
        }
    }

    // Adds a key value pair to hash
    public void add(String id, String firstName, String lastName, String phone) {
        int hashedTime = 0;
        int hashedID = hashCode(id, hashedTime);
//        System.out.println(id + " " + hashedID);

        // Check if key is already present
        while (true) {
            if (hashList[hashedID] == null) {
                hashList[hashedID] = new HashNode(id, firstName, lastName, phone, hashedID);
                return;
            }
            else {
                hashedTime++;
                hashedID = hashCode(id, hashedTime);
            }
        }
    }
}