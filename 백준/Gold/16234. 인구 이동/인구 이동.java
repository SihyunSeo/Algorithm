import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while (true) {
            visited = new boolean[N][N]; 
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> countryList = new ArrayList<>();
                        if (bfs(i, j, countryList)) {
                            moved = true;
                            population(countryList);
                        }
                    }
                }
            }
            if (!moved) {
                break;
            }
            day++;
        }

        bw.append(Integer.toString(day));
        bw.flush();
        bw.close();
    }

    private static boolean bfs(int x, int y, List<int[]> countryList) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        countryList.add(new int[]{x, y});
        int sum = map[x][y];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[curr[0]][curr[1]] - map[nx][ny]);
                    if (diff >= L && diff <= R) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        countryList.add(new int[]{nx, ny});
                        sum += map[nx][ny];
                    }
                }
            }
        }
        
        if(countryList.size() > 1) {
            return true;
        }
        
        return false;
    }

    private static void population(List<int[]> countryList) {
        int populationSum = 0;
        for (int[] country : countryList) {
            populationSum += map[country[0]][country[1]];
        }
        int avgPopulation = populationSum / countryList.size();

        for (int[] country : countryList) {
            map[country[0]][country[1]] = avgPopulation;
        }
    }
}
