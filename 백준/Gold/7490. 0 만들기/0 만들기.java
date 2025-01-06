import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            List<String> results = new ArrayList<>();

            solve(1, N, "1", results);
            results.sort(String::compareTo);
            
            for (String s : results) {
                bw.write(s + "\n");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void solve(int current, int n, String me, List<String> results) {
        if (current == n) {
            if (evaluate(me) == 0) {
                results.add(me);
            }
            return;
        }

        int next = current + 1;
        solve(next, n, me + "+" + next, results);
        solve(next, n, me + "-" + next, results);
        solve(next, n, me + " " + next, results);
    }

    public static int evaluate(String me) {
        me = me.replaceAll(" ", "");
        String[] tokens = me.split("(?=[+-])");
        int sum = 0;
        for (String token : tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
