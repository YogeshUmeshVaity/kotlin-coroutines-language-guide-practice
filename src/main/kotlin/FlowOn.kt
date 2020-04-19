package abc.anydiff123.flowOn

import log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext



@ExperimentalCoroutinesApi
fun fooFlowOn() = flow {
    log("starting flow")
    for ( i in 1..3) {
        log("Emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)

/**
 * Since fooFlow().collect() is called from worker thread, the body of the flow is also called
 * in the worker thread.
 */
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    log("Before changing the context.")
    fooFlowOn().collect { log("Collected $it") }
}