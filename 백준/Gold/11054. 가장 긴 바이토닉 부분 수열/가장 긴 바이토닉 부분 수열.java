import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(arr[i] + " ");
//        }

        int[] dp_up = new int[N];
        int[] dp_down = new int[N];

        for (int i = 0; i < N; i++) {
            dp_up[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp_up[i] = Math.max(dp_up[i], dp_up[j] + 1);
                }
            }
        }


        for (int i = N - 1; i >= 0; i--) {
            dp_down[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    dp_down[i] = Math.max(dp_down[i], dp_down[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp_up[i] + dp_down[i] - 1);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.append(Integer.toString(max));
        bw.flush();

    }
}
