import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bcount {
  static StreamTokenizer st;
  static int N;
  static int Q;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("bcount.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    Q = nextInt();
    
    // solve
    int[] prefix1 = new int[N+1];
    int[] prefix2 = new int[N+1];
    int[] prefix3 = new int[N+1];

    for (int i = 0; i < N; i++) {
      int cow = nextInt();

      if (cow == 1) {
        prefix1[i+1]++;
      } else if (cow == 2) {
        prefix2[i+1]++;
      } else {
        prefix3[i+1]++;
      }

      prefix1[i+1] += prefix1[i];
      prefix2[i+1] += prefix2[i];
      prefix3[i+1] += prefix3[i];
    }

    for (int i = 0; i < Q; i++) {
      int a = nextInt();
      int b = nextInt();

      pw.print(prefix1[b]-prefix1[a-1]);
      pw.print(" ");
      pw.print(prefix2[b]-prefix2[a-1]);
      pw.print(" ");
      pw.println(prefix3[b]-prefix3[a-1]);
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