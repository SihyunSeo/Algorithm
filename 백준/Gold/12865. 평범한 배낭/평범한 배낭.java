import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
    static int n,k;
    static int [][] arr,dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
 
        arr = new int[n+1][2];
        dp = new int[n+1][k+1];
 
        for(int i=1; i<=n; i++){
            String[] s1 = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(s1[0]);
            arr[i][1] = Integer.parseInt(s1[1]);
        }
 
        Arrays.sort(arr, (a,b) ->{
            if(a[0]<b[0]){
                return a[0]-b[0];
            }
            else{
                return a[0]-b[0];
            }
        });
 
        for(int i=1; i<=n; i++){
            for(int j=1; j<=k; j++){
                dp[i][j] = dp[i-1][j];
                if(j-arr[i][0]>=0){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-arr[i][0]]+arr[i][1]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}