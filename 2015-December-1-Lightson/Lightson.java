import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Lightson {
  static StreamTokenizer st;
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("lightson.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    M = nextInt();
    
    // solve
    Cell[][] grid = new Cell[N+2][N+2];
    for (int i = 0; i <= N+1; i++) {
      for (int j = 0; j <= N+1; j++) {
        grid[i][j] = new Cell();
      }
    }

    for (int i = 0; i < M; i++) {
      int a = nextInt();
      int b = nextInt();
      grid[a][b].canLight.add(nextInt());
      grid[a][b].canLight.add(nextInt());
    }

    grid[1][1].light = true;
    boolean run = true;
    while (run) {
      run = false;
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(1);
      q.add(1);
      grid[1][1].visited = true;

      while (!q.isEmpty()) {
        int i = q.poll();
        int j = q.poll();

        if (!grid[i][j].used) {
          // use it
          run = true;
          for (int k = 0; k < grid[i][j].canLight.size(); k+=2) {
            grid[grid[i][j].canLight.get(k)][grid[i][j].canLight.get(k+1)].light = true;
          }
          grid[i][j].used = true;
        }

        // add nbs
        if (grid[i+1][j].light && !grid[i+1][j].visited) {
          q.add(i+1);
          q.add(j);
          grid[i+1][j].visited = true;
        }
        if (grid[i-1][j].light && !grid[i-1][j].visited) {
          q.add(i-1);
          q.add(j);
          grid[i-1][j].visited = true;
        }
        if (grid[i][j+1].light && !grid[i][j+1].visited) {
          q.add(i);
          q.add(j+1);
          grid[i][j+1].visited = true;
        }
        if (grid[i][j-1].light && !grid[i][j-1].visited) {
          q.add(i);
          q.add(j-1);
          grid[i][j-1].visited = true;
        }
      }

      // reset visited
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          grid[i][j].visited = false;
        }
      }
    }

    int count = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (grid[i][j].light) {
          count++;
        }
      }
    }

    pw.println(count);

    br.close();
    pw.close();
  }

  private static class Cell {
    boolean visited;
    boolean light;
    boolean used;
    List<Integer> canLight = new ArrayList<>();
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