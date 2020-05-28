package coroutines.practice.oncompletion

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun foo() = flow {
    emit(1)
    throw RuntimeException()
}

/**
 * onCompletion is just like 'finally' in try/catch block. It's used for executing an action when
 * the flow collection completes. It can also be used to determine whether flow collection completed
 * normally or with exception as shown in this example. The onCompletion operator, unlike catch,
 * does not handle the exception.
 */

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    foo()
        .onCompletion { cause -> if (cause != null) println("Completed with exception: $cause") }
        .catch { cause -> println("Exception occurred $cause") }
        .collect { println(it) }
}