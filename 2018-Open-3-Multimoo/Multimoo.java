import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Multimoo {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("multimoo.out"));
    N = nextInt();
    
    // solve
    Cow[][] grid = new Cow[N+2][N+2];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        grid[i][j] = new Cow(nextInt(), i, j);
      }
    }

    // give group ids
    int curGroupId = 0;
    int maxSize = 0;
    List<Group> groups = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (grid[i][j].visited) continue;

        // bfs
        ArrayDeque<Cow> q = new ArrayDeque<>();
        q.add(grid[i][j]);
        grid[i][j].visited = true;
        Group curGroup = new Group(grid[i][j].id, curGroupId);
        groups.add(curGroup);

        int size = 0;
        while (!q.isEmpty()) {
          Cow curCow = q.poll();
          curCow.groupId = curGroupId;
          size++;

          int id = curCow.id;
          int i_ = curCow.i;
          int j_ = curCow.j;

          Cow down = grid[i_+1][j_];
          if (down != null && down.id == id && !down.visited) {
            q.add(down);
            down.visited = true;
          }
          Cow up = grid[i_-1][j_];
          if (up != null && up.id == id && !up.visited) {
            q.add(up);
            up.visited = true;
          }
          Cow right = grid[i_][j_+1];
          if (right != null && right.id == id && !right.visited) {
            q.add(right);
            right.visited = true;
          }
          Cow left = grid[i_][j_-1];
          if (left != null && left.id == id && !left.visited) {
            q.add(left);
            left.visited = true;
          }
        }

        curGroup.size = size;
        maxSize = Math.max(maxSize, size);
        curGroupId++;
      }
    }

    pw.println(maxSize);

    // reset visited
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        grid[i][j].visited = false;
      }
    }

    for (Group g : groups) {
      g.visited = new boolean[groups.size()];
      g.traversed = new boolean[groups.size()];
    }

    // get max and split into groups
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        Cow curCow = grid[i][j];
        Group curGroup = groups.get(grid[i][j].groupId);
        int i_ = curCow.i;
        int j_ = curCow.j;

        Cow down = grid[i_+1][j_];
        if (down != null &&
            down.groupId != curGroup.groupId &&
            !curGroup.visited[down.groupId]) {
          curGroup.nbs.add(groups.get(down.groupId));
          curGroup.visited[down.groupId] = true;
        }

        Cow up = grid[i_-1][j_];
        if (up != null &&
            up.groupId != curGroup.groupId &&
            !curGroup.visited[up.groupId]) {
          curGroup.nbs.add(groups.get(up.groupId));
          curGroup.visited[up.groupId] = true;
        }

        Cow right = grid[i_][j_+1];
        if (right != null &&
            right.groupId != curGroup.groupId &&
            !curGroup.visited[right.groupId]) {
          curGroup.nbs.add(groups.get(right.groupId));
          curGroup.visited[right.groupId] = true;
        }

        Cow left = grid[i_][j_-1];
        if (left != null &&
            left.groupId != curGroup.groupId &&
            !curGroup.visited[left.groupId]) {
          curGroup.nbs.add(groups.get(left.groupId));
          curGroup.visited[left.groupId] = true;
        }
      }
    }

    // traverse groups
    int maxLen = 0;
    for (Group g : groups) {
      for (int i = 0; i < g.nbs.size(); i++) {
        if (g.traversed[g.nbs.get(i).groupId]) continue;

        // not traversed
        int curLen = 0;
        ArrayDeque<Group> q = new ArrayDeque<>();
        boolean[] visited = new boolean[groups.size()];
        q.add(g);
        visited[g.groupId] = true;
        while (!q.isEmpty()) {
          Group curG = q.poll();
          curLen += curG.size;
          for (Group nb : curG.nbs) {
            if (!curG.traversed[nb.groupId] && (nb.id == g.id || nb.id == g.nbs.get(i).id)) {
              curG.traversed[nb.groupId] = true;
              nb.traversed[curG.groupId] = true;
              if (!visited[nb.groupId]) {
                q.add(nb);
                visited[nb.groupId] = true;
              }
            }
          }
        }

        maxLen = Math.max(maxLen, curLen);
      }
    }

    pw.println(maxLen);

    br.close();
    pw.close();
  }

  private static class Group {
    int id;
    int groupId;
    int size;
    List<Group> nbs = new ArrayList<>();
    boolean[] visited;
    boolean[] traversed;

    public Group(int id, int groupId) {
      this.id = id;
      this.groupId = groupId;
    }
  }

  private static class Cow {
    int id;
    int i;
    int j;
    int groupId;
    boolean visited = false;

    public Cow(int id, int i, int j) {
      this.id = id;
      this.i = i;
      this.j = j;
    }

    @Override
    public String toString() {
      return Integer.toString(id);
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