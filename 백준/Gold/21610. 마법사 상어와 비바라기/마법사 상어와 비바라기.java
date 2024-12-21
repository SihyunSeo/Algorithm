import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static boolean[][] hasCloud;
    static List<int[]> rainCloud;
    static List<int[]> move;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//        }

        rainCloud = new ArrayList<>();
        // (N, 1), (N, 2), (N-1, 1), (N-1, 2)
        // 시작이 0,0이 아닌 1,1로 한다는 가정하에 들어가면 비구름 위치 -1 연산 필요
        rainCloud.add(new int[]{N-1, 0});
        rainCloud.add(new int[]{N-1, 1});
        rainCloud.add(new int[]{N-2, 0});
        rainCloud.add(new int[]{N-2, 1});

        // 이동 좌표 저장
        move = new ArrayList<>();
        for(int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            move.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        // main logic
        for(int i = 0; i < move.size(); i++) {
//            System.out.println(move.get(i)[0] + ", " + move.get(i)[1]);
            moveClouds(move.get(i)[0], move.get(i)[1]);
            rain();
            water();
            removeWater();
        }

        int result = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result += grid[i][j];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.append(Integer.toString(result));
        bw.flush();
    }

    static void moveClouds(int d, int s) {
        hasCloud = new boolean[N][N];
        for (int[] cloud : rainCloud) {
            int x = cloud[0];
            int y = cloud[1];
//            System.out.println("x: " + x);
//            System.out.println("y: " + y);
            int nx = x + dx[d - 1] * s;
            int ny = y + dy[d - 1] * s;

            while (nx < 0) nx += N; // 위쪽으로 넘어간 경우
            while (nx >= N) nx -= N; // 아래쪽으로 넘어간 경우
            while (ny < 0) ny += N; // 왼쪽으로 넘어간 경우
            while (ny >= N) ny -= N; // 오른쪽으로 넘어간 경우

//            System.out.println("nx: " + nx);
//            System.out.println("ny: " + ny);

            cloud[0] = nx;
            cloud[1] = ny;
            hasCloud[nx][ny] = true;
        }

        // 확인
//        for(int i = 0; i < hasCloud.length; i++) {
//            for(int j = 0; j < hasCloud[0].length; j++) {
//                System.out.print(hasCloud[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    static void rain() {
        for (int[] cloud : rainCloud) {
            int x = cloud[0];
            int y = cloud[1];
            grid[x][y]++;
        }
    }

    static void water() {
        for (int[] cloud : rainCloud) {
            int x = cloud[0];
            int y = cloud[1];
            int addWater = 0;

            for (int i = 1; i < dx.length; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && grid[nx][ny] > 0) {
                    addWater++;
                }
            }
            grid[x][y] += addWater;
        }
    }

    static void removeWater() {
        List<int[]> newClouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!hasCloud[i][j] && grid[i][j] >= 2) {
                    newClouds.add(new int[]{i, j});
                    grid[i][j] -= 2;
                }
            }
        }
        rainCloud = newClouds;
    }
}

