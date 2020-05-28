package coroutines.practice.catchoperator

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun foo() = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    foo()
        .onEach { value ->
            check(value <= 1)
            println(value)
        }
        .catch { println("Caught $it") }
        .collect()
}