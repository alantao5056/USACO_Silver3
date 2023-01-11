import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cbarn {
  static StreamTokenizer st;
  static int N;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("cbarn.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    long minSteps = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      if (cows[i] != 0) continue;
      long steps = getSteps(i);
      if (steps != -1) {
        minSteps = Math.min(minSteps, steps);
      }
    }

    pw.println(minSteps);

    br.close();
    pw.close();
  }

  private static long getSteps(int start) {
    int[] arr = copy();
    int red = start;
    int blue = start;
    long total = 0;
    do  {
      if (red == blue && arr[red] == 1) {
        red--;
        if (red == -1) {
          red = N-1;
        }
        blue--;
        if (blue == -1) {
          blue = N-1;
        }
        continue;
      }
      // blue find first room with cow
      while (arr[blue] == 0) {
        blue--;
        if (blue == -1) {
          blue = N-1;
        }
      }

      // give to red
      arr[blue]--;
      total += energy(blue, red);
      arr[red]++;
      red--;
      if (red == -1) {
        red = N-1;
      }
    } while (red != start || (red == blue && arr[red] > 1));

    if (red == start) {
      return total;
    }

    return -1;
  }

  private static long energy(int a, int b) {
    if (a <= b) {
      return (long) Math.pow((b-a), 2);
    }
    // a > b
    return (long) Math.pow((N-a+b), 2);
  }

  private static int[] copy() {
    int[] newArr = new int[N];
    for (int i = 0; i < N; i++) {
      newArr[i] = cows[i];
    }
    return newArr;
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