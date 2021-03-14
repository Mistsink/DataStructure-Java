
import java.util.*;

public class Solution {



/*
     * 785. 判断二分图
     * @param graph
     * @return
    private int[][] graph;
    private boolean[] visited;
    private int[] colors;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        visited = new boolean[graph.length];
        colors = new int[graph.length];

        for (int i = 0; i < graph.length; i ++) {
            visited[i] = false;
            colors[i] = -1;
        }

        for (int v = 0; v < graph.length; v ++) {
            if (!visited[v]) if (!dfs(v, 0)) return false;
        }

        return true;
    }
    private boolean dfs (int v, int color) {
        visited[v] = true;
        colors[v] = color;

        for (int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == color) return false;
        }

        return true;
    }




    private int R, C;
    private boolean[][] visited;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

*
     * 695. 岛屿的最大面积


    public int maxAreaOfIsland (int[][] grid) {
        if (grid == null) return 0;

        R = grid.length;
        if (R == 0) return 0;

        C = grid[0].length;
        if (C == 0) return 0;


        visited = new boolean[R][C];
        int res = 0;

        for (int r = 0; r < R; r ++)
            for (int c = 0; c < C; c ++) {
                if (!visited[r][c] && grid[r][c] == 1) res = Math.max(res, dfs(r, c, grid));
            }

        return res;
    }

    private int dfs(int r, int c, int[][] grid) {
        visited[r][c] = true;

        int res = 1;

        for (int d = 0; d < 4; d ++) {
            int nextR = r + dirs[d][0], nextC = c + dirs[d][1];
            if (inArea(nextR, nextC) && !visited[nextR][nextC] && grid[nextR][nextC] == 1)
                res += dfs(nextR, nextC, grid);
        }
        return res;
    }

//    private boolean inArea(int x, int y) {
//        return x >= 0 && x < R && y >= 0 && y < C;
//    }


*
     * 1091. 二进制矩阵中的最短路径


    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = grid[0].length;

        if (grid[0][0] == 1) return -1;
        if (R == 1 && C == 1) return 1;

        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        //  BFS
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);   //  queue只有一维，故将二维降为一维（扁平化）
        visited[0][0] = true;
        dis[0][0] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int r = cur / C, c = cur % C;
            for (int d = 0; d < 8; d ++) {
                int nextR = r + dirs[d][0], nextC = c + dirs[d][1];
                if (inArea(nextR, nextC) && !visited[nextR][nextC] && grid[nextR][nextC] == 0) {
                    queue.add(nextR * C + nextC);
                    visited[nextR][nextC] = true;
                    dis[nextR][nextC] = dis[r][c] + 1;

                    if (nextR == R - 1 && nextC == C - 1) return dis[nextR][nextC];
                }
            }
        }

        return -1;

    }


*
     * 752. 打开转盘锁


    public int openLock(String[] deadends, String target) {
        HashSet<String> dataSet = new HashSet<>();
        for (String str : deadends) dataSet.add(str);

        if (dataSet.contains(target) || dataSet.contains("0000")) return -1;
        if (target.equals("0000")) return 0;

        HashMap<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add("0000");
        visited.put("0000", 0);

        while (!queue.isEmpty()) {
            String cur = queue.remove();

            char[] curArray = cur.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();

            for (int i = 0; i < 4; i ++) {
                char c = curArray[i];

                //  让 i 位数字加一
                curArray[i] = Character.forDigit((curArray[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curArray));

                curArray[i] = c;

                //  让 i 位数字减一
                curArray[i] = Character.forDigit((curArray[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curArray));

                curArray[i] = c;
            }

            for (String next : nexts) {
                if (!dataSet.contains(next) && !visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if (next.equals(target)) {
                        return visited.get(next);
                    }
                }
            }


        }

        return -1;
    }

 */


    /**
     * 773. 滑动谜题
     */
    /**
    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        String initStr = boardToString(board);
        if (initStr.equals("123450")) return 0;

        queue.add(initStr);
        visited.put(initStr, 0);

        while (!queue.isEmpty()) {
            String cur = queue.remove();

            ArrayList<String> nexts = getNexts(cur);

            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if (next.equals("123450")) return visited.get(next);
                }
            }
        }

        return -1;
    }

    private ArrayList<String> getNexts(String str) {
        int[][] cur = stringToBoard(str);
        int zero = 0;
        while (zero < 6 && cur[zero / 3][zero % 3] != 0) zero ++;
        int zX = zero / 3, zY = zero % 3;

        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        ArrayList<String> res = new ArrayList<>();
        for (int d = 0; d < 4; d ++) {
            int nextX = zX + dirs[d][0], nextY = zY + dirs[d][1];
            if (inArea(nextX, nextY)) {
                swap(cur, zX, zY, nextX, nextY);
                res.add(boardToString(cur));
                swap(cur, zX, zY, nextX, nextY);
            }
        }
        return res;
    }
    private void swap (int[][]board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < 2 && y >=0 && y < 3;
    }
    private String boardToString (int[][] board) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < 2; i ++)
            for (int j = 0; j < 3; j ++) ret.append(board[i][j]);

        return ret.toString();
    }
    private int[][] stringToBoard(String str) {
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i ++) {
            board[i / 3][i % 3] = str.charAt(i) - '0';
        }

        return board;
    }
    */


    /**
     * 980. 不同路径 III
     */
    private int[][] grid;
    private boolean[][] visited;
    private int start, end, R, C;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1},{0, -1}};
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        int leftCount = R * C;

        for (int i = 0; i < R; i ++)
            for (int j = 0; j < C; j ++) {
                if (grid[i][j] == 1) {
                    start = C * i + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = C * i + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    leftCount --;
                }
            }

        return dfs(start, leftCount);
    }
    private int dfs(int v, int leftCount) {
        int x = v / C, y = v % C;
        visited[x][y] = true;
        leftCount --;
        if (leftCount == 0 && v == end) {
            visited[x][y] = false;      //  状态回溯
            return 1;
        }

        int res = 0;
        for (int d = 0; d < 4; d ++) {
            int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                res += dfs(nextX * C + nextY, leftCount);
            }
        }

        visited[x][y] = false;      //  状态回溯
        return res;
    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >=0 && y < C;
    }


}
