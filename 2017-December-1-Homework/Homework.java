import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Homework {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("homework.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("homework.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[] homeworks = new int[N];

    int total = 0;
    for (int i = 0; i < N; i++) {
      homeworks[i] = nextInt();
      total += homeworks[i];
    }

    int minScore = Integer.MAX_VALUE;
    int[] prefix = new int[N];
    for (int i = N-1; i >= 0; i--) {
      minScore = Math.min(minScore, homeworks[i]);
      prefix[i] = minScore;
    }

    double maxScore = 0;
    List<Integer> results = new ArrayList<>();
    total -= homeworks[0];
    for (int i = 1; i < N-1; i++) {
      double score = (double) (total - prefix[i]) / (N-i-1);
      if (Double.compare(maxScore, score) < 0) {
        // score is greater
        maxScore = score;
        results = new ArrayList<>();
        results.add(i);
      } else if (Double.compare(maxScore, score) == 0) {
        results.add(i);
      }
      total -= homeworks[i];
    }

    for (int r : results) {
      pw.println(r);
    }

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