import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Space {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("space.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("space.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[][] points = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        points[i][j] = nextInt();
      }
    }

    // horizantal
    int max1 = 0;
    for (int i = 0; i < N; i++) {
      int count1 = 0;
      for (int j = 0; j < N; j+=2) {
        count1 += points[i][j];
      }

      int count2 = 0;
      for (int j = 1; j < N; j+=2) {
        count2 += points[i][j];
      }

      max1 += Math.max(count1, count2);
    }

    // horizantal
    int max2 = 0;
    for (int i = 0; i < N; i++) {
      int count1 = 0;
      for (int j = 0; j < N; j+=2) {
        count1 += points[j][i];
      }

      int count2 = 0;
      for (int j = 1; j < N; j+=2) {
        count2 += points[j][i];
      }

      max2 += Math.max(count1, count2);
    }

    pw.println(Math.max(max1, max2));

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