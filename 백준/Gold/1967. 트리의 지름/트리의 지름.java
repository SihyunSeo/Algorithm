import java.io.*;
import java.util.*;

public class Main {
    static List<List<int[]>> nodeList;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(start).add(new int[]{end, weight});
            nodeList.get(end).add(new int[]{start, weight});
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(farNode, 0);

        bw.append(String.valueOf(maxDistance));
        bw.flush();
        bw.close();
    }

    public static void dfs(int currentNode, int currentDistance) {
        visited[currentNode] = true;

        if (currentDistance > maxDistance) {
            maxDistance = currentDistance;
            farNode = currentNode;
        }

        for (int[] neighbor : nodeList.get(currentNode)) {
            int nextNode = neighbor[0];
            int weight = neighbor[1];
            if (!visited[nextNode]) {
                dfs(nextNode, currentDistance + weight);
            }
        }
    }
}
