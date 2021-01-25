package com.company;

import java.util.Arrays;

public class Trie {
    class Node {
        private char value;
        private Node[] children;
        private boolean isEndOfWord;

        public Node(char value, Node[] children, boolean isEndOfWord) {
            this.value = value;
            this.children = children;
            this.isEndOfWord = isEndOfWord;
        }
    }

    private Node root = new Node('r', new Node[26], false);

    public void insert(String word) {
        var current = root;
        var letters = word.toCharArray();

        for (char ch : letters) {
            var childFound = false;
            for (Node child : current.children) {
                if (child != null && child.value == ch) {
                    childFound = true;
                    current = child;
                    break;
                }
            }

            if (!childFound) {
                var node = new Node(ch, new Node[26], false);
                current.children[ch - 'a'] = node;
                current = node;
            }

        }
        current.isEndOfWord = true;
    }

    public boolean hasChar(Node node, char ch) {
        for (Node child : node.children) {
            if (child.value == ch)
                return true;
        }
        return false;
    }
}
