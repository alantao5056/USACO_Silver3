import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Paintbarn {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int[][] prefix = new int[1004][1004];

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("paintbarn.out"));
    N = nextInt();
    K = nextInt();
    
    // solve
    for (int i = 0; i < N; i++) {
      int x1 = nextInt()+1;
      int y1 = nextInt()+1;
      int x2 = nextInt()+1;
      int y2 = nextInt()+1;

      prefix[x1][y1]++;
      prefix[x2][y2]++;
      prefix[x1][y2]--;
      prefix[x2][y1]--;
    }

    int count = 0;
    for (int i = 1; i <= 1003; i++) {
      for (int j = 1; j <= 1003; j++) {
        prefix[i][j] += prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
        count += prefix[i][j] == K ? 1 : 0;
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