import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, newMap;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        while (true) {
            visited = new boolean[N][M];
            int iceCnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        dfs(i, j);
                        iceCnt++;
                    }
                }
            }

            if (iceCnt >= 2) {
                bw.append(Integer.toString(year));
                break;
            }

            if (iceCnt == 0) {
                bw.append(Integer.toString(0));
                break;
            }

            melt();
            year++;
        }

        bw.flush();
        bw.close();
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (map[nx][ny] > 0 && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }

    static void melt() {
        newMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int seaCnt = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                            seaCnt++;
                        }
                    }

                    newMap[i][j] = Math.max(0, map[i][j] - seaCnt);
                } else {
                    newMap[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = newMap[i][j];
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (map[i][j] > 0) {
//                    int seaCnt = 0;
//                    for (int d = 0; d < 4; d++) {
//                        int nx = i + dx[d];
//                        int ny = j + dy[d];
//
//                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
//                            seaCnt++;
//                        }
//                    }
//                    map[i][j] = Math.max(0, map[i][j] - seaCnt);
//                }
//            }
//        }
    }

}

