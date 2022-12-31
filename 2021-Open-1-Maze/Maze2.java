import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Maze2 {
  static StreamTokenizer st;
  static int N;
  static char[][][] grid;
  static boolean[][][] visited;
  static int[] placeValues = new int[9];
  static int[] i_ = {1, 0, -1, 0};
  static int[] j_ = {0, 1, 0, -1};
  static Set<Integer> possible = new HashSet<>();

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("maze.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("maze.out"));
    // PrintWriter pw = new PrintWriter(System.out);

    placeValues[0] = 1;
    for (int i = 1; i < 9; i++) {
      placeValues[i] = placeValues[i-1] * 3;
    }

    N = Integer.parseInt(br.readLine().strip());
    
    // solve
    grid = new char[N][N][3];

    int bessieI = 0;
    int bessieJ = 0;
    for (int i = 0; i < N; i++) {
      char[] line = br.readLine().strip().toCharArray();
      for (int j = 0; j < N; j++) {
        grid[i][j][0] = line[j*3];
        grid[i][j][1] = line[j*3+1];
        grid[i][j][2] = line[j*3+2];
        if (grid[i][j][0] == 'B') {
          bessieI = i;
          bessieJ = j;
        }
      }
    }

    visited = new boolean[N][N][19684]; // .=0, M=1, O=2
    visited[bessieI][bessieJ][0] = true;

    flood(bessieI, bessieJ, 0);

    // for (int p : possible) {
    //   for (int l = 0; l < 3; l++) {
    //     for (int m = 0; m < 3; m++) {
    //       int cur = (p / placeValues[l*3+m]) % 3;
    //       pw.print(cur==0?'.' : cur == 1 ? 'M' : 'O');
    //       pw.print(" ");
    //     }
    //     pw.println();
    //   }
    //   pw.println();
    // }

    pw.println(possible.size());

    br.close();
    pw.close();
  }

  private static void flood(int i, int j, int curBoard) {
    for (int k = 0; k < 4; k++) {
      int nextI = i+i_[k];
      int nextJ = j+j_[k];
      if (grid[nextI][nextJ][0] != '#') {
        int newBoard = curBoard;
        if (grid[nextI][nextJ][0] != '.' && grid[nextI][nextJ][0] != 'B') {
          int place = (grid[nextI][nextJ][1]-'0'-1) * 3 + grid[nextI][nextJ][2]-'0';
          int value = (curBoard / placeValues[place-1]) % 3;
          if (value == 0) {
            newBoard += grid[nextI][nextJ][0] == 'M' ? placeValues[place-1] : placeValues[place-1] * 2;
          }
        }
        if (!visited[nextI][nextJ][newBoard]) {
          visited[nextI][nextJ][newBoard] = true;

          // check board
          int[][] board = new int[3][3];
          for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
              board[l][m] = (newBoard / placeValues[l*3+m]) % 3;
            }
          }

          if (board[0][0] == 1) {
            if (board[0][1]==2 && board[0][2]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][1]==2 && board[2][2]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][0]==2 && board[2][0]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[0][1] == 1) {
            if (board[1][1]==2 && board[2][1]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[0][2]==1) {
            if (board[0][1]==2 && board[0][0]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][1]==2 && board[2][0]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][2]==2 && board[2][2]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[1][0]==1) {
            if (board[1][1]==2 && board[1][2]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[1][2] == 1) {
            if (board[1][1]==2 && board[1][0]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[2][0] == 1) {
            if (board[1][0]==2 && board[0][0]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][1]==2 && board[0][2]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[2][1]==2 && board[2][2]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[2][1] == 1) {
            if (board[1][1]==2 && board[0][1]==2) {
              possible.add(newBoard);
              continue;
            }
          }
          if (board[2][2] == 1) {
            if (board[1][2]==2 && board[0][2]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[1][1]==2 && board[0][0]==2) {
              possible.add(newBoard);
              continue;
            }
            if (board[2][1]==2 && board[2][0]==2) {
              possible.add(newBoard);
              continue;
            }
          }

          flood(nextI, nextJ, newBoard);
        }
      }
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