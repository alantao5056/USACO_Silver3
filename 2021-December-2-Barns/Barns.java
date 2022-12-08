import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Barns {
  static StreamTokenizer st;
  static int T;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("barns.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("barns.out"));
    PrintWriter pw = new PrintWriter(System.out);
    T = nextInt();
    
    // solve
    for (int i = 0; i < T; i++) {
      int N = nextInt();
      int M = nextInt();

      List<Integer>[] to = new ArrayList[N];

      for (int j = 0; j < N; j++) {
        to[j] = new ArrayList<>();
      }

      for (int j = 0; j < M; j++) {
        int a = nextInt()-1;
        int b = nextInt()-1;

        to[a].add(b);
        to[b].add(a);
      }

      boolean[] visited = new boolean[N];

      // do start group
      ArrayDeque<Integer> q = new ArrayDeque<>();
      List<Integer> start = new ArrayList<>();
      q.add(0);
      visited[0] = true;

      while (!q.isEmpty()) {
        int cur = q.poll();
        start.add(cur);

        for (int n : to[cur]) {
          if (!visited[n]) {
            q.add(n);
            visited[n] = true;
          }
        }
      }

      Collections.sort(start);

      if (visited[N-1]) {
        // in same group
        pw.println(0);
        continue;
      }

      // do end group
      q = new ArrayDeque<>();
      List<Integer> end = new ArrayList<>();
      q.add(N-1);
      visited[N-1] = true;

      while (!q.isEmpty()) {
        int cur = q.poll();
        end.add(cur);

        for (int n : to[cur]) {
          if (!visited[n]) {
            q.add(n);
            visited[n] = true;
          }
        }
      }

      Collections.sort(end);

      long minCost = Long.MAX_VALUE;

      // direct connect
      for (int e : end) {
        int result = Collections.binarySearch(start, e);
        if (result < 0) {
          // rounded down
          result *= -1;
          result -= 2;
        }

        int startDelta1 = result != -1 ? e-start.get(result) : Integer.MAX_VALUE;
        int startDelta2 = result + 1 != start.size() ? start.get(result+1)-e : Integer.MAX_VALUE;
        minCost = Math.min(minCost, (long) Math.pow(Math.min(startDelta1, startDelta2), 2));
      }


      // do middle
      for (int j = 1; j < N-1; j++) {
        if (visited[j]) continue;

        q = new ArrayDeque<>();
        q.add(j);
        visited[j] = true;

        int minStart = Integer.MAX_VALUE;
        int minEnd = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
          int cur = q.poll();
          
          // cur is fixed
          int startResult = Collections.binarySearch(start, cur);
          if (startResult < 0) {
            // rounded down
            startResult *= -1;
            startResult -= 2;
          }

          int startDelta1 = startResult != -1 ? cur-start.get(startResult) : Integer.MAX_VALUE;
          int startDelta2 = startResult + 1 != start.size() ? start.get(startResult+1)-cur : Integer.MAX_VALUE;
          minStart = Math.min(minStart, Math.min(startDelta1, startDelta2));

          int endResult = Collections.binarySearch(end, cur);
          if (endResult < 0) {
            // rounded down
            endResult *= -1;
            endResult -= 2;
          }

          int endDelta1 = endResult != -1 ? cur-end.get(endResult) : Integer.MAX_VALUE;
          int endDelta2 = endResult + 1 != end.size() ? end.get(endResult+1)-cur : Integer.MAX_VALUE;
          minEnd = Math.min(minEnd, Math.min(endDelta1, endDelta2));

          for (int n : to[cur]) {
            if (!visited[n]) {
              q.add(n);
              visited[n] = true;
            }
          }
        }

        minCost = (long) Math.min(minCost, Math.pow(minStart, 2) + Math.pow(minEnd, 2));
      }

      pw.println(minCost);
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