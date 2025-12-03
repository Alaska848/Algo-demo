package GreedyChapter;
import java.util.*;


public class Main {
    public static void main(String[] args) {

    }

 /* coins change problem (greedy approach) */

    public static ArrayList<Integer> coins(int n , int []deno , int V){
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (V >= deno[i]) {
                V -= deno[i];
                ans.add(deno[i]);
            }
        }
      return ans;
    }

    /* ---------------------------------------- */


 /*    Minimum cost Spanning tree problem   */

    /* explanation for the problem :
    *  In a connected, undirected, weighted graph, a spanning tree is a subgraph that:
       * 1) Connects all vertices
       * 2) Has no cycles
       * 3) Uses exactly (n − 1) edges (n is the number of vertices)
    * A minimum cost spanning tree is
    * the spanning tree with minimum total weight (sum of edge costs)
    */

    /* so , we have two algorithms that solves this problem by different approaches
     but both are greedy approaches
     1) Prim’s Algorithm (Vertex-based Greedy)
     2) Kruskal’s Algorithm (Edge-based Greedy)
     */


    /*   Prim’s Algorithm
    * best case : O(n^2)
    * worst case : O(n^2)
    *  */

    static class Pair {
        int node;
        int distance;
        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static int Prims(int v , ArrayList<ArrayList<ArrayList<Integer>>> adj){
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int[] vis = new int[v];
        pq.add(new Pair(0 , 0));
        int sum = 0;

        while (!pq.isEmpty()){
            int wt = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();

            if(vis[node] == 1) continue;
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int nod = adj.get(node).get(i).get(0);
                int ed  = adj.get(node).get(i).get(1);

                if(vis[nod] == 0)
                    pq.add(new Pair(nod , ed));
            }
        }
        return sum;
    }



    /* Another implementation of Prims by OMAR MOURAD (it outputs the path with its cost) */

    static class Edge{
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + src + " -> " + dest + " : " + weight + ")";
        }
    }
    public static List<Edge> prim(int n, int[][] W) {
        List<Edge> ans = new ArrayList<>();
        int[] nearest = new int[n];
        int[] D = new int[n];

        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            D[i] = W[0][i];
        }
        D[0] = -1;

        for (int i = 1; i < n; i++) {
            int vnear = -1;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (D[j] >= 0 && D[j] < min) {
                    min = D[j];
                    vnear = j;
                }
            }

            ans.add(new Edge(nearest[vnear], vnear, min));

            D[vnear] = -1;

            for (int j = 0; j < n; j++) {
                if (D[j] >= 0 && W[vnear][j] < D[j]) {
                    D[j] = W[vnear][j];
                    nearest[j] = vnear;
                }
            }
        }

        return ans;
    }

    /*------------------------------------------------------*/


    /* Kruskal Algorithm
    * best case : O(nlogn)
    * worst case : O(n^2 logn)
    * */


    static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int findPar(int x) {
            if (parent[x] != x)
                parent[x] = findPar(parent[x]);
            return parent[x];
        }

        public void unionBySize(int u, int v) {
            int pu = findPar(u);
            int pv = findPar(v);

            if (pu == pv) return;

            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pv] = pu;
                rank[pu]++;
            }
        }
    }

    static class Edge1 implements Comparable<Edge1> {
        int src, dest, weight;

        public Edge1(int _src, int _dest, int _wt) {
            this.src = _src;
            this.dest = _dest;
            this.weight = _wt;
        }

        @Override
        public int compareTo(Edge1 other) {
            return this.weight - other.weight;
        }
    }

    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<Edge1> edges = new ArrayList<>();

        // Convert adjacency list to edge list
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int adjNode = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                if (i < adjNode) {
                    edges.add(new Edge1(i, adjNode, wt));
                }

            }
        }

        DisjointSet ds = new DisjointSet(V);

        Collections.sort(edges);

        int mstWt = 0;

        for (Edge1 e : edges) {
            int u = e.src;
            int v = e.dest;
            int wt = e.weight;

            if (ds.findPar(u) != ds.findPar(v)) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }

        return mstWt;
    }

/*---------------------------------------------------*/

   /* Huffman Algorithm*/
   static class Node {
       char symbol;
       int frequency;
       Node left, right;

       public Node(char symbol, int frequency) {
           this.symbol = symbol;
           this.frequency = frequency;
           this.left = null;
           this.right = null;
       }
   }

        public static Map<String, Object> huffmanCoding(String text) {

            Map<Character, Integer> freqMap = new HashMap<>();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                public int compare(Node n1, Node n2) {
                    return n1.frequency - n2.frequency;
                }
            });

            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                pq.add(new Node(entry.getKey(), entry.getValue()));
            }

            while (pq.size() > 1) {
                Node p = pq.poll();
                Node q = pq.poll();
                Node r = new Node('-', p.frequency + q.frequency);
                r.left = p;
                r.right = q;
                pq.add(r);
            }

            Node root = pq.poll();


            Map<Character, String> codes = new HashMap<>();
            generateCodes(root, "", codes);


            String encoded = "";
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                encoded += codes.get(c);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("codes", codes);
            result.put("encoded", encoded);

            return result;
        }
        private static void generateCodes(Node root, String code, Map<Character, String> codes) {
            if (root == null)
                return;

            if (root.left == null && root.right == null) {
                codes.put(root.symbol, code);
            }

            generateCodes(root.left, code + "0", codes);
            generateCodes(root.right, code + "1", codes);

    }

}
