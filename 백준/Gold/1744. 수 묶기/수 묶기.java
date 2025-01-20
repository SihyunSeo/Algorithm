import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int zeroCnt = 0;
        int oneCnt = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                oneCnt++;
            } else if (num == 0) {
                zeroCnt++;
            } else {
                minus.add(num);
            }
        }

        Collections.sort(plus, Collections.reverseOrder());
        int maxSum = 0;

        for (int i = 0; i < plus.size(); i++) {
            if (i + 1 < plus.size() && plus.get(i) > 1 && plus.get(i + 1) > 1) {
                maxSum += plus.get(i) * plus.get(i + 1);
                i++;
            } else {
                maxSum += plus.get(i);
            }
        }

        maxSum += oneCnt;

        Collections.sort(minus);
        for (int i = 0; i < minus.size(); i++) {
            if (i + 1 < minus.size()) {
                maxSum += minus.get(i) * minus.get(i + 1);
                i++;
            } else {
                if (zeroCnt == 0) {
                    maxSum += minus.get(i);
                }
            }
        }

        bw.write(maxSum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
