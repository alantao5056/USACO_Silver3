import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Wormsort {
  static StreamTokenizer st;
  static int N;
  static int M;
  static int[] cows;
  static int[][] wormholes;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("wormsort.out"));
    N = nextInt();
    M = nextInt();
    
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt()-1;
    }
    
    wormholes = new int[M][3]; // 0: a, 1: b, 2: size
    for (int i = 0; i < M; i++) {
      wormholes[i][0] = nextInt()-1;
      wormholes[i][1] = nextInt()-1;
      wormholes[i][2] = nextInt();
    }
    
    // solve
    // binary search answer
    int low = 0;
    int high = 1000000001;
    
    while (low < high) { // notice we do not compare element at mid with our target
      int mid = low + (high - low + 1) / 2;
      if (!check(mid))
        high = mid-1;
      else
        low = mid;
    }

    pw.println(check(1000000001) ? -1 : low);

    br.close();
    pw.close();
  }

  private static boolean check(int minSize) {
    List<Integer>[] nbs = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      nbs[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      if (wormholes[i][2] >= minSize) {
        nbs[wormholes[i][0]].add(wormholes[i][1]);
        nbs[wormholes[i][1]].add(wormholes[i][0]);
      }
    }

    boolean[] visited = new boolean[N];
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;

      // new group
      Set<Integer> group = new HashSet<>();
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);
      visited[i] = true;
      while (!q.isEmpty()) {
        int cur = q.poll();
        group.add(cur);
        for (int n : nbs[cur]) {
          if (!visited[n]) {
            q.add(n);
            visited[n] = true;
          }
        }
      }

      for (int g : group) {
        if (!group.contains(cows[g])) return false;
      }
    }

    return true;
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}