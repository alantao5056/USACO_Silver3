import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Lifeguards {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("lifeguards.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[][] lifeguards = new int[N][3];
    long[] results = new long[N];
    for (int i = 0; i < N; i++) {
      lifeguards[i][0] = nextInt();
      lifeguards[i][1] = nextInt();
      lifeguards[i][2] = i;
      results[i] = lifeguards[i][1] - lifeguards[i][0];
    }
    
    Arrays.sort(lifeguards, (int[] a, int[] b) -> a[0] > b[0] ? 1 : a[0] < b[0] ? -1 : b[1]-a[1]);

    int total = lifeguards[0][1] - lifeguards[0][0];
    int maxEnd = lifeguards[0][1];
    for (int i = 1; i < N; i++) {
      results[lifeguards[i][2]] -= Math.max(0, maxEnd - lifeguards[i][0]);

      if (lifeguards[i][0] < maxEnd) {
        total += Math.max(0, lifeguards[i][1] - maxEnd);
        maxEnd = Math.max(maxEnd, lifeguards[i][1]);
      } else {
        total += lifeguards[i][1] - lifeguards[i][0];
        maxEnd = lifeguards[i][1];
      }
    }

    Arrays.sort(lifeguards, (int[] a, int[] b) -> a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : a[0]-b[0]);

    int minEnd = lifeguards[0][0];
    for (int i = 1; i < N; i++) {
      results[lifeguards[i][2]] -= Math.max(0, lifeguards[i][1] - minEnd);
      minEnd = Math.min(minEnd, lifeguards[i][0]);
    }

    long minResult = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      minResult = Math.min(minResult, results[i]);
    }

    pw.println(total - Math.max(0, minResult));

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