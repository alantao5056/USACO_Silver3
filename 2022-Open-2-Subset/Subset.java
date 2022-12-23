import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Subset {
  static StreamTokenizer st;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("subset.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("subset.out"));
    PrintWriter pw = new PrintWriter(System.out);
    char[] S = nextString().toCharArray();
    char[] T = nextString().toCharArray();
    
    boolean[][] map = new boolean[18][18];
    for (int i = 0; i < 18; i++) {
      for (int j = i+1; j < 18; j++) {
        StringBuilder sb1 = new StringBuilder();
        for (char c : S) {
          if (c-'a' == i || c-'a' == j) {
            sb1.append(c);
          }
        }

        StringBuilder sb2 = new StringBuilder();
        for (char c : T) {
          if (c-'a' == i || c-'a' == j) {
            sb2.append(c);
          }
        }

        map[i][j] = sb1.toString().equals(sb2.toString());
      }
    }

    // solve
    int Q = nextInt();
    for (int i = 0; i < Q; i++) {
      char[] cur = nextString().toCharArray();

      if (cur.length == 1) {
        int count1 = 0;
        for (char c : S) {
          count1 += c == cur[0] ? 1 : 0;
        }

        int count2 = 0;
        for (char c : T) {
          count2 += c == cur[0] ? 1 : 0;
        }
        
        pw.print(count1 == count2 ? 'Y' : 'N');
        continue;
      }

      boolean found = true;
      for (int j = 0; j < cur.length; j++) {
        for (int k = j+1; k < cur.length; k++) {
          if (!map[cur[j]-'a'][cur[k]-'a']) {
            found = false;
            break;
          }
        }
      }

      if (found) {
        pw.print('Y');
      } else {
        pw.print('N');
      }
    }

    pw.println();

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