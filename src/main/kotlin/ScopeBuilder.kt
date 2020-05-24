import kotlinx.coroutines.*

/**
 * runBlocking and coroutineScope may look similar because they both wait for its body and all its
 * children to complete. The main difference between these two is that the runBlocking method blocks
 * the current thread for waiting, while coroutineScope just suspends, releasing the underlying
 * thread for other usages. Because of that difference, runBlocking is a regular function and
 * coroutineScope is a suspending function.
 */
fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(50L)
        println("Task from runBlocking")
    }

    coroutineScope { // Creates a coroutine scope
        launch {
            delay(75L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }

    println("Coroutine scope is over") // This line is not printed until the nested launch completes
}