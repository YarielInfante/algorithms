package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {

    class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }
    }

    class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    Map<String, Node> nodes = new HashMap<>();
    Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        var nodeFrom = nodes.get(from);
        var nodeTo = nodes.get(to);

        var edgeFrom = new Edge(nodeFrom, nodeTo, weight);
        var edgeTo = new Edge(nodeTo, nodeFrom, weight);

        adjacencyList.get(nodeFrom).add(edgeTo);
        adjacencyList.get(nodeTo).add(edgeFrom);
    }
}
