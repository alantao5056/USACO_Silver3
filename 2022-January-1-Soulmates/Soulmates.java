import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Soulmates {
  static StreamTokenizer st;
  static int N;
  static long minMoves;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("soulmates.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("soulmates.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    for (int i = 0; i < N; i++) {
      minMoves = Integer.MAX_VALUE;

      long cur = nextLong();
      long target = nextLong();

      recursive(cur, target, 0);

      pw.println(minMoves);
    }

    br.close();
    pw.close();
  }

  private static void recursive(long cur, long target, long moves) {
    if (target == 0) {
      return;
    }
    // divide cur so that it fits target
    while (cur > target) {
      if (cur % 2 != 0) {
        cur++;
        moves++;
      }
      cur /= 2;
      moves++;
    }

    minMoves = Math.min(minMoves, moves + target-cur);

    if (target % 2 != 0) {
      target--;
      moves++;
    }

    target /= 2;
    moves++;

    recursive(cur, target, moves);
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static long nextLong() throws Exception {
    st.nextToken();
    return (long) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}