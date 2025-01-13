import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int babyShark = 2;
    static int sharkEat = 0;
    static int totalTime = 0;
    static int sharkX, sharkY;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Position target = bfs();

            if (target == null) {
                break;
            }

            moveToPosition(target);
        }

        bw.append(Integer.toString(totalTime));
        bw.flush();
        bw.close();
    }

    static Position bfs() {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        visited = new boolean[N][N];
        
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(sharkX, sharkY, 0));
        
        visited[sharkX][sharkY] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] <= babyShark) {
                        visited[nx][ny] = true;
                        queue.add(new Position(nx, ny, current.distance + 1));

                        if (map[nx][ny] > 0 && map[nx][ny] < babyShark) {
                            pq.add(new Position(nx, ny, current.distance + 1));
                        }
                    }
                }
            }
        }

        if(pq.isEmpty()) {
            return null;
        } else {
            return pq.poll();
        }
    }

    static void moveToPosition(Position target) {
        sharkX = target.x;
        sharkY = target.y;
        totalTime += target.distance;

        map[sharkX][sharkY] = 0;
        sharkEat++;

        if (sharkEat == babyShark) {
            babyShark++;
            sharkEat = 0;
        }

//        System.out.println("상어 위치: (" + sharkX + ", " + sharkY + ") - 현재 크기: " + babyShark);
//        System.out.println("총 시간: " + totalTime);
    }

    static class Position implements Comparable<Position> {
        int x, y, distance;

        Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Position other) {
            return comparePosition(this, other);
        }

        private static int comparePosition(Position p1, Position p2) {
            if (p1.distance != p2.distance) {
                return Integer.compare(p1.distance, p2.distance);
            } else if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            } else {
                return Integer.compare(p1.y, p2.y);
            }
        }
    }
}
