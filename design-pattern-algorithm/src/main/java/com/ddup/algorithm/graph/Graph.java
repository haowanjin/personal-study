package com.ddup.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author haowanjin
 * @date 2022/12/22 13:58
 * @description 图
 */
public class Graph {
    private List<String> vertexList;//顶点
    private int[][] edges; // 边及权重
    private int numOfEdges;
    private boolean[] visited;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        // A-B A-C B-C B-D B-E
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);

        graph.showGraph();

        graph.dfs();
    }

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        visited = new boolean[n];
        this.numOfEdges = 0;
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        for (int i = 0; i <getNumOfVertex(); i++) {
            if (!visited[i]) {
                dfs(visited, i);
            }
        }
    }

    public void dfs(boolean[] visited, int index) {
        // 访问此结点
        System.out.print(getValueByIndex(index) + "->");
        // 标记当前结点为已访问
        visited[index] = true;
        int w = getNextNeighbor(index);
        while (w != -1) {
            if (!visited[w]) {
                dfs(visited, w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    /**
     * 获取当前结点的下一个点的下标
     *
     * @param index 当前结点所在行下标
     * @return
     */
    public int getNextNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取当前结点下一个结点的下标
     *
     * @param row 当前结点的行下标
     * @param col 当前结点的列下标
     * @return
     */
    public int getNextNeighbor(int row, int col) {
        for (int i = col + 1; i < vertexList.size(); i++) {
            if (edges[row][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
