import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Highcard {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("highcard.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[] elsie = new int[N];
    for (int i = 0; i < N; i++) {
      elsie[i] = nextInt();
    }

    Arrays.sort(elsie);

    int[] bessie = new int[N];
    int idx = 0;
    int idx2 = 0;
    boolean end = false;
    for (int i = 1; i <= N*2; i++) {
      if (end) {
        bessie[idx2] = i;
        idx2++;
        continue;
      }
      if (elsie[idx] < i) {
        idx++;
        if (idx == N) {
          end = true;
        }
        i--;
      } else if (elsie[idx] > i) {
        bessie[idx2] = i;
        idx2++;
      }
    }

    idx = N-1;
    idx2 = N-1;
    int count = 0;
    while (idx >= 0 && idx2 >= 0) {
      if (elsie[idx] < bessie[idx2]) {
        count++;
        idx--;
        idx2--;
      } else  {
        idx--;
      }
    }

    pw.println(count);

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