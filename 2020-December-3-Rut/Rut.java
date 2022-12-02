import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Rut {
  static StreamTokenizer st;
  static int N;
  static Cow[] cows;
  static List<Cow> north;
  static List<Cow> east;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("rut.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("rut.out"));
    N = nextInt();
    
    // solve
    cows = new Cow[N];
    north = new ArrayList<>();
    east = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(nextString().charAt(0) == 'N', nextInt(), nextInt(), i);
      if (cows[i].direction) {
        north.add(cows[i]);
      } else {
        east.add(cows[i]);
      }
    }

    Collections.sort(north, (Cow a, Cow b) -> a.x-b.x);
    Collections.sort(east, (Cow a, Cow b) -> a.y-b.y);

    for (int i = 0; i < N; i++) {
      can(cows[i], 1000000001);
    }

    int[] result = new int[N];
    for (int i = 0; i < N; i++) {
      Cow cur = cows[i];
      while (cur.stop != null) {
        cur = cur.stop;
        result[cur.id]++;
      }
    }

    for (int i = 0; i < N; i++) {
      System.out.println(result[i]);
    }

    br.close();
    // pw.close();
  }

  private static boolean can(Cow cow, int targetPos) {
    if (cow.direction) {
      // north cow
      if (cow.stop != null) {
        // already know is stopped
        return cow.stop.y > targetPos;
      }

      for (Cow e : east) {
        if (e.y < cow.y || e.x > cow.x) continue;
        int collide = whichCollide(cow, e);
        if (collide != 1) continue;
        if (can(e, cow.x)) {
          // will collide
          cow.stop = e;
          return e.y > targetPos;
        }
      }
    } else {
      // east cow
      if (cow.stop != null) {
        // already know is stopped
        return cow.stop.x > targetPos;
      }

      for (Cow n : north) {
        if (n.x < cow.x || n.y > cow.y) continue;
        int collide = whichCollide(cow, n);
        if (collide != 1) continue;
        if (can(n, cow.y)) {
          // will collide
          cow.stop = n;
          return n.x > targetPos;
        }
      }
    }

    // nothing can stop it
    return true;
  }

  private static int whichCollide(Cow a, Cow b) { // returns true if a dies to b
    if (a.direction) {
      // a is north
      return (b.y-a.y) > (a.x-b.x) ? 1 : (b.y-a.y) < (a.x-b.x) ? -1 : 0;
    } else {
      // a is east
      return (a.y-b.y) < (b.x-a.x) ? 1 : (a.y-b.y) > (b.x-a.x) ? -1 : 0;
    }
  }

  private static class Cow {
    boolean direction;
    int id;
    int x;
    int y;
    Cow stop;

    public Cow(boolean direction, int x, int y, int id) {
      this.direction = direction;
      this.x = x;
      this.y = y;
      this.id = id;
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