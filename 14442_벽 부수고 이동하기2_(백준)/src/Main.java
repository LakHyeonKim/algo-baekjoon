import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int i;
		int j;
		int level;
		int breakCount;
		Point(int i, int j, int level, int breakCount){
			this.i = i;
			this.j = j;
			this.level = level;
			this.breakCount = breakCount;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + ", breakCount=" + breakCount + "]";
		}
	}

	private static int N,M,K;
	private static char[][] map;
	private static boolean[][][] visited;
	private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	private static String[] line;
	private static String mapLine;

	private static int bfs() {
		Queue<Point> q = new LinkedList<>();
		int result = -1;
		visited[0][0][0] = true;
		q.offer(new Point(0, 0, 0, 0));
		outer:while(!q.isEmpty()) {
			Point cur = q.poll();
			//System.out.println(cur);
			if(N == 1 && M == 1) {
				result = 1;
				break outer;
			}
			if(cur.breakCount < K) {
				for(int k = 0; k < 4; k++) {
					int nextI = cur.i + dir[k][0];
					int nextJ = cur.j + dir[k][1];
					if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
					if(nextI == N-1 && nextJ == M-1) {
						result = cur.level+2;
						break outer;
					}
					if(map[nextI][nextJ] == '1' && !visited[cur.breakCount+1][nextI][nextJ]) {
						visited[cur.breakCount+1][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level+1, cur.breakCount+1));
					}
					if(map[nextI][nextJ] == '0' && !visited[cur.breakCount][nextI][nextJ]) {
						visited[cur.breakCount][nextI][nextJ] = true;
						q.offer(new Point(nextI, nextJ, cur.level+1, cur.breakCount));
					}
				}
			}
			for(int k = 0; k < 4; k++) {
				int nextI = cur.i + dir[k][0];
				int nextJ = cur.j + dir[k][1];
				if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) continue;
				if(nextI == N-1 && nextJ == M-1) {
					result = cur.level+2;
					break outer;
				}
				if(map[nextI][nextJ] == '0' && !visited[cur.breakCount][nextI][nextJ]) {
					visited[cur.breakCount][nextI][nextJ] = true;
					q.offer(new Point(nextI, nextJ, cur.level+1, cur.breakCount));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		map = new char[N][M];
		visited = new boolean[K+1][N][M];
		for(int i = 0; i < N; i++) {
			mapLine = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = mapLine.charAt(j);
			}
		}
		System.out.println(bfs());
	}

}
