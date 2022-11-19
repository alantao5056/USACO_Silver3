import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Perimeter {
  static StreamTokenizer st;
  static int N;
  static boolean[][] grid;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("perimeter.out"));
    N = Integer.parseInt(br.readLine());
    grid = new boolean[N+2][N+2];
    visited = new boolean[N+2][N+2];
    
    for (int i = 1; i <= N; i++) {
      char[] line = br.readLine().toCharArray();
      
      for (int j = 1; j <= N; j++) {
        grid[i][j] = line[j-1] == '#';
      }
    }
    
    // solve
    int maxSize = 0;
    int maxPerimeter = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (!grid[i][j] || visited[i][j]) continue;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i);
        q.add(j);
        visited[i][j] = true;
        int size = 0;
        int perimeter = 0;

        while (!q.isEmpty()) {
          int curI = q.poll();
          int curJ = q.poll();
          size++;

          if (grid[curI+1][curJ] && !visited[curI+1][curJ]) {
            q.add(curI+1);
            q.add(curJ);
            visited[curI+1][curJ] = true;
          } else if (!grid[curI+1][curJ]) {
            perimeter++;
          }

          if (grid[curI][curJ+1] && !visited[curI][curJ+1]) {
            q.add(curI);
            q.add(curJ+1);
            visited[curI][curJ+1] = true;
          } else if (!grid[curI][curJ+1]) {
            perimeter++;
          }

          if (grid[curI-1][curJ] && !visited[curI-1][curJ]) {
            q.add(curI-1);
            q.add(curJ);
            visited[curI-1][curJ] = true;
          } else if (!grid[curI-1][curJ]) {
            perimeter++;
          }

          if (grid[curI][curJ-1] && !visited[curI][curJ-1]) {
            q.add(curI);
            q.add(curJ-1);
            visited[curI][curJ-1] = true;
          } else if (!grid[curI][curJ-1]) {
            perimeter++;
          }
        }

        if (size > maxSize) {
          maxSize = size;
          maxPerimeter = perimeter;
        } else if (size == maxSize) {
          maxPerimeter = Math.min(maxPerimeter, perimeter);
        }
      }
    }

    pw.print(maxSize);
    pw.println(' '+Integer.toString(maxPerimeter));

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