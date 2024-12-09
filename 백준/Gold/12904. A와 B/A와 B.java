import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String S;
        StringBuilder T;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = new StringBuilder(br.readLine());

        while (T.length() > S.length()) {
            char last = T.charAt(T.length() - 1);
            T = T.deleteCharAt(T.length() - 1);

            if (last == 'B') {
                T.reverse();
            }
        }

        if (T.toString().equals(S)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
