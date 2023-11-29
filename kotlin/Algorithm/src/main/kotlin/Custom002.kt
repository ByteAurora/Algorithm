import java.util.*

data class Node(
    var value: Int = 0,
    var outNode: Node? = null,
    var enterNodeCount: Int = 0
)

fun solution(n: Int, k: Int, connections: List<Pair<String, String>>): Int {
    val nodes: MutableMap<String, Node> = mutableMapOf()

    connections.forEach { (parent, child) ->

        nodes.getOrPut(parent) { Node() }.apply { enterNodeCount += 1 }
        nodes.getOrPut(child) { Node() }.outNode = nodes[parent]
    }

    val queue = LinkedList<Node>().apply {
        nodes.values.filter { node ->
            node.enterNodeCount == 0
        }.forEach { startNode -> add(startNode.apply { value = 1 }) }
    }

    var totalWeight = 0

    while (queue.isNotEmpty()) {
        val currentNode: Node = queue.poll()

        totalWeight += currentNode.value

        currentNode.outNode?.let { outNode ->
            outNode.enterNodeCount--
            outNode.value += currentNode.value

            if (outNode.enterNodeCount == 0) queue.add(outNode)
        }
    }


    return k / totalWeight
}

fun main() {
    for (testNum in 1..readln().toInt()) {
        val inputData: List<Int> = readln().split(" ").map { e -> e.toInt() }
        val n = inputData[0]
        val k = inputData[1]

        val connections = mutableListOf<Pair<String, String>>()
        repeat(n) {
            val connectionData: List<String> = readln().split(" ")
            connections.add(Pair(connectionData[0], connectionData[1]))
        }

        println("#$testNum: ${solution(n, k, connections)}")
    }
}