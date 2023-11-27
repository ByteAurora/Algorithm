fun main() {
    repeat(readln().toInt()) {
        val commands = readln()
        val dataLength = readln().toInt()
        val inputData: String = readln()

        if (dataLength == 0 && commands.contains('D')) {
            println("error")
            return@repeat
        }

        var isReversed = false
        var startIdx = 0
        var endIdx: Int = dataLength - 1

        val data = if (dataLength > 0) inputData.removeSurrounding("[", "]")
            .split(',')
            .map { it.toInt() }
            .toTypedArray() else emptyArray()

        for (command in commands) {
            when (command) {
                'R' -> isReversed = !isReversed
                'D' -> {
                    if (startIdx > endIdx) {
                        print("error")
                        return
                    }
                    if (isReversed) endIdx-- else startIdx++
                }
            }
        }

        printArray(data, startIdx, endIdx, isReversed)
    }
}

fun printArray(array: Array<Int>, startIdx: Int, endIdx: Int, isReversed: Boolean) {
    val result = if (isReversed) {
        array.sliceArray(startIdx..endIdx).reversed().joinToString(",")
    } else {
        array.sliceArray(startIdx..endIdx).joinToString(",")
    }

    println("[$result]")
}