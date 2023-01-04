import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cowdance {
  static StreamTokenizer st;
  static int N;
  static int T;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("cowdance.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    T = nextInt();
    
    // solve
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    // binary search K
    int low = 0;
    int high = N;
    
    while (low < high) { // notice we do not compare element at mid with our target
      int mid = low + (high - low) / 2;
      if (works(mid))
        high = mid;
      else
        low = mid + 1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  private static boolean works(int K) {
    int[] dance = new int[K];
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < K; i++) {
      dance[i] = cows[i];
      min = Math.min(min, cows[i]);
    }

    int idx = K;
    int time = 0;
    while (time <= T) {
      time += min;
      int newMin = Integer.MAX_VALUE;
      boolean done = false;
      for (int i = 0; i < K; i++) {
        dance[i] -= min;
        if (dance[i] == 0) {
          if (idx < N) {
            dance[i] = cows[idx];
            idx++;
          } else {
            done = true;
          }
        }

        newMin = Math.min(newMin, dance[i]);
      }
      min = newMin;
      if (done) break;
    }

    if (time > T) return false;

    int max = 0;
    for (int i = 0; i < K; i++) {
      max = Math.max(max, dance[i]);
    }

    return time + max <= T;

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