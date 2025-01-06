import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean isBinary = true;
            boolean[] visited = new boolean[V + 1];
            int[] depth = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                if (!visited[i]) {
                    if (!dfs(i, visited, depth, 0)) {
                        isBinary = false;
                        break;
                    }
                }
            }

            if (isBinary) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static boolean dfs(int current, boolean[] visited, int[] depth, int d) {
        visited[current] = true;
        depth[current] = d;

        for (int neighbor : graph[current]) {
            if (!visited[neighbor]) {
                if (!dfs(neighbor, visited, depth, d ^ 1)) {
                    return false;
                }
            } else if (depth[current] == depth[neighbor]) {
                return false;
            }
        }
        return true;
    }
}
