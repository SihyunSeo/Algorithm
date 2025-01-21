import java.io.*;
import java.util.*;
//    2
//    4 4
//    10 1 100 10
//    1 2
//    1 3
//    2 4
//    3 4
//    4
//    8 8
//    10 20 1 5 8 7 1 43
//    1 2
//    1 3
//    2 4
//    2 5
//    3 6
//    5 7
//    6 7
//    7 8
//    7
public class Main {
    static int T, N, K, W;
    static int[] D;
    static List<List<Integer>> graph;
    static int[] inDegree;
    static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            D = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                D[j] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList<>();
            for(int k = 0; k <= N; k++) {
                graph.add(new ArrayList<>());
            }

            result = new int[N + 1];
            inDegree = new int[N + 1];
            for(int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                inDegree[y]++;
            }

            W = Integer.parseInt(br.readLine());

            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int solution() {
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            result[i] = D[i];
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int currentX = queue.poll();

            for(int nextX : graph.get(currentX)) {
                result[nextX] = Math.max(result[nextX], result[currentX] + D[nextX]);

                inDegree[nextX] -- ;

                if (inDegree[nextX] == 0) {
                    queue.offer(nextX);
                }
            }
        }

        return result[W];
    }
}
