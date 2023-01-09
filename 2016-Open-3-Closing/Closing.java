import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Closing {
  static StreamTokenizer st;
  static int N;
  static int M;
  static List<Integer>[] cows;
  static boolean[] closed;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("closing.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("closing.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    M = nextInt();
    
    // solve
    cows = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      cows[i] = new ArrayList<>();
    }
    
    for (int i = 0; i < M; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;

      cows[a].add(b);
      cows[b].add(a);
    }
    closed = new boolean[N];

    pw.println(checkIfOne(N) ? "YES" : "NO");

    for (int i = 0; i < N-1; i++) {
      closed[nextInt()-1] = true;
      pw.println(checkIfOne(N-i-1) ? "YES" : "NO");
    }

    br.close();
    pw.close();
  }

  private static boolean checkIfOne(int size) {
    boolean[] visited = new boolean[N];
    ArrayDeque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < N; i++) {
      if (!closed[i]) {
        q.add(i);
        visited[i] = true;
        break;
      }
    }
    
    int size_ = 0;
    while (!q.isEmpty()) {
      int cur = q.poll();
      size_++;

      for (int n : cows[cur]) {
        if (!visited[n] && ! closed[n]) {
          q.add(n);
          visited[n] = true;
        }
      }
    }

    return size_ == size;
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