import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            if(System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++}...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("I'm tired of waiting.")
    job.cancelAndJoin()
    println("Now I can quit.")
}