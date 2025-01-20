import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] truePeople;
    static List<List<Integer>> enterParty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());
        truePeople = new boolean[N + 1];

        for (int i = 0; i < truthCount; i++) {
            truePeople[Integer.parseInt(st.nextToken())] = true;
        }


        enterParty = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());

            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }

            enterParty.add(party);
        }

        boolean[] canLie = new boolean[M];
        boolean changed = true;

        while (changed) {
            changed = false;
            for (int i = 0; i < M; i++) {
                if (!canLie[i]) {
                    for (int person : enterParty.get(i)) {
                        if (truePeople[person]) {
                            canLie[i] = true;
                            changed = true;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < M; i++) {
                if (canLie[i]) {
                    for (int person : enterParty.get(i)) {
                        truePeople[person] = true;
                    }
                }
            }
        }

        int result = 0;
        for (boolean can : canLie) {
            if (!can) {
                result++;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
