import kotlin.math.max

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun swap(array: Array<IntArray>, v1Row: Int, v1Col: Int, v2Row: Int, v2Col: Int) {
    val temp = array[v1Row][v1Col]
    array[v1Row][v1Col] = array[v2Row][v2Col]
    array[v2Row][v2Col] = temp
}

fun dfs(n: Int, gameMap: Array<IntArray>, currentRound: Int): Int {
    if (currentRound == 5) return gameMap.flatMap { it.toList() }.max()

    var maxValue = 0
    for (direction in Direction.values()) {
        val changedGameMap: Array<IntArray> = Array(gameMap.size) { row -> gameMap[row].copyOf() }
        when (direction) {
            Direction.UP -> {
                for (c in 0..<n) {
                    var mergeableIdx = 0
                    for (r in 0..<n) {
                        if (r == mergeableIdx || changedGameMap[r][c] == 0) continue

                        if (changedGameMap[mergeableIdx][c] == 0) {
                            swap(changedGameMap, mergeableIdx, c, r, c)
                            continue
                        }

                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2
                            changedGameMap[r][c] = 0
                        } else {
                            swap(changedGameMap, mergeableIdx + 1, c, r, c)
                        }
                        mergeableIdx += 1
                    }
                }
            }

            Direction.DOWN -> {
                for (c in 0..<n) {
                    var mergeableIdx = n - 1
                    for (r in n - 1 downTo 0) {
                        if (r == mergeableIdx || changedGameMap[r][c] == 0) continue

                        if (changedGameMap[mergeableIdx][c] == 0) {
                            swap(changedGameMap, mergeableIdx, c, r, c)
                            continue
                        }

                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2
                            changedGameMap[r][c] = 0
                        } else {
                            swap(changedGameMap, mergeableIdx - 1, c, r, c)
                        }
                        mergeableIdx -= 1
                    }
                }
            }

            Direction.RIGHT -> {
                for (r in 0..<n) {
                    var mergeableIdx = n - 1
                    for (c in n - 1 downTo 0) {
                        if (c == mergeableIdx || changedGameMap[r][c] == 0) continue

                        if (changedGameMap[r][mergeableIdx] == 0) {
                            swap(changedGameMap, r, mergeableIdx, r, c)
                            continue
                        }

                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2
                            changedGameMap[r][c] = 0
                        } else {
                            swap(changedGameMap, r, mergeableIdx - 1, r, c)
                        }
                        mergeableIdx -= 1
                    }
                }
            }

            Direction.LEFT -> {
                for (r in 0..<n) {
                    var mergeableIdx = 0
                    for (c in 0..<n) {
                        if (c == mergeableIdx || changedGameMap[r][c] == 0) continue

                        if (changedGameMap[r][mergeableIdx] == 0) {
                            swap(changedGameMap, r, mergeableIdx, r, c)
                            continue
                        }

                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2
                            changedGameMap[r][c] = 0
                        } else {
                            swap(changedGameMap, r, mergeableIdx + 1, r, c)
                        }
                        mergeableIdx += 1
                    }
                }
            }
        }

        maxValue = if (gameMap contentDeepEquals changedGameMap) {
            max(maxValue, gameMap.flatMap { it.toList() }.max())
        } else {
            max(maxValue, dfs(n, changedGameMap, currentRound + 1))
        }
    }

    return maxValue
}

fun main() {
    val n = readln().toInt()
    val gameMap = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    print(dfs(n, gameMap, 0))
}