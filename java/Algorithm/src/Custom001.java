import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Custom001 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int t = Integer.parseInt(br.readLine());

        for (int testNum = 1; testNum <= t; testNum++) {
            String[] inputData = br.readLine().split(" ");
            int n = Integer.parseInt(inputData[0]), k = Integer.parseInt(inputData[1]);
            int[] interests = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            System.out.printf("#%d: %d\n", testNum, solution(n, k, interests));
        }

        br.close();
        ir.close();
    }

    private static int solution(int n, int k, int[] interests) {
        int maxGroup = 0, startIdx = 0, endIdx = 0;

        while (endIdx < n) {
            while (endIdx < n && interests[endIdx] - interests[startIdx] <= k)
                endIdx++;

            maxGroup = Math.max(maxGroup, endIdx - startIdx);

            while (endIdx < n && interests[endIdx] - interests[startIdx] > k)
                startIdx++;
        }

        return maxGroup;
    }
}
