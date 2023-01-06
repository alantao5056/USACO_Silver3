import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Citystate {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("citystate.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
    String[] arr1 = new String[N];
    String[] arr2 = new String[N];
    for (int i = 0; i < N; i++) {
      String a = nextString().substring(0,2);
      String b = nextString();
      arr1[i] = a;
      arr2[i] = b;

      HashMap<String, Integer> hs = map.getOrDefault(b, new HashMap<>());
      hs.put(a, hs.getOrDefault(a, 0)+1);
      map.put(b, hs);
    }

    int count = 0;
    for (int i = 0; i < N; i++) {
      if (arr1[i].equals(arr2[i])) {
        continue;
      }
      if (map.containsKey(arr1[i])) {
        count += map.get(arr1[i]).getOrDefault(arr2[i], 0);
      }
    }

    pw.println(count/2);

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