import java.io.*;
import java.util.*;

public class Main {
    static int N,M,K;
    static List<Integer>[] graph;
    static int[] degree;
    static int[] build;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        degree = new int[N + 1];
        build = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            degree[v]++;
        }

        boolean isValid = true;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());

            if (op == 1) {
                if (degree[building] > 0) {
                    isValid = false;
                    break;
                }
                build[building]++;
                if (build[building] == 1) {
                    for (int next : graph[building]) {
                        degree[next]--;
                    }
                }
            } else {
                if (build[building] == 0) {
                    isValid = false;
                    break;
                }
                build[building]--;
                if (build[building] == 0) {
                    for (int next : graph[building]) {
                        degree[next]++;
                    }
                }
            }
        }

        bw.write(isValid ? "King-God-Emperor\n" : "Lier!\n");
        bw.flush();
        bw.close();
    }
}
