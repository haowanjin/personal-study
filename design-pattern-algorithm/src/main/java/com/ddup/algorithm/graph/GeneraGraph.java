package com.ddup.algorithm.graph;

import java.util.*;

/**
 * @author haowanjin
 * @date 2023/5/17 9:26
 * <p>
 *
 * </p>
 */
public class GeneraGraph {
    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public GeneraGraph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public static class Node {
        public int value;
        public int in;
        public int out;
        public List<Node> nextNodes;
        public List<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nextNodes = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    public static class Edge {
        public Node from;
        public Node to;
        public int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * N*3 维数组
     * 0 from 1 to 2 weight
     *
     * @param matrix
     * @return
     */
    public static GeneraGraph generateGraph(int[][] matrix) {
        GeneraGraph graph = new GeneraGraph();
        for (int[] ints : matrix) {
            int from = ints[0];
            int to = ints[1];
            int weight = ints[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, weight);
            fromNode.nextNodes.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

    public void bfs(Node node) {
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        Set<Node> hasFound = new HashSet<>();
        hasFound.add(node);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            System.out.println(curNode.value);
            for (Node nextNode : curNode.nextNodes) {
                if (!hasFound.contains(nextNode)) {
                    hasFound.add(nextNode);
                    queue.add(nextNode);
                }
            }
        }
    }

    public void dfs(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<>();
        Set<Node> hasFound = new HashSet<>();
        hasFound.add(node);
        stack.push(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            for (Node nextNode : curNode.nextNodes) {
                if (!hasFound.contains(nextNode)) {
                    stack.push(curNode);
                    stack.push(nextNode);
                    hasFound.add(nextNode);
                    System.out.println(nextNode.value);
                    break;
                }
            }
        }
    }

    public int shortestRoad(Node node, int n) {
        if (node == null) return -1;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Node target = nodes.get(n);
        Set<Node> hasFound = new HashSet<>();
        hasFound.add(node);
        int min = Integer.MAX_VALUE;
        int road = 0;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node nextNode : cur.nextNodes) {
                for (Edge edge : cur.edges) {
                    if (edge.from == cur && edge.to == nextNode && nextNode != target) {
                        road += edge.weight;
                        break;
                    }
                }
                if (nextNode == target) {
                    for (Edge edge : cur.edges) {
                        if (edge.to == target) {
                            road += edge.weight;
                            break;
                        }
                    }
                    min = Math.min(road, min);
                    road = 0;
                }
                if (!hasFound.contains(nextNode)) {
                    stack.push(cur);
                    stack.push(nextNode);
                    hasFound.add(nextNode);
                    break;
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 2}, {1, 4, 5}, {2, 3, 3}, {3, 5, 4}, {4, 5, 5}};

       /* matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 5;

        matrix[1][0] = 1;
        matrix[1][1] = 3;
        matrix[1][2] = 3;

        matrix[2][0] = 3;
        matrix[2][1] = 2;
        matrix[2][2] = 4;

        matrix[3][0] = 2;
        matrix[3][1] = 4;
        matrix[3][2] = 6;

        matrix[4][0] = 3;
        matrix[4][1] = 5;
        matrix[4][2] = 7;*/

        GeneraGraph graph = generateGraph(matrix);

        System.out.println("广度优先：");
        graph.bfs(graph.nodes.get(1));
        System.out.println("-----------------------------------------");
        System.out.println("深度优先：");
        graph.dfs(graph.nodes.get(1));

        System.out.println("最短路径");
        System.out.println(graph.shortestRoad(graph.nodes.get(1), 5));
    }

}
