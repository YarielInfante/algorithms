package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<String, Integer> indexes = new HashMap<>();
    private int currentIndex;
    private List<LinkedList<Node>> edges = new ArrayList<LinkedList<Node>>();

    class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }
    }

    public void addNode(String label) {
        indexes.putIfAbsent(label, currentIndex);
        edges.add(currentIndex, new LinkedList<>());
        currentIndex++;
    }

    public void addEdge(String from, String to) {
        var fromIndex = indexes.get(from);
        var toIndex = indexes.get(to);

        LinkedList<Node> fromEdges = edges.get(fromIndex);
        fromEdges.add(new Node(to));
    }

    public void print() {
        for (var entry : indexes.entrySet()) {
            String from = entry.getKey();
            int fromIndex = entry.getValue();

            LinkedList<Node> nodes = edges.get(fromIndex);
            String connections = nodes.stream().map(n -> n.label).collect(Collectors.joining(", "));
            System.out.println(from + " is connected to " + connections);
        }
    }


}
