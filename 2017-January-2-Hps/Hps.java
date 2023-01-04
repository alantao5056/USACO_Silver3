import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Hps {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("hps.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("hps.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[] hPrefix1 = new int[N+1];
    int[] pPrefix1 = new int[N+1];
    int[] sPrefix1 = new int[N+1];
    char[] hps = new char[N];

    for (int i = 0; i < N; i++) {
      char cur = nextString().charAt(0);
      hps[i] = cur;
      if (cur == 'H') {
        hPrefix1[i+1]++;
      } else if (cur == 'P') {
        pPrefix1[i+1]++;
      } else {
        sPrefix1[i+1]++;
      }

      hPrefix1[i+1] += hPrefix1[i];
      pPrefix1[i+1] += pPrefix1[i];
      sPrefix1[i+1] += sPrefix1[i];
    }

    int h = 0;
    int p = 0;
    int s = 0;
    int result = Math.max(hPrefix1[N], Math.max(pPrefix1[N], sPrefix1[N]));
    for (int i = N-1; i >= 0; i--) {
      char cur = hps[i];
      if (cur == 'H') {
        h++;
      } else if (cur == 'P') {
        p++;
      } else {
        s++;
      }

      result = Math.max(result, Math.max(h, Math.max(p, s)) + Math.max(hPrefix1[i], Math.max(pPrefix1[i], sPrefix1[i])));
    }

    pw.println(result);

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