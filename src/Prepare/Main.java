package Prepare;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int INF = (int) 1e9;

        int[][] dp = {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        };
        int[][] p = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

//        floyd2(dp , p , 4);
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if(dp[i][j] == INF)
//                    System.out.print("INF ");
//                else
//                    System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println("--------------");
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if(p[i][j] == INF)
//                    System.out.print("INF ");
//                else
//                    System.out.print(p[i][j] + " ");
//            }
//            System.out.println();
//        }

        int n = 4;
        System.out.println(S(n));

    }


    public static int binomial(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            // Iterate up to min(i, k) because C(n, k) is 0 if k > n
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }


    public static void floyd1(int[][] dp, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    public static void floyd2(int[][] d, int[][] p, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        p[i][j] = k + 1; // 1- indexed
                    }
                }
            }
        }
    }


    /* generate pascal's triangle by given n
     such n is the number of the rows */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
        List<Integer> first = new ArrayList<>();

        int[][] dp = new int[numRows][numRows + 1];
        dp[0][1] = 1;
        first.add(dp[0][1]);
        pascal.add(first);

        for (int i = 1; i < numRows; i++) {
            List<Integer> in = new ArrayList<>();
            for (int j = 1; j <= i + 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                in.add(dp[i][j]);
            }
            pascal.add(in);
        }
        return pascal;

    }


    /*You are given an array prices
    where prices[i] is the price of a given stock on the ith day.
    You want to maximize your profit
    by choosing a single day to buy one stock and choosing
    a different day in the future to sell that stock.
    Return the maximum profit you can achieve from this transaction.
    If you cannot achieve any profit, return 0.

    example : prices [7, 1 , 5 , 3 , 6 , 4]
    output : 5  (6 - 1)
     */
    public int maxProfit(int[] prices) {
        int dp = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int n = prices[i] - dp;
            max = Math.max(n, max);
            dp = Math.min(dp, prices[i]);
        }
        return max;
    }


    /* You are climbing a staircase.
    It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps.
    In how many distinct ways can you climb to the top? */
    public int climbStairs(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /* Longest increasing subsequence */

    public static int LIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /* divisor game
    * Alice and Bob take turns playing a game, with Alice starting first.
     Initially, there is a number n on the chalkboard.
     * On each player's turn, that player makes a move consisting of:
     Choosing any integer x with 0 < x < n and n % x == 0.
     Replacing the number n on the chalkboard with n - x.
     Also, if a player cannot make a move, they lose the game.
     Return true if and only if Alice wins the game,
     *  assuming both players play optimally.
     */

    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /* count 1s */

    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i / 2] + i % 2;
        }
        return dp;
    }

    /*  */

    public static int S(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] + 2 * dp[n - 2] + 3 * dp[i - 3];
        }
        return dp[n - 1];
    }


    public static int optimalBST(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];

        // حساب مجموع الاحتمالات
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + p[i];
        }

        // طول الشجرة
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                int total = sum[j + 1] - sum[i]; // sum[i..j]
                for (int r = i; r <= j; r++) {
                    int cost = total;
                    if (r > i) cost += dp[i][r - 1];
                    if (r < j) cost += dp[r + 1][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1; // عدد المصفوفات
        int[][] M = new int[n][n];

        // طول السلسلة من 2 إلى n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                M[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = M[i][k] + M[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    M[i][j] = Math.min(M[i][j], cost);
                }
            }
        }

        return M[0][n - 1]; // أقل عدد ضربات لجميع المصفوفات
    }

    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    public class BSTSearch {

        // دالة البحث في الشجرة
        public static Node search(Node tree, int keyIn) {
            Node p = tree;
            while (p != null) {
                if (p.key == keyIn) {
                    return p; // وجدنا المفتاح
                } else if (keyIn < p.key) {
                    p = p.left; // انتقل لليسار
                } else {
                    p = p.right; // انتقل لليمين
                }
            }
            return null; // المفتاح غير موجود
        }

    }

    public class TSPNoBitmask {

        static int N;
        static int[][] W;       // أوزان الطرق بين المدن
        static int[][] P;       // لتخزين الاختيارات الأمثل
        static Map<String, Integer> D = new HashMap<>(); // DP table

        // دالة مساعدة لإنشاء مفتاح Map لكل مجموعة
        static String key(int city, Set<Integer> remaining) {
            List<Integer> list = new ArrayList<>(remaining);
            Collections.sort(list);
            return city + ":" + list.toString();
        }

        static int tsp(int current, Set<Integer> remaining) {
            if (remaining.isEmpty()) {
                return W[current][0]; // العودة للمدينة 0
            }

            String k = key(current, remaining);
            if (D.containsKey(k)) return D.get(k);

            int minCost = Integer.MAX_VALUE;
            int bestNext = -1;

            for (int next : remaining) {
                Set<Integer> newRemaining = new HashSet<>(remaining);
                newRemaining.remove(next);
                int cost = W[current][next] + tsp(next, newRemaining);
                if (cost < minCost) {
                    minCost = cost;
                    bestNext = next;
                }
            }

            D.put(k, minCost);
            return minCost;
        }

        public static void main(String[] args) {
            W = new int[][]{
                    {0, 10, 15, 20},
                    {10, 0, 35, 25},
                    {15, 35, 0, 30},
                    {20, 25, 30, 0}
            };
            N = W.length;

            Set<Integer> remaining = new HashSet<>();
            for (int i = 1; i < N; i++) remaining.add(i);

            int minCost = tsp(0, remaining);
            System.out.println("Minimum tour cost: " + minCost);
        }
    }

    public class TSP_DP {
        public static double travel(int n, double[][] W, int[][] P) {
            int size = 1 << (n - 1);
            double[][] D = new double[n][size];


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < size; j++) {
                    D[i][j] = Double.MAX_VALUE;
                }
            }

            for (int i = 1; i < n; i++) {
                D[i][0] = W[i][0];
            }
            for (int mask = 1; mask < size; mask++) {
                for (int i = 1; i < n; i++) {
                    if ((mask & (1 << (i - 1))) == 0) {
                        for (int j = 1; j < n; j++) {
                            if ((mask & (1 << (j - 1))) != 0) {
                                int prevMask = mask ^ (1 << (j - 1));
                                double cost = W[i][j] + D[j][prevMask];
                                if (cost < D[i][mask]) {
                                    D[i][mask] = cost;
                                    P[i][mask] = j;
                                }
                            }
                        }
                    }
                }
            }

            double minlength = Double.MAX_VALUE;
            int fullMask = size - 1;
            for (int j = 1; j < n; j++) {
                double cost = W[0][j] + D[j][fullMask ^ (1 << (j - 1))];
                if (cost < minlength) {
                    minlength = cost;
                    P[0][fullMask] = j;
                }
            }

            return minlength;
        }

        public static void main(String[] args) {
            double[][] W = {
                    {0, 10, 15, 20},
                    {10, 0, 35, 25},
                    {15, 35, 0, 30},
                    {20, 25, 30, 0}
            };
            int n = W.length;
            int[][] P = new int[n][1 << (n - 1)];

            double minCost = travel(n, W, P);
            System.out.println("Minimum cost: " + minCost);
        }
    }

}