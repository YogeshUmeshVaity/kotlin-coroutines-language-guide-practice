import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun numbers() = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not be reached.")
        emit(3)
    } catch (e: Exception) {
        println(e.printStackTrace())
    } finally {
        println("Inside finally")
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    numbers().take(2).collect { println(it) }
}