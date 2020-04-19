import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun log(message: String) = println("${Thread.currentThread().name} : $message")

fun fooFlow() = flow {
    log("starting flow")
    for ( i in 1..3) {
        emit(i)
    }
}

/**
 * Since fooFlow().collect() is called from worker thread, the body of the flow is also called
 * in the worker thread.
 */
fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        fooFlow().collect { log("Collected $it") }
    }
}

