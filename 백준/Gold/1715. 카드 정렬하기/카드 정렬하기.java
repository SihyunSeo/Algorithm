import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static void settings() {
        int answer = 0;
        while (pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            answer += (first + second);
            pq.add(first + second);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        settings();

        br.close();
    }
}
