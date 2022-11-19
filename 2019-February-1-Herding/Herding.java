import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Herding {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("herding.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("herding.out"));
    N = nextInt();
    
    // solve
    int[] cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    Arrays.sort(cows);

    boolean special1 = cows[1]-cows[0]-1 > 1;
    boolean special2 = cows[N-1]-cows[N-2]-1 > 1;
    int gapSum = 0;

    for (int i = 0; i < N-1; i++) {
      int gap = cows[i+1]-cows[i]-1;
      gapSum += gap;
      if (i != 0 && gap != 0) {
        special1 = false;
      }

      if (i != N-2 && gap != 0) {
        special2 = false;
      }
    }

    if (special1) {
      pw.println(2);
      pw.println(cows[1] - cows[0]-1);
      br.close();
      pw.close();
      return;
    } else if (special2) {
      pw.println(2);
      pw.println(cows[N-1] - cows[N-2]-1);
      br.close();
      pw.close();
      return;
    }

    // get smallest
    int smallest = Integer.MAX_VALUE;
    int endIdx = 0;

    for (int i = 0; i < N; i++) {
      int end = cows[i] + N-1;
      while (endIdx < N-1 && cows[endIdx+1] <= end) {
        endIdx++;
      }

      smallest = Math.min(smallest, N-(endIdx-i+1));
    }

    pw.println(smallest);

    pw.println(gapSum-Math.min(cows[1] - cows[0]-1, cows[N-1] - cows[N-2]-1));

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