import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun fooConflate() = flow {
    for (i in 1..3) {
        delay(100) // Asynchronously waiting
        emit(i)
    }
}

/**
 * conflate operator can be used to skip the intermediate values, when the collector is slow to process the values.
 * Only most recent value that's emitted gets collected.
 */
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val time = measureTimeMillis {
        fooConflate()
            .conflate()
            .collect {
                delay(300)  // Processing the value slow.
                println("Collected: $it")
            }
    }
    println("Completed in $time ms")
}