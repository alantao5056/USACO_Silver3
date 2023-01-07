import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Moocast {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("moocast.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(nextInt(), nextInt(), nextInt());
    }

    for (int i = 0; i < N; i++) {
      for (int j = i+1; j < N; j++) {
        // check if i and j can communicate
        double distance = distance(cows[i].x, cows[i].y, cows[j].x, cows[j].y);
        if (distance <= cows[i].p) {
          // can communicate
          cows[i].nbs.add(cows[j]);
        }
        if (distance <= cows[j].p) {
          cows[j].nbs.add(cows[i]);
        }
      }
    }

    // find groups
    int maxGroup = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        cows[j].visited = false;
      }

      // dfs
      ArrayDeque<Cow> q = new ArrayDeque<>();
      q.add(cows[i]);
      cows[i].visited = true;
      int curSize = 0;
      while (!q.isEmpty()) {
        Cow curCow = q.poll();
        curSize++;

        for (Cow c : curCow.nbs) {
          if (!c.visited) {
            q.add(c);
            c.visited = true;
          }
        }
      }

      maxGroup = Math.max(maxGroup, curSize);
    }

    pw.println(maxGroup);

    br.close();
    pw.close();
  }

  private static double distance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
  }

  private static class Cow {
    int x;
    int y;
    int p;
    boolean visited = false;
    List<Cow> nbs = new ArrayList<>();
    public Cow(int x, int y, int p) {
      this.x = x;
      this.y = y;
      this.p = p;
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