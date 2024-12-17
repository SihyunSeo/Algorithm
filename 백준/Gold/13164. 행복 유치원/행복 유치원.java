import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 유치원 원생 총인원
        N = Integer.parseInt(st.nextToken());

        // 몇개의 조
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] subtract = new int[N-1];
        for(int i = 1; i < N; i++) {
            subtract[i-1] = arr[i] - arr[i-1];
        }

        Arrays.sort(subtract);

        int result = 0;
        for(int i = 0; i < N-K; i++) {
            result += subtract[i];
        }

        System.out.println(result);
    }
}
