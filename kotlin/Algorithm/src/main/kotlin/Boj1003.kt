fun main(args: Array<String>) {
    val dp: Array<Array<Int>> = Array(41) { arrayOf(0, 0) }
    dp[0] = arrayOf(1, 0)
    dp[1] = arrayOf(0, 1)

    val T: Int = readln().toInt()

    repeat(T) {
        val N: Int = readln().toInt()

        for (i: Int in 2..N) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0]
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1]
        }

        println("${dp[N][0]} ${dp[N][1]}")
    }
}