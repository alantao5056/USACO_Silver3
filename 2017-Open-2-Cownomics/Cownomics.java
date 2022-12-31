import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cownomics {
  static StreamTokenizer st;
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("cownomics.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    M = nextInt();
    
    // solve
    char[][] spotty = new char[N][M];
    for (int i = 0; i < N; i++) {
      char[] line = nextString().toCharArray();

      for (int j = 0; j < M; j++) {
        spotty[i][j] = line[j];
      }
    }

    char[][] plain = new char[N][M];
    for (int i = 0; i < N; i++) {
      char[] line = nextString().toCharArray();

      for (int j = 0; j < M; j++) {
        plain[i][j] = line[j];
      }
    }

    int count = 0;
    for (int i = 0; i < M; i++) {
      for (int j = i+1; j < M; j++) {
        for (int k = j+1; k < M; k++) {
          Set<String> spottySet = new HashSet<>();

          for (int l = 0; l < N; l++) {
            spottySet.add(""+spotty[l][i] + spotty[l][j] + spotty[l][k]);
          }

          boolean found = true;
          for (int l = 0; l < N; l++) {
            if (spottySet.contains("" + plain[l][i] + plain[l][j] + plain[l][k])) {
              found = false;
              break;
            }
          }

          if (found) {
            count++;
          }
        }
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