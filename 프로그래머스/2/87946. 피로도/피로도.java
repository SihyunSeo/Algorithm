class Solution {
    private static boolean[] visited;
    private static int maxCnt = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return maxCnt;
    }

    public static void dfs(int cnt, int k, int[][] dungeons) {
        maxCnt = Math.max(maxCnt, cnt);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(cnt + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
}
