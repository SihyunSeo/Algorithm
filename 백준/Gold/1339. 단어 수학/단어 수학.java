import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        weight = new int[26];

        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                weight[c - 'A'] += (int) Math.pow(10, length - 1 - i);
//                System.out.println(weight[c - 'A']);
            }
        }
        Arrays.sort(weight);

        int result = 0;
        int number = 9;
        for (int i = 25; i >= 0; i--) {
            if (weight[i] == 0) break;
            result += weight[i] * number--;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.append(Integer.toString(result));

        bw.flush();
        bw.close();
    }
}
