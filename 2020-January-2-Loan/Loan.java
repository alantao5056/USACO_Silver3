import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Loan {
  static StreamTokenizer st;
  static long N;
  static long K;
  static long M;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("loan.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("loan.out"));
    N = nextLong();
    K = nextLong();
    M = nextLong();
    
    // solve
    long low = 1;
    long high = N;
    
    while (low < high) { // notice we do not compare element at mid with our target
      long mid = low + (high - low + 1) / 2;
      if (check(mid))
        low = mid;
      else
        high = mid-1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  // private static boolean pay(long x) {
  //   long n = N;
  //   long k = 0;
  //   while (n > 0) {
  //     long payment = Math.max(M, n/x);
  //     long times = Math.max((n % x) / payment, 1);
  //     times = Math.min(times, K - k);
  //     n -= times * payment;
  //     k += times;
  //   }
  //   return n <= 0;
  // }

  private static boolean check(long x) {
    long remain = N;
    long curDays = 0;

    while (remain > 0 && curDays < K) {
      long subtract = remain / x;

      if (subtract <= M) {
        curDays += (long) Math.ceil((double) remain / M);
        return curDays <= K;
      }

      long size = remain % x;
      if (size == 0) {
        // give once
        remain -= subtract;
        curDays++;
      } else {
        long days = (long) Math.ceil((double) size / subtract);
        remain -= days * subtract;
        curDays += days;
      }
    }

    return remain <= 0 && curDays <= K;
  }

  private static long nextLong() throws Exception {
    st.nextToken();
    return (long) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}