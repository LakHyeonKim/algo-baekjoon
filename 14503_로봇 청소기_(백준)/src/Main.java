import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int i;
		int j;
		int d;
		Point(int i, int j, int d){
			this.i = i;
			this.j = j;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", d=" + d + "]";
		} 

	}

	private static int N,M;
	private static String[] line;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		line = br.readLine().split(" ");
		Point start = new Point(Integer.parseInt(line[0]), 
				Integer.parseInt(line[1]),
				Integer.parseInt(line[2]));

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		int ans = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		boolean flag = true;
		outer1:while(!q.isEmpty()) {
			Point cur = q.poll();
			System.out.println(cur);
			for(int i = 0; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			if(flag) {
				if(map[cur.i][cur.j] == 0) {
					map[cur.i][cur.j] = 2;
					ans++;
				}
			}
			int nextI = cur.i + dir[(cur.d+3)%4][0];
			int nextJ = cur.j + dir[(cur.d+3)%4][1];
			if(map[nextI][nextJ] == 0) {
				q.offer(new Point(nextI, nextJ, (cur.d+3)%4));
				flag = true;
				continue outer1;
			}else if(map[nextI][nextJ] == 2){
				q.offer(new Point(cur.i, cur.j, (cur.d+3)%4));
				flag = false;
				continue outer1;
			}
			int cnt = 0;
			for(int k = 0; k < 4; k++) {
				nextI = cur.i + dir[k][0];
				nextJ = cur.j + dir[k][1];
				if(map[nextI][nextJ] == 1 || map[nextI][nextJ] == 2) {
					cnt++;
				}
			}
			if(cnt == 4) {
				nextI = cur.i + dir[(cur.d+2)%4][0];
				nextJ = cur.j + dir[(cur.d+2)%4][1];
				if(map[nextI][nextJ] == 1) break;
				else {
					q.offer(new Point(nextI, nextJ, (cur.d+2)%4));
					flag = false;
					continue outer1;
				}
			}
			flag = true;
		}
		System.out.println(ans);
	}

}
