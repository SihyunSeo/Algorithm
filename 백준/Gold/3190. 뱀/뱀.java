import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    // K개의 줄에 사과의 위치 -> (행, 열)
    // 시작 위치 (0, 0) 아님 xx (1, 1)로 취급
    static int L;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 보드의 크기 : N x N
        N = Integer.parseInt(br.readLine());

        // 사과의 개수
        K = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(st.nextToken()) - 1;
            int appleY = Integer.parseInt(st.nextToken()) - 1;
            map[appleX][appleY] = 1;
        }

//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        L = Integer.parseInt(br.readLine());
        Map<Integer, String> directionInfo = new HashMap<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            directionInfo.put(time, dir);
        }

        Queue<Position> snake = new LinkedList<>();
        snake.add(new Position(0, 0));

        int time = 0;
        int direction = 1;
        int x = 0, y = 0;

        while (true) {
            time++;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || contains(snake, nx, ny)) {
                break;
            }

            snake.add(new Position(nx, ny));

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                snake.poll();
            }

            if (directionInfo.containsKey(time)) {
                if (directionInfo.get(time).equals("L")) {
                    direction = (direction + 3) % 4;
                } else if (directionInfo.get(time).equals("D")) {
                    direction = (direction + 1) % 4;
                }
            }

            x = nx;
            y = ny;
        }

        bw.append(Integer.toString(time));
        bw.flush();
        bw.close();
    }

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean contains(Queue<Position> snake, int x, int y) {
        for (Position snakePosition : snake) {
            if (snakePosition.x == x && snakePosition.y == y) {
                return true;
            }
        }
        return false;
    }
}
