package coroutines.practice.flatmaplatest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun getDetails(id: Int) = flow {
    emit("first name $id")
    delay(500)
    emit("last name $id")
}

/**
 * flatMapLatest { } is similar to the the collectLatest { } but its used for Flow of Flow.
 * For example Flow<Flow<String>> in this case. It will consider only the latest ID flow and fetch
 * values for the latest ID. It cancels all the code in its block on a new value.
 */
@ExperimentalCoroutinesApi
@FlowPreview
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapLatest { delay(300); getDetails(it) }
        .collect {
            println("$it in ${System.currentTimeMillis() - startTime} ms")
        }
}