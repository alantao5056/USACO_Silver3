import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Frisbee {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("frisbee.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("frisbee.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    long total = 0;
    Cow[] cows = new Cow[N];
    TreeSet<Cow> ts = new TreeSet<>(new cowComparator());
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i, nextInt());

      while (!ts.isEmpty() && ts.first().value < cows[i].value) {
        total += i - ts.first().idx + 1;
        ts.remove(ts.first());
      }

      ts.add(cows[i]);
    }

    ts = new TreeSet<>(new cowComparator());
    for (int i = N-1; i >= 0; i--) {
      while (!ts.isEmpty() && ts.first().value < cows[i].value) {
        total += ts.first().idx - i + 1;
        ts.remove(ts.first());
      }

      ts.add(cows[i]);
    }

    pw.println(total);

    br.close();
    pw.close();
  }

  private static class Cow {
    int idx;
    int value;

    public Cow(int idx, int value) {
      this.idx = idx;
      this.value = value;
    }
  }

  private static class cowComparator implements Comparator<Cow> {
    @Override public int compare(Cow a, Cow b) {
      return a.value - b.value;
    }
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