package app;

import java.util.*;

class Main {

  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    In.open("public/test1.in");

    int t = In.readInt();
    for (int i = 0; i < t; i++) {
      testCase();
    }
    // Uncomment this line if you want to read from a file
    // In.close();
  }

  public static void testCase() {
    // Input using In.java class

    //
    int n = In.readInt();
    int m = In.readInt();
    //
    LinkedList<Integer>[] vertices = new LinkedList[n];
    for (int j = 0; j < n; j++) {
      // initializing graph
      vertices[j] = new LinkedList<Integer>();
    }
    for (int i = 0; i < m; i++) {
      // filling graph
      int a = In.readInt();
      int b = In.readInt();
      vertices[a].add(b);
      vertices[b].add(a);
    }
    //
    HashSet<Integer> h = DFS(vertices);
    ArrayList<Integer> a = new ArrayList<Integer>();
    a.addAll(h);
    a.sort(null);
    if (a.size() > 0) {
      for (int i = 0; i < a.size() - 1; i++) {
        Out.print(a.get(i) + " ");
      }
      Out.println(a.get(a.size() - 1));
    } else {
      Out.println(-1);
    }
    // Output using Out.java class

  }

  public static HashSet<Integer> DFS(LinkedList<Integer>[] vertices) {
    int V = vertices.length;
    int[] low = new int[V];
    int[] dfs = new int[V];
    int[] pred = new int[V];
    boolean[] visited = new boolean[V];

    int currDFS = 0;
    HashSet<Integer> articV = new HashSet<Integer>();

    for (int u = 0; u < V; u++) {
      if (!visited[u]) {
        currDFS =
          DFSVisit(
            u,
            -1,
            low,
            dfs,
            pred,
            vertices,
            visited,
            currDFS,
            currDFS,
            articV
          ) +
          1;
      }
    }

    return articV;
  }

  public static int DFSVisit(
    int u,
    int pre,
    int[] low,
    int[] dfs,
    int[] pred,
    LinkedList<Integer>[] vertices,
    boolean[] visited,
    int startDFS,
    int currDFS,
    HashSet<Integer> articV
  ) {
    visited[u] = true;
    dfs[u] = currDFS;
    low[u] = currDFS;
    pred[u] = pre;
    //
    for (int v : vertices[u]) {
      if (!visited[v]) {
        if (pre == -1 && currDFS > startDFS) {
          articV.add(u);
        }
        currDFS =
          DFSVisit(
            v,
            u,
            low,
            dfs,
            pred,
            vertices,
            visited,
            startDFS,
            currDFS + 1,
            articV
          );
        low[u] = Math.min(low[u], low[v]);
        if (low[v] >= dfs[u] && pre != -1) {
          articV.add(u);
        }
      } else if (v != pre) {
        low[u] = Math.min(low[u], dfs[v]);
      }
    }
    return currDFS;
  }
}
