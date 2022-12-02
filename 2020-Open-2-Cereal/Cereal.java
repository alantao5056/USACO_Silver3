import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cereal {
  static StreamTokenizer st;
  static int N;
  static int M;
  static Cow[] cows;
  static Cow[] taken;
  static int count = 0;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("cereal.out"));
    N = nextInt();
    M = nextInt();
    
    // solve
    cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(nextInt()-1, nextInt()-1, i);
    }

    taken = new Cow[M];
    int[] result = new int[N];
    for (int i = N-1; i >= 0; i--) {
      take(cows[i], true);
      result[i] = count;
    }

    for (int i = 0; i < N; i++) {
      pw.println(result[i]);
    }

    br.close();
    pw.close();
  }

  private static void take(Cow cow, boolean first) {
    if (first) {
      if (taken[cow.first] == null) {
        // can take
        taken[cow.first] = cow;
        cow.firstChoice = true;
        count++;
        return;
      }

      if (cow.id < taken[cow.first].id) {
        // cow has higher priority
        Cow kickedCow = taken[cow.first];
        taken[cow.first] = cow;
        cow.firstChoice = true;
        if (kickedCow.firstChoice) {
          // take second
          kickedCow.firstChoice = false;
          take(kickedCow, false);
        } else {
          kickedCow.firstChoice = null;
        }
        return;
      }
    }

    // take second
    if (taken[cow.second] == null) {
      // can take
      taken[cow.second] = cow;
      cow.firstChoice = true;
      count++;
      return;
    }

    if (cow.id < taken[cow.second].id) {
      // cow has higher priority
      Cow kickedCow = taken[cow.second];
      taken[cow.second] = cow;
      if (kickedCow.firstChoice) {
        // take second
        kickedCow.firstChoice = false;
        take(kickedCow, false);
      } else {
        kickedCow.firstChoice = null;
      }
    }
  }

  private static class Cow {
    int first;
    int second;
    int id;
    Boolean firstChoice;

    public Cow(int first, int second, int id) {
      this.first = first;
      this.second = second;
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