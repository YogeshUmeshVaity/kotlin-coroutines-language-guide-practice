import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        // These functions are executed sequentially and execute in 2000 ms, if we don't use async-await.
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("result: ${one.await() + two.await()}")
    }
    println("Time taken: $time")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 12
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 18
}
