import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point[] points;
    static boolean[] visited;
    static int n;

    static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            if(current == n+1) {
                return true;
            }

            for(int i = 0; i < n+2; i++) {
                if(!visited[i] && walkable(points[current], points[i])) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return false;
    }
    
    static boolean walkable(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) <= 1000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            points = new Point[n+2];
            visited = new boolean[n+2];

            for(int j = 0; j < n+2; j++) {
                String[] input = br.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                points[j] = new Point(x,y);
            }

            if(bfs()) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        System.out.println(sb);
    }
}
