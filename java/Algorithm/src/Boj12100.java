import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Boj12100 {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int n = Integer.parseInt(br.readLine());
        int[][] gameMap = new int[n][n];

        for (int r = 0; r < n; r++) {
            int[] inputDatas = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.arraycopy(inputDatas, 0, gameMap[r], 0, n);
        }

        System.out.println(dfs(n, gameMap, 0));

        br.close();
        ir.close();
    }

    private static int dfs(int n, int[][] gameMap, int currentRound) {
        if (currentRound == 5) {
            return findMaxValue(gameMap);
        }

        int maxValue = 0;
        for (Direction direction : Direction.values()) {
            int[][] changedGameMap = deepCopy(gameMap);
            if (direction == Direction.UP) {
                for (int c = 0; c < n; c++) {
                    int mergeableIdx = 0;
                    for (int r = 0; r < n; r++) {
                        if (r == mergeableIdx || changedGameMap[r][c] == 0) continue;

                        if (changedGameMap[mergeableIdx][c] == 0) {
                            swap(changedGameMap, r, c, mergeableIdx, c);
                            continue;
                        }

                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2;
                            changedGameMap[r][c] = 0;
                        } else {
                            swap(changedGameMap, mergeableIdx + 1, c, r, c);
                        }
                        mergeableIdx++;
                    }
                }
            } else if (direction == Direction.DOWN) {
                for (int c = 0; c < n; c++) {
                    int mergeableIdx = n - 1;
                    for (int r = n - 1; r >= 0; r--) {
                        if (r == mergeableIdx || changedGameMap[r][c] == 0) continue;

                        if (changedGameMap[mergeableIdx][c] == 0) {
                            swap(changedGameMap, r, c, mergeableIdx, c);
                            continue;
                        }

                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2;
                            changedGameMap[r][c] = 0;
                        } else {
                            swap(changedGameMap, mergeableIdx - 1, c, r, c);
                        }
                        mergeableIdx--;
                    }
                }
            } else if (direction == Direction.RIGHT) {
                for (int r = 0; r < n; r++) {
                    int mergeableIdx = n - 1;
                    for (int c = n - 1; c >= 0; c--) {
                        if (c == mergeableIdx || changedGameMap[r][c] == 0) continue;

                        if (changedGameMap[r][mergeableIdx] == 0) {
                            swap(changedGameMap, r, c, r, mergeableIdx);
                            continue;
                        }

                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2;
                            changedGameMap[r][c] = 0;
                        } else {
                            swap(changedGameMap, r, mergeableIdx - 1, r, c);
                        }
                        mergeableIdx--;
                    }
                }
            } else {
                for (int r = 0; r < n; r++) {
                    int mergeableIdx = 0;
                    for (int c = 0; c < n; c++) {
                        if (c == mergeableIdx || changedGameMap[r][c] == 0) continue;

                        if (changedGameMap[r][mergeableIdx] == 0) {
                            swap(changedGameMap, r, c, r, mergeableIdx);
                            continue;
                        }

                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2;
                            changedGameMap[r][c] = 0;
                        } else {
                            swap(changedGameMap, r, mergeableIdx + 1, r, c);
                        }
                        mergeableIdx++;
                    }
                }
            }

            int currentMaxValue;

            if (arrayEqual(gameMap, changedGameMap)) currentMaxValue = findMaxValue(changedGameMap);
            else currentMaxValue = dfs(n, changedGameMap, currentRound + 1);

            if (maxValue < currentMaxValue) maxValue = currentMaxValue;
        }

        return maxValue;
    }

    private static int findMaxValue(int[][] arr) {
        return Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(0);
    }

    private static boolean arrayEqual(int[][] arr1, int[][] arr2) {
        return IntStream.range(0, arr1.length).allMatch(r -> Arrays.equals(arr1[r], arr2[r]));
    }

    private static int[][] deepCopy(int[][] arr) {
        int[][] newArr = new int[arr.length][];
        for (int r = 0; r < arr.length; r++) {
            newArr[r] = arr[r].clone();
        }
        return newArr;
    }

    private static void swap(int[][] arr, int v1Row, int v1Col, int v2Row, int v2Col) {
        int temp = arr[v1Row][v1Col];
        arr[v1Row][v1Col] = arr[v2Row][v2Col];
        arr[v2Row][v2Col] = temp;
    }
}
