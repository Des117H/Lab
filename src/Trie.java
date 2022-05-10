import static java.lang.Character.isDigit;

public class Trie {
    // Alphabet size (# of symbols)
    static final int TRIE_SIZE = 36;
    TrieNode root;
    static String[] id = new String[10];
    static int size = 0;

    // If not present, inserts key into trie
    // If the key is prefix of trie node, just marks leaf node
    // Best: n & Worst: n
    void insert(String word) {
        // Get the length of key
        int length = word.length();
        // Index of character
        int index;
        // Get the root node
        TrieNode node = root;

        for (int level = 0; level < length; level++) {
            if (isDigit(word.charAt(level)))
                // Because there is number in this trie,
                // the number must add 26 to fit in the array
                index = word.charAt(level) - '0' + 26;
            else
                index = word.charAt(level) - 'A';

            // If the next index is null, make it a new node
            if (node.children[index] == null)
                node.children[index] = new TrieNode();

            // Go to the next node
            node = node.children[index];
        }

        // mark last node as leaf
        node.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    // Best: n & Worst: n
    boolean isInTrie(String word) {
        // Get the length of key
        int length = word.length();
        // Index of character
        int index;
        // Get the root node
        TrieNode node = root;

        // Check if the word appear in tree
        for (int level = 0; level < length; level++) {
            // Get the index of character
            if (isDigit(word.charAt(level)))
                // Because there is number in this trie,
                // the number must add 26 to fit in the array
                index = word.charAt(level) - '0' + 26;
            else
                index = word.charAt(level) - 'A';

            // If no then quit
            if (node.children[index] == null)
                return false;

            // Go to the next node
            node = node.children[index];
        }

        return node.isEndOfWord;
    }

    // Best: n! & Worst: n!
    String[] searchPartial(String partial) {
        // Get the length of partial key
        int length = partial.length();
        // Index of character
        int index;
        // Get the root node
        TrieNode node = root;

        // Check if the partial appear in tree
        for (int level = 0; level < length; level++) {
            // Get the index of character
            if (isDigit(partial.charAt(level)))
                // Because there is number in this trie,
                // the number must add 26 to fit in the array
                index = partial.charAt(level) - '0' + 26;
            else
                index = partial.charAt(level) - 'A';

            // If no then quit
            if (node.children[index] == null)
                return null;

            // Go to the next node
            node = node.children[index];
        }
        // reset the array id
        id = reset();
        // Get the lst of id by recursion
        partialRecursion(node, partial);

        return id;
    }

    // O(n!)
    void partialRecursion (TrieNode node, String partial) {
        // If come to the end but there is no word, exit
        if (node == null)
            return;

        // For loop to get each character of the ID
        for (int i = 0; i < TRIE_SIZE; i++) {
            String temp = partial;


            if (node.children[i] != null) {
                // Get character
                if (i > 25)
                    temp += i - 26;
                else
                    temp += Character.toString('A' + i);

                partialRecursion(node.children[i], temp);

                // If it is end of word, add word to ID's list
                if (node.children[i].isEndOfWord) {
                    id[size++] = temp;
                }

                // If the size of partial list larger than 9, quit
                if (size > 9)
                    return;
            }
        }
    }

    String[] reset() {
        size = 0;
        return new String[10];
    }
}
