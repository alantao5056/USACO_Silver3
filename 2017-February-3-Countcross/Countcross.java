import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Countcross {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int R;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("countcross.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    R = nextInt();
    
    Cell[][] grid = new Cell[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] = new Cell();
        if (i == 0) {
          grid[i][j].up = true;
        }
        if (i == N-1) {
          grid[i][j].down = true;
        }
        if (j == 0) {
          grid[i][j].left = true;
        }
        if (j == N-1) {
          grid[i][j].right = true;
        }
      }
    }

    // solve
    for (int i = 0; i < R; i++) {
      int i1 = nextInt()-1;
      int j1 = nextInt()-1;
      int i2 = nextInt()-1;
      int j2 = nextInt()-1;

      if (i2 == i1-1) {
        grid[i1][j1].up = true;
        grid[i2][j2].down = true;
      } else if (i2 == i1+1) {
        grid[i1][j1].down = true;
        grid[i2][j2].up = true;
      } else if (j2 == j1-1) {
        grid[i1][j1].left = true;
        grid[i2][j2].right = true;
      } else if (j2 == j1+1) {
        grid[i1][j1].right = true;
        grid[i2][j2].left = true;
      }
    }

    for (int i = 0; i < K; i++) {
      grid[nextInt()-1][nextInt()-1].hasCow = true;
    }

    // find groups
    int totalCount = (K*(K-1))/2;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j].visited) continue;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i);
        q.add(j);
        grid[i][j].visited = true;

        int cowCount = 0;
        while (!q.isEmpty()) {
          int curI = q.poll();
          int curJ = q.poll();
          Cell curCell = grid[curI][curJ];

          if (curCell.hasCow) cowCount++;

          if (!curCell.up && !grid[curI-1][curJ].visited) {
            q.add(curI-1);
            q.add(curJ);
            grid[curI-1][curJ].visited = true;
          }
          if (!curCell.down && !grid[curI+1][curJ].visited) {
            q.add(curI+1);
            q.add(curJ);
            grid[curI+1][curJ].visited = true;
          }
          if (!curCell.left && !grid[curI][curJ-1].visited) {
            q.add(curI);
            q.add(curJ-1);
            grid[curI][curJ-1].visited = true;
          }
          if (!curCell.right && !grid[curI][curJ+1].visited) {
            q.add(curI);
            q.add(curJ+1);
            grid[curI][curJ+1].visited = true;
          }
        }

        if (cowCount != 0) {
          totalCount -= (cowCount*(cowCount-1))/2;
        }
      }
    }

    pw.println(totalCount);

    br.close();
    pw.close();
  }

  private static class Cell {
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean visited = false;
    boolean hasCow = false;
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