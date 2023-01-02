import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Maxcross {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int B;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("maxcross.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    B = nextInt();
    
    // solve
    boolean[] broken = new boolean[N];
    int curBroken = 0;
    for (int i = 0; i < B; i++) {
      int idx = nextInt()-1;
      broken[idx] = true;
      if (idx < K) {
        curBroken++;
      }
    }
    int minBroken = curBroken;
    for (int i = K-1; i < N-1; i++) {
      minBroken = Math.min(minBroken, curBroken);

      curBroken += broken[i+1] ? 1 : 0;
      curBroken -= broken[i-K+1] ? 1 : 0;
    }
    minBroken = Math.min(minBroken, curBroken);

    pw.println(minBroken);

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