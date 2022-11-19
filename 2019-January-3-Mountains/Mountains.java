import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Mountains {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("mountains.out"));
    N = nextInt();
    
    // solve
    int[][] segments = new int[N][2];
    for (int i = 0; i < N; i++) {
      int x = nextInt();
      int y = nextInt();
      segments[i][0] = x-y;
      segments[i][1] = x+y;
    }

    Arrays.sort(segments, (int[] a, int[] b) -> a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : b[1]-a[1]);

    int maxEnd = Integer.MIN_VALUE;
    int count = 0;
    for (int i = 0; i < N; i++) {
      if (segments[i][1] > maxEnd) {
        count++;
      }
      maxEnd = Math.max(maxEnd, segments[i][1]);
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