// Trie node
class TrieNode {
    static final int SIZE = 36;
    TrieNode[] children = new TrieNode[SIZE];

    // isEndOfWord is true if the node represents end of a word
    boolean isEndOfWord;

    // Constructor
    public TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < SIZE; i++)
            children[i] = null;
    }
}