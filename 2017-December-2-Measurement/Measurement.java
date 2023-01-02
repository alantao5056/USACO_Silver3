import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Measurement {
  static StreamTokenizer st;
  static int N;
  static int G;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("measurement.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    String[] line = br.readLine().split(" ");
    N = Integer.parseInt(line[0]);
    G = Integer.parseInt(line[1]);
    
    // solve
    int[][] entries = new int[N][3];
    for (int i = 0; i < N; i++) {
      line = br.readLine().split(" ");

      entries[i][0] = Integer.parseInt(line[0]);
      entries[i][1] = Integer.parseInt(line[1]);
      entries[i][2] = Integer.parseInt(line[2]);
    }

    Arrays.sort(entries, (int[] a, int[] b) -> a[0]-b[0]);

    // for (int i = 0; i < N; i++) {
    //   pw.println(entries[i][0] + " " + entries[i][1] + " " + entries[i][2]);
    // }

    HashMap<Integer, Integer> milk = new HashMap<>();
    TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder());
    tm.put(G, 1000000000);
    int count = 0;
    int maxKey = -1, maxValue = -1;
    for (int i = 0; i < N; i++) {
      int cow = entries[i][1];
      int delta = entries[i][2];

      int before = milk.getOrDefault(cow, G);
      int after = before + delta;

      milk.put(cow, after);

      tm.put(before, tm.get(before) - 1);
      if (tm.get(before) == 0) {
        tm.remove(before);
      }
      tm.put(after, tm.getOrDefault(after, 0) + 1);

      var first = tm.firstEntry();
      if (first.getKey() != maxKey || first.getValue() != maxValue) {
        if (!(first.getValue() == 1 && maxValue == 1 && after == first.getKey() && maxKey == before)) {
          count++;
        }
        maxKey = first.getKey();
        maxValue = first.getValue();
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