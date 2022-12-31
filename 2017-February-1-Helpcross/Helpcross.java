import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Helpcross {
  static StreamTokenizer st;
  static int C;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("helpcross.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    C = nextInt();
    N = nextInt();

    TreeMap<Integer, Integer> chickens = new TreeMap<>();
    for (int i = 0; i < C; i++) {
      int cur = nextInt();
      if (!chickens.containsKey(cur)) {
        chickens.put(cur, 1);
      } else {
        chickens.put(cur, chickens.get(cur)+1);
      }
    }
    
    // solve
    int[][] cows = new int[N][2];
    for (int i = 0; i < N; i++) {
      cows[i][0] = nextInt();
      cows[i][1] = nextInt();
    }

    Arrays.sort(cows, (int[] a, int[] b) -> a[1]-b[1]);

    int count = 0;
    for (int i = 0; i < N; i++) {
      Integer target = chickens.ceilingKey(cows[i][0]);
      if (target != null && target <= cows[i][1]) {
        if (chickens.get(target) == 1) {
          chickens.remove(target);
        } else {
          chickens.put(target, chickens.get(target)-1);
        }
        count++;
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