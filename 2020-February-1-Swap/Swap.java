import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Swap {
  static StreamTokenizer st;
  static int N;
  static int M;
  static int K;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("swap.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("swap.out"));
    N = nextInt();
    M = nextInt();
    K = nextInt();
    
    // solve
    int[] cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = i;
    }

    for (int i = 0; i < M; i++) {
      int start = nextInt()-1;
      int end = nextInt()-1;

      // swap
      while (start < end) {
        int temp = cows[start];
        cows[start] = cows[end];
        cows[end] = temp;
        start++;
        end--;
      }
    }
    int[] goesTo = new int[N];
    for (int i = 0; i< N; i++) {
      goesTo[cows[i]] = i;
    }

    // cows[i] --> i
    int[] result = new int[N];
    boolean[] visited = new boolean[N];
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;

      List<Integer> group = new ArrayList<>();
      int cur = i;
      while (!visited[cur]) {
        group.add(cur);
        visited[cur] = true;
        cur = goesTo[cur];
      }

      int offset = K % group.size();
      for (int j = 0; j < group.size(); j++) {
        result[group.get((j+offset) % group.size())] = group.get(j);
      }
    }

    for (int i = 0; i < N; i++) {
      pw.println(result[i]+1);
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