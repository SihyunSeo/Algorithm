import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;
    static int[] dist = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, -1);
        dist[N] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            int next = current * 2;

            if (next <= MAX && dist[next] == -1) { 
                dist[next] = dist[current]; 
                queue.add(next);
            }

            next = current - 1;
            if (next >= 0 && dist[next] == -1) {
                dist[next] = dist[current] + 1; // 1초 추가
                queue.add(next);
            }

            next = current + 1;
            if (next <= MAX && dist[next] == -1) {
                dist[next] = dist[current] + 1;
                queue.add(next);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(dist[K]));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
