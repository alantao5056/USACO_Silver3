import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Pairup {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("pairup.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[][] cows = new int[N][2];
    for (int i = 0; i < N; i++) {
      cows[i][0] = nextInt();
      cows[i][1] = nextInt();
    }

    Arrays.sort(cows, (int[] a, int[] b) -> a[1]-b[1]);

    int left = 0;
    int right = N-1;
    int max = 0;
    while (left < right) {
      max = Math.max(max, cows[left][1] + cows[right][1]);
      if (cows[left][0] < cows[right][0]) {
        cows[right][0] -= cows[left][0];
        left++;
      } else if (cows[left][0] > cows[right][0]) {
        cows[left][0] -= cows[right][0];
        right--;
      } else {
        left++;
        right--;
      }
    }

    if (left == right) {
      max = Math.max(max, cows[left][1] * 2);
    }

    pw.println(max);

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