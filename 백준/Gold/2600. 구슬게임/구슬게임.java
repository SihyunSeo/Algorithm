import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] b = new int[3];
        for (int i = 0; i < 3; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            int k1 = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());

            if (canAWin(k1, k2, b)) {
                bw.write("A\n");
            } else {
                bw.write("B\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean canAWin(int k1, int k2, int[] b) {
        boolean[][] dp = new boolean[k1 + 1][k2 + 1];

        for (int i = 0; i <= k1; i++) {
            for (int j = 0; j <= k2; j++) {
                for (int take : b) {
                    if (i >= take && !dp[i - take][j]) {
                        dp[i][j] = true;
                    }
                    if (j >= take && !dp[i][j - take]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        return dp[k1][k2];
    }
}
