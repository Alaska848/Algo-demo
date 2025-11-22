package Prepare;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int INF = (int)1e9;

        int[][] dp = {
                {0,   3,   INF, 7},
                {8,   0,   2,   INF},
                {5,   INF, 0,   1},
                {2,   INF, INF, 0}
        };
        int[][] p = {
                {0,   0,   0,   0},
                {0,   0,   0,   0},
                {0,   0,   0,   0},
                {0,   0,   0,   0}
        };

        floyd2(dp , p , 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(dp[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("--------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(p[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }

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


    public static void floyd1(int [][] dp , int n){
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                   dp[i][j] = Math.min(dp[i][j] , dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    public static void floyd2(int [][] d , int [][] p , int n){
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(d[i][j] > d[i][k] + d[k][j]){
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
           List<List <Integer>> pascal = new ArrayList<>();
           List<Integer> first = new ArrayList<>();

           int [][] dp = new int[numRows][numRows + 1];
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
            dp = Math.min(dp , prices[i]);
        }
        return max;
    }


    /* You are climbing a staircase.
    It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps.
    In how many distinct ways can you climb to the top? */
    public int climbStairs(int n) {
         if(n <= 1) return 1;

         int [] dp = new int[n + 1];
         dp[0] = 1;
         dp[1] = 1;
        for (int i = 2; i <= n; i++) {
           dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /* Longest increasing subsequence */

    public static int LIS(int [] arr){
        int n = arr.length;
        int [] dp = new int [n];

        Arrays.fill(dp , 1);

        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
            max = Math.max(max , dp[i]);
        }
      return max;
    }



}
