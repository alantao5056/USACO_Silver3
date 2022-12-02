import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Paint {
  static StreamTokenizer st;
  static int N;
  static int Q;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("paint.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("paint.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    Q = nextInt();
    
    // solve
    char[] f = nextString().toCharArray();
    int[] fence = new int[N];
    int[] lastFound = new int[26];
    for (int i = 0; i < N; i++) {
      fence[i] = f[i] - 'A';
    }

    for (int i = 0; i < 26; i++) {
      lastFound[i] = -1;
    }

    int[] left = new int[N];
    int count = 0;
    for (int i = 0; i < N; i++) {
      int letter = fence[i];
      if (lastFound[letter] != -1) {
        boolean flag = false;
        for (int j = letter-1; j >= 0; j--) {
          if (lastFound[j] > lastFound[letter]) {
            // doesn't work
            flag = true;
            break;
          }
        }

        if (!flag) {
          count--;
        }
      }
      count++;
      lastFound[letter] = i;
      left[i] = count;
    }

    for (int i = 0; i < 26; i++) {
      lastFound[i] = -1;
    }

    int[] right = new int[N];
    count = 0;
    for (int i = N-1; i >= 0; i--) {
      int letter = fence[i];
      if (lastFound[letter] != -1) {
        boolean flag = false;
        for (int j = letter-1; j >= 0; j--) {
          if (lastFound[j] != -1 && lastFound[j] < lastFound[letter]) {
            // doesn't work
            flag = true;
            break;
          }
        }

        if (!flag) {
          count--;
        }
      }
      count++;
      lastFound[letter] = i;
      right[i] = count;
    }

    for (int i = 0; i < Q; i++) {
      int start = nextInt();
      int end = nextInt();
      pw.println((start != 1 ? left[start-2] : 0)+(end != N ? right[end] : 0));
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