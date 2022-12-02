import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Dance {
  static StreamTokenizer st;
  static int N;
  static int K;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("dance.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    
    // solve
    int[] to = new int[N];
    List<Set<Integer>> canReach = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      to[i] = i;
      canReach.add(new HashSet<>());
      canReach.get(i).add(i);
    }

    for (int i = 0; i < K; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;

      canReach.get(to[a]).add(b);
      canReach.get(to[b]).add(a);

      // swap
      int temp = to[a];
      to[a] = to[b];
      to[b] = temp;
    }

    boolean[] visited = new boolean[N];
    int[] result = new int[N];
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;

      int cur = to[i];
      Set<Integer> total = new HashSet<>();
      List<Integer> group = new ArrayList<>();
      while (!visited[cur]) {
        visited[cur] = true;
        group.add(cur);
        total.addAll(canReach.get(cur));
        cur = to[cur];
      }

      for (int g : group) {
        result[g] = total.size();
      }
    }

    for (int i = 0; i < N; i++) {
      pw.println(result[i]);
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