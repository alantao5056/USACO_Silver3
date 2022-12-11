import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Gifts {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("gifts.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("gifts.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i);
    }

    for (int i = 0; i < N; i++) {
      boolean stop = false;
      for (int j = 0; j < N; j++) {
        int cur = nextInt()-1;
        if (cur == i) {
          stop = true;
        }

        if (!stop) {
          cows[i].nbs.add(cows[cur]);
        }
      }
    }

    for (int i = 0; i < N; i++) {
      boolean found2 = false;
      boolean[] visited = new boolean[N];
      for (int j = 0; j < cows[i].nbs.size(); j++) {
        // dfs
        boolean found = false;
        ArrayDeque<Cow> q = new ArrayDeque<>();
        q.add(cows[i].nbs.get(j));
        visited[cows[i].nbs.get(j).id] = true;

        while (!q.isEmpty()) {
          Cow cur = q.poll();
          if (cur == cows[i]) {
            found = true;
            break;
          }

          for (Cow c : cur.nbs) {
            if (!visited[c.id]) {
              q.add(c);
              visited[c.id] = true;
            }
          }
        }

        if (found) {
          found2 = true;
          pw.println(cows[i].nbs.get(j).id+1);
          break;
        }
      }

      if (!found2) {
        pw.println(i+1);
      }
    }

    br.close();
    pw.close();
  }

  private static class Cow {
    int id;
    List<Cow> nbs = new ArrayList<>();

    public Cow(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return "" + id;
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