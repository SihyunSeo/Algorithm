import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int move = 1;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visited[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(move + "\n");
        bw.flush();
    }

    static void dfs(int r, int c, int record_move) {
        move = Math.max(move, record_move);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                if (!visited[board[nr][nc] - 'A']) {
                    visited[board[nr][nc] - 'A'] = true;
                    dfs(nr, nc, record_move + 1);
                    visited[board[nr][nc] - 'A'] = false;
                }
            }
        }
    }
}
