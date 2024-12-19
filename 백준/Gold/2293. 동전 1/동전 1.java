import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[k + 1];
        dp[0] = 1;

        for (int coin : arr) {
            for (int j = coin; j <= k; j++) {
                dp[j] += dp[j-coin];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.append(Integer.toString(dp[k]));
        bw.flush();
    }
}
