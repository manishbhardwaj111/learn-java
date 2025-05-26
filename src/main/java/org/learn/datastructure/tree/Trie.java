package org.learn.datastructure.tree;

public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        var current = root;
        for(char ch: word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        current.endOfString = true;
    }

    public boolean search(String word) {
        var current = root;
        for (char ch: word.toCharArray()) {
            var node = current.children.get(ch);
            if (node == null) {
                return false;
            } else
                current = node;
        }
        return current.endOfString;
    }

    public void delete(String word) {
        if (search(word)) {
            System.out.println("Search word found = " + word);
            delete(root, word, 0);
        } else {
            System.out.println("Word not found = " + word);
        }
    }

    private boolean delete(TrieNode parentNode, String word, int index) {
        char ch = word.charAt(index);
        var currentNode = parentNode.children.get(ch);
        boolean canThisNodeBeDeleted;

        if (currentNode.children.size() > 1) { // Prefix of other words
            delete(currentNode, word, index + 1);
            return false;
        }

        if (index == word.length() - 1) { // Last word
            if (!currentNode.children.isEmpty()) { // Prefix of other words
                currentNode.endOfString = false;
                return false;
            } else {
                parentNode.children.remove(ch);
                return true;
            }
        }
        if (currentNode.endOfString) {
            delete(currentNode, word, index + 1);
            return false;
        }
        canThisNodeBeDeleted = delete(currentNode, word, index + 1);
        if (canThisNodeBeDeleted) {
            parentNode.children.remove(ch);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        var trie = new Trie();
        trie.insert("ABC");
        System.out.println(trie.search("ABC"));
    }
}
