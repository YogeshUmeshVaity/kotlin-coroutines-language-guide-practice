import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        // These functions are executed sequentially and execute in 2000 ms, if we don't use async-await.
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("result: ${one + two}")
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
