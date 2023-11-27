import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1003 {
    public static void main(String[] args) throws IOException {
        int[][] dp = new int[41][2];

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(ir);

        int T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bf.readLine());

            for (int f_num_idx = 2; f_num_idx < N + 1; f_num_idx++) {
                dp[f_num_idx][0] = dp[f_num_idx - 1][0] + dp[f_num_idx - 2][0];
                dp[f_num_idx][1] = dp[f_num_idx - 1][1] + dp[f_num_idx - 2][1];
            }

            System.out.println(dp[N][0] + " " + dp[N][1]);
        }

        bf.close();
        ir.close();
    }
}