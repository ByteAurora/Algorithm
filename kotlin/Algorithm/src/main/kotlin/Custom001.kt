import kotlin.math.max

fun main() {
    for (testNum in 1..readln().toInt()) {
        val inputData = readln().split(' ')
        val n = inputData[0].toInt()
        val k = inputData[1].toInt()

        val interests: Array<Int> = readln().split(' ').map { s -> s.toInt() }.toTypedArray()

        println("#${testNum}: ${solution(n, k, interests)}")
    }
}

fun solution(n: Int, k: Int, interests: Array<Int>): Int {
    var maxGroup = 0
    var startIdx = 0
    var endIdx = 0

    interests.sort()

    while (endIdx < n) {
        while (endIdx < n && interests[endIdx] - interests[startIdx] <= k)
            endIdx++

        maxGroup = max(maxGroup, endIdx - startIdx)

        while (endIdx < n && interests[endIdx] - interests[startIdx] > k)
            startIdx++
    }

    return maxGroup
}