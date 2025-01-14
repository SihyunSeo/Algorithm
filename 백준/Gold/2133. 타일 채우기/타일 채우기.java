import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        
        if (N % 2 != 0) {
            bw.append("0");
        } else {
            int[] dp = new int[N + 1];
            dp[0] = 1;
            dp[2] = 3;
            
            for (int i = 4; i <= N; i += 2) {
                dp[i] = dp[i - 2] * 3;
                for (int j = i - 4; j >= 0; j -= 2) {
                    dp[i] += dp[j] * 2;
                }
            }
            bw.append(dp[N] + "\n");
        }

        bw.flush();
        bw.close();
    }
}