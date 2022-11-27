import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Socdist {
  static StreamTokenizer st;
  static int N;
  static int M;
  static long[][] grass;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("socdist.out"));
    N = nextInt();
    M = nextInt();
    
    // solve
    grass = new long[M][2];
    for (int i = 0; i < M; i++) {
      grass[i][0] = nextLong();
      grass[i][1] = nextLong();
    }

    Arrays.sort(grass, (long[] a, long[] b) -> a[0]-b[0] > 0 ? 1 : -1);

    long low = 0;
    long high = (long) Math.pow(10, 18);
    
    while (low < high) { // notice we do not compare element at mid with our target
      long mid = low + (high - low + 1) / 2;
      if (check(mid))
        low = mid;
      else
        high = mid - 1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  private static boolean check(long d) {
    int cowPlaced = 1;
    long lastPos = grass[0][0];
    long nextPos = lastPos + d;
    int grassIdx = 0;
    while (cowPlaced < N && grassIdx < M) {
      if (nextPos <= grass[grassIdx][1]) {
        // can place
        if (nextPos >= grass[grassIdx][0]) {
          lastPos = nextPos;
        } else {
          // place at first
          lastPos = grass[grassIdx][0];
        }
        nextPos = lastPos+d;
        cowPlaced++;
      } else {
        grassIdx++;
      }
    }

    if (cowPlaced == N) return true;
    return false;
  }

  private static long nextLong() throws Exception {
    st.nextToken();
    return (long) st.nval;
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