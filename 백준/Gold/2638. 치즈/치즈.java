import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cnt++;
            }
        }

        int time = 0;
        while (cnt > 0) {
            outsideAir();
            cnt -= melt();
            time++;
        }

        bw.write(time + "\n");
        bw.flush();
        bw.close();
    }

    static void outsideAir() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        map[0][0] = -1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] <= 0) {
                    visited[nx][ny] = true;
                    map[nx][ny] = -1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static int melt() {
        List<int[]> meltList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int airCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];

                        if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == -1) {
                            airCount++;
                        }
                    }
                    if (airCount >= 2) {
                        meltList.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int[] pos : meltList) {
            map[pos[0]][pos[1]] = 0;
        }

        return meltList.size();
    }
}
