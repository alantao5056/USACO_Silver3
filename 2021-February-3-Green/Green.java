import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Green {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("green.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("green.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[][] grid = new int[N][N+1];
    for (int i = 0; i < N; i++) {
      for (int j = 1; j <= N; j++) {
        grid[i][j] = nextInt();
      }
    }

    // create >= 100 prefix
    int[][] prefix1 = new int[N][N+1];
    for (int i = 0; i < N; i++) {
      for (int j = 1; j <= N; j++) {
        prefix1[i][j] = prefix1[i][j-1] + (grid[i][j] >= 100 ? 1 : 0);
      }
    }

    // create > 100 prefix
    int[][] prefix2 = new int[N][N+1];
    for (int i = 0; i < N; i++) {
      for (int j = 1; j <= N; j++) {
        prefix2[i][j] = prefix2[i][j-1] + (grid[i][j] > 100 ? 1 : 0);
      }
    }

    long total1 = 0;

    // fix j1 and j2
    for (int j1 = 1; j1 <= N; j1++) {
      for (int j2 = j1; j2 <= N; j2++) {
        // add to total1
        int run = 0;
        for (int i = 0; i < N; i++) {
          if (prefix1[i][j2] - prefix1[i][j1-1] == j2-j1+1) {
            total1 += ++run;
          } else {
            run = 0;
          }
        }
      }
    }

    long total2 = 0;

    // fix j1 and j2
    for (int j1 = 1; j1 <= N; j1++) {
      for (int j2 = j1; j2 <= N; j2++) {
        // add to total1
        int run = 0;
        for (int i = 0; i < N; i++) {
          if (prefix2[i][j2] - prefix2[i][j1-1] == j2-j1+1) {
            total2 += ++run;
          } else {
            run = 0;
          }
        }
      }
    }

    pw.println(total1-total2);

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