import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Rental {
  static StreamTokenizer st;
  static int N;
  static int M;
  static int R;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("rental.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("rental.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    M = nextInt();
    R = nextInt();
    
    // solve
    int[] cows = new int[N];

    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    Arrays.sort(cows);

    int[][] offers = new int[M][2];
    for (int i = 0; i < M; i++) {
      offers[i][0] = nextInt();
      offers[i][1] = nextInt();
    }

    Arrays.sort(offers, (int[] a, int[] b) -> b[1] - a[1]);

    Integer[] rents = new Integer[R];
    for (int i = 0; i < R; i++) {
      rents[i] = nextInt();
    }

    Arrays.sort(rents, Collections.reverseOrder());

    int left = 0;
    int right = N-1;
    int curRentIdx = 0;
    int curOfferIdx = 0;
    long result = 0;
    long rightValue = 0;
    int newOfferIdx = curOfferIdx;
    boolean alreadyDid = false;

    while (left<=right) {
      long leftValue = curRentIdx < R ? rents[curRentIdx] : 0;
      if (!alreadyDid) {
        rightValue = 0;
        newOfferIdx = curOfferIdx;
        int milkLeft = cows[right];
        while (newOfferIdx < M) {
          if (milkLeft <= offers[newOfferIdx][0]) {
            offers[newOfferIdx][0] -= milkLeft;
            rightValue += (long) milkLeft * offers[newOfferIdx][1];
            break;
          } else {
            // take all
            rightValue += (long) offers[newOfferIdx][0] * offers[newOfferIdx][1];
            milkLeft -= offers[newOfferIdx][0];
          }
          newOfferIdx++;
        }
      }

      if (leftValue > rightValue) {
        result += leftValue;
        curRentIdx++;
        left++;
        alreadyDid = true;
      } else {
        result += rightValue;
        curOfferIdx = newOfferIdx;
        right--;
        alreadyDid = false;
      }

    }

    pw.println(result);

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