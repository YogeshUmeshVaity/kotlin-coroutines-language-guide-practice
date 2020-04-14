package abc.anydiff123

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun foo(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

/**
 * Flow gets cancelled before collecting all 3 values. Only collects 1 and 2.
 */
@InternalCoroutinesApi
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) {
        foo().collect {
            println(it)
        }
    }
    println("Done")
}