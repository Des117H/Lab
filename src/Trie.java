import static java.lang.Character.isDigit;

public class Trie {
    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 36;
    TrieNode root;
    static String[] id = new String[10];
    static int size = 0;

    // If not present, inserts key into trie
    // If the key is prefix of trie node, just marks leaf node
    // Best: 10
    // Worst: 10
    void insert(String key) {
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++)
        {
            if (isDigit(key.charAt(level)))
                index = key.charAt(level) - '0' + 26;
            else
                index = key.charAt(level) - 'A';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    // Best: 10
    // Worst: 10
    boolean isInTrie(String word) {
        int length = word.length();
        int index;
        TrieNode node = root;

        for (int level = 0; level < length; level++)
        {
            if (isDigit(word.charAt(level)))
                index = word.charAt(level) - '0' + 26;
            else
                index = word.charAt(level) - 'A';

            if (node.children[index] == null)
                return false;

            node = node.children[index];
        }

        return node.isEndOfWord;
    }

    // Best: (10 - W) * S
    // Worst: (10 - W) * S
    String[] searchPartial(String partial) {

        int length = partial.length();
        TrieNode pCrawl = root;

        // check if the partial appear in tree
        for (int level = 0; level < length; level++)
        {
            int index;
            if (isDigit(partial.charAt(level)))
                index = partial.charAt(level) - '0' + 26;
            else
                index = partial.charAt(level) - 'A';

            // if no then quit
            if (pCrawl.children[index] == null)
                return null;

            pCrawl = pCrawl.children[index];
        }
        // reset the array id
        id = reset();
        partialRecursion(pCrawl, partial);

        return id;
    }

    void partialRecursion (TrieNode node, String partial) {
        if (node == null)
            return;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            String temp = partial;
            if (node.children[i] != null) {
                if (i > 25)
                    temp += i - 26;
                else
                    temp += 'A' + i;

                partialRecursion(node.children[i], temp);

                if (node.children[i].isEndOfWord) {
                    id[size++] = temp;
                }

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
