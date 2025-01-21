import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> plus;
    static List<Integer> minus;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        plus = new ArrayList<>();
        minus = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int loc = Integer.parseInt(st.nextToken());
            if(loc < 0) {
                minus.add(-loc);
            } else {
                plus.add(loc);
            }
        }

        Collections.sort(minus, Collections.reverseOrder());
        Collections.sort(plus, Collections.reverseOrder());

        int maxDist = 0;

        if(!minus.isEmpty() && !plus.isEmpty()) {
            maxDist = Math.max(minus.get(0), plus.get(0));
        } else if(!minus.isEmpty()) {
            maxDist = minus.get(0);
        } else if(!plus.isEmpty()) {
            maxDist = plus.get(0);
        }
        
        int result = 0;
        
        for(int i = 0; i < minus.size(); i+=M) {
            result += minus.get(i) * 2;
        }
        
        for(int i = 0; i < plus.size(); i+=M) {
            result += plus.get(i) * 2;
        }
        
        result -= maxDist;
        
        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}