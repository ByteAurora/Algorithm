import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj5430 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int T = Integer.parseInt(br.readLine());

        for (int testIdx = 0; testIdx < T; testIdx++) {
            String commands = br.readLine();
            int dataLength = Integer.parseInt(br.readLine());
            String inputData = br.readLine().replace("[", "").replace("]", "");

            if (dataLength == 0 && commands.contains("D")) {
                System.out.println("error");
                continue;
            }

            boolean isError = false;
            boolean isReversed = false;
            int startIdx = 0, endIdx = dataLength - 1;

            int[] dataList = new int[dataLength];

            if (dataLength > 0) {
                String[] splitDataList = inputData.split(",");
                for (int dataIdx = 0; dataIdx < dataLength; dataIdx++) {
                    dataList[dataIdx] = Integer.parseInt(splitDataList[dataIdx]);
                }
            }

            for (char command : commands.toCharArray()) {
                if (command == 'R') {
                    isReversed = !isReversed;
                } else if (command == 'D') {
                    if (startIdx > endIdx) {
                        isError = true;
                        break;
                    }

                    if (isReversed) endIdx--;
                    else startIdx++;
                }
            }

            if (isError) {
                System.out.println("error");
            } else {
                printArray(dataList, startIdx, endIdx, isReversed);
            }
        }

        br.close();
        ir.close();
    }

    static void printArray(int[] dataList, int startIdx, int endIdx, boolean isReversed) {
        StringBuilder result = new StringBuilder();

        result.append("[");
        if (isReversed) {
            for (int idx = endIdx; idx >= startIdx; idx--) {
                result.append(dataList[idx]);
                if (idx > startIdx) result.append(",");
            }
        } else {
            for (int idx = startIdx; idx <= endIdx; idx++) {
                result.append(dataList[idx]);
                if (idx < endIdx) result.append(",");
            }
        }
        result.append("]");
        System.out.println(result);
    }
}