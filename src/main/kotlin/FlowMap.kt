package abc.anydiff123.flowmap

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "request: $request"
}


/**
 * Just like collections and sequences, flows can be transformed with operators like map(), filter().
 * Key difference between operators of sequences and operators of Flows is that the blocks of code inside operators of
 * Flow can call suspending functions.
 *
 * In this example, A Flow of incoming requests is mapped to the results with the map() operator, even when performing
 * a request is a long running operation. And this long running operation is implemented by a suspending function.
 */
fun main() = runBlocking {
    (1..3).asFlow()
        .map { request -> performRequest(request) }
        .collect { println(it) }
}
