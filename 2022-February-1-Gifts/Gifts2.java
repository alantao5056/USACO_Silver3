import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Gifts2 {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("gifts.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("gifts.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    List<Integer>[] gifts = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      boolean added = false;
      gifts[i] = new ArrayList<>();
      for (int j = 0; j < N; j++) {
        int cur = nextInt()-1;
        if (added) continue;
        if (cur == i) {
          added = true;
          continue;
        }
        gifts[i].add(cur);
      }
    }

    boolean[][] connected = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      boolean[] visited = new boolean[N];
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);
      visited[i] = true;
      while (!q.isEmpty()) {
        int cur = q.poll();
        connected[i][cur] = true;

        for (int n : gifts[cur]) {
          if (!visited[n]) {
            q.add(n);
            visited[n] = true;
          }
        }
      }
    }

    for (int i = 0; i < N; i++) {
      boolean found = false;
      for (int g : gifts[i]) {
        if (connected[g][i] && connected[i][g]) {
          pw.println(g+1);
          found = true;
          break;
        }
      }
      if (!found) {
        pw.println(i+1);
      }
    }

    br.close();
    pw.close();
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
