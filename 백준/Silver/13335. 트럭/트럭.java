import java.io.*;
import java.util.*;

public class Main {
    static Queue<Integer> truckList = new LinkedList<>();
    static Queue<Integer> bridge = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferdReader 오타 수정
        
        StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer 변수명 수정
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            truckList.add(Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }
        
        int result = 0;
        int weight = 0;
        
        while (!bridge.isEmpty()) {
            result += 1;
            weight -= bridge.poll();
            
            if (!truckList.isEmpty()) {
                if (truckList.peek() + weight <= L) {
                    weight += truckList.peek();
                    bridge.offer(truckList.poll());
                } else {
                    bridge.add(0);
                }
            }
        }
        
        System.out.println(result);
    }
}
