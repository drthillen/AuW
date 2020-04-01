package app;

import java.io.File;
import java.util.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("Dining Table/publicTests/test1.in");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }

        // Uncomment this line if you want to read from a file
        // In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int n = In.readInt(); // total number attending
        int m = In.readInt(); // total number of friendships
        int r = In.readInt(); // best friend
        //
        TestCase testCase = new TestCase(n, m);
        //

        // Output using Out.java class
        if (!testCase.solvable) {
            Out.println("no");
        } else {
            int ref = testCase.distances[r];
            int i = 0;
            for (int d : testCase.distances) {
                if (d % 2 == ref % 2) {
                    Out.print("" + i + " ");
                }
                i++;
            }
            Out.println();
        }
    }

}

class TestCase {
    boolean[] visited;
    LinkedList<Integer>[] vertices;
    int[] distances;
    Deque<Integer> q = new LinkedList<>();
    int V = 0;
    //
    boolean solvable = false;

    TestCase(int n, int m) {
        this.V = n;
        distances = new int[n];
        visited = new boolean[n];
        vertices = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int from = In.readInt();
            int to = In.readInt();
            //
            vertices[from].add(to);
            vertices[to].add(from);
        }
        this.solvable = BFS_mod();
    }

    public boolean BFS_mod() {
        //
        for (int i = 0; i < this.V && !visited[i]; i++) {
            q.add(i);
            distances[i] = 0;
            while (!q.isEmpty()) {
                int current = q.poll();
                visited[current] = true;
                for (int v : vertices[current]) {

                    if (!visited[v] && distances[v] == 0) { // cycle
                        distances[v] = distances[current] + 1;
                        q.add(v);
                    } else if (distances[v] % 2 != (distances[current] + 1) % 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}