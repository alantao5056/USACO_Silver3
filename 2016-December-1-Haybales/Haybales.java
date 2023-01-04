import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Haybales {
  static StreamTokenizer st;
  static int N;
  static int Q;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("haybales.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    Q = nextInt();

    int[] haybales = new int[N];
    // solve
    for (int i = 0; i < N; i++) {
      haybales[i] = nextInt();
    }
    Arrays.sort(haybales);

    for (int i = 0; i < Q; i++) {
      int start = nextInt();
      int end = nextInt();
      int startSearch = Arrays.binarySearch(haybales, start);
      int startIdx = startSearch < 0 ? startSearch * -1 - 1 : startSearch;
      int endSearch = Arrays.binarySearch(haybales, end);
      int endIdx = endSearch < 0 ? endSearch * -1 -2 : endSearch;
      pw.println(endIdx - startIdx+1);
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