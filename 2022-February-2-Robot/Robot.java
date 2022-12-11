import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Robot {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("robot.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("robot.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int targetX = nextInt();
    int targetY = nextInt();

    Pair[] instructions = new Pair[N];
    for (int i = 0; i < N; i++) {
      instructions[i] = new Pair(nextInt(),  nextInt());
    }

    // do first half
    int[] result = new int[N+1];

    boolean[] binary = new boolean[N/2];
    HashMap<String, List<Integer>> combs = new HashMap<>();
    for (int i = 0; i < Math.pow(2, N/2)-1; i++) {
      addOne(binary);

      long totalX = 0;
      long totalY = 0;
      int len = 0;
      for (int j = 0; j < N/2; j++) {
        if (binary[j]) {
          len++;
          totalX += instructions[j].x;
          totalY += instructions[j].y;
        }
      }
      if (totalX == targetX && totalY == targetY) {
        result[len]++;
      }
      // long hash = totalX*31+totalY;
      String hash = totalX + " " + totalY;
      if (!combs.containsKey(hash)) {
        combs.put(hash, new ArrayList<>());
      }
      combs.get(hash).add(len);
    }


    boolean[] binary2 = new boolean[N-N/2];
    for (int i = 0; i < Math.pow(2, N-N/2)-1; i++) {
      addOne(binary2);

      long totalX = 0;
      long totalY = 0;
      int len = 0;
      for (int j = 0; j < N-N/2; j++) {
        if (binary2[j]) {
          len++;
          totalX += instructions[N/2+j].x;
          totalY += instructions[N/2+j].y;
        }
      }
      if (totalX == targetX && totalY == targetY) {
        result[len]++;
      }
      // long hash = Objects.hash(targetX-totalX)*31 + (targetY-totalY);
      String hash = (targetX-totalX) + " " + (targetY-totalY);
      if (combs.containsKey(hash)) {
        for (int c : combs.get(hash)) {
          result[c + len]++;
        }
      }
    }
    
    for (int i = 1; i <= N; i++) {
      pw.println(result[i]);
    }

    br.close();
    pw.close();
  }

  private static class Pair {
    int x;
    int y;
    int len;
    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static void addOne(boolean[] binary) {
    for (int i = binary.length-1; i >= 0; i--) {
      if (!binary[i]) {
        binary[i] = true;
        for (int j = i+1; j < binary.length; j++) {
          binary[j] = false;
        }
        break;
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