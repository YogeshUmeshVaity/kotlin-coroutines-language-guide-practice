package coroutines.practice.debounce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun foo() = flow {
    emit(1)
    delay(1000)
    emit(2)
    delay(500)
    emit(3)
    delay(600)
    emit(4)
    delay(1000)
    emit(5)
    delay(1000)
    emit(6)
    delay(1000)
    emit(7)
}


@FlowPreview
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val time = measureTimeMillis {
        foo()
            .debounce(1000)
            .collect {
                println("Collected: $it")
            }
    }
    println("Completed in $time ms")
}