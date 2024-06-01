package com.ddup.algorithm.basic;

import java.util.*;

public class GeneralGraph {
    private Map<Integer, Node> nodes;
    private List<Edge> edges;

    public GeneralGraph() {
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    public GeneralGraph generateGraph(int[][] matrix) {
        for (int[] ints : matrix) {
            int from = ints[0];
            int to = ints[1];
            int weight = ints[2];
            if (!nodes.containsKey(from)) {
                nodes.put(from, new Node(from));
            }
            if (!nodes.containsKey(to)) {
                nodes.put(to, new Node(to));
            }
            Node fromNode = nodes.get(from);
            Node toNode = nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, weight);
            fromNode.nextNodes.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            this.edges.add(edge);
        }
        return this;
    }


    private static class Node {
        private int value;
        private Set<Node> nextNodes;
        private int out;
        private int in;
        private Set<Edge> edges;

        public Node(int value) {
            this.value = value;
            nextNodes = new HashSet<>();
            edges = new HashSet<>();
        }
    }

    private static class Edge {
        private Node from;
        private Node to;

        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        GeneralGraph graph = new GeneralGraph();
        int[][] matrix = new int[][]{{1, 2, 358}, {2, 3, 106}, {2, 4, 725}, {4, 5, 650}, {1, 6, 780}, {6, 7, 587}, {3, 8, 804}, {1, 9, 252}, {3, 10, 313}, {5, 3, 977}, {9, 9, 132}, {7, 5, 36}, {4, 5, 830}, {4, 1, 713}, {2, 5, 938}, {7, 5, 690}, {4, 3, 896}, {9, 2, 481}, {4, 6, 448}, {4, 7, 888}};

        graph = graph.generateGraph(matrix);

        System.out.println(graph.shortestRoad(graph.nodes.get(1), 10));

        System.out.println(graph.findShortestPath(10, 20, matrix));

    }

    private int shortestRoad(Node node, int n) {
        Node target = this.nodes.get(n);
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        int road = 0;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node nextNode : cur.nextNodes) {
                if (!set.contains(nextNode)) {
                    for (Edge edge : cur.edges) {
                        if (edge.to == nextNode) {
                            road += edge.weight;
                            break;
                        }
                    }
                }
                if (nextNode == target) {
                    min = Math.min(road, min);
                    road = 0;
                    break;
                }
                if (!set.contains(nextNode)) {
                    stack.push(cur);
                    stack.push(nextNode);
                    set.add(nextNode);
                    break;
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int findShortestPath(int n, int m, int[][] graph) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i <= n; i++) {//遍历所有节点
            for (int j = 0; j < m; j++) {
                if (graph[j][0] == i) {
                    int k = graph[j][1];
                    int path = -1;//记录中间节点为i的1~k的距离
                    if (dp[i] == Integer.MAX_VALUE) {
                        path = Integer.MAX_VALUE;
                    } else {
                        path = dp[i] + graph[j][2];//1~i的距离+i~k的距离
                    }
                    dp[k] = Math.min(dp[k], path);//更新1~k的距离
                }
            }
        }
        if (dp[n] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[n];
        }
    }
}
