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
 * Sometimes you have a situation where you receive a value from a Flow and you have to pass that
 * value to get another Flow. For example here, from a Flow of IDs 1,2,3, you want to receive
 * details for each of those IDs. So you pass each ID again to the getDetails function to receive
 * another Flow of values. In such cases you end up having Flow of Flow i.e. Flow<Flow<String>>.
 *
 * In this case you use flatMapConcat { } or flatMapMerge { } to flatten it to receive Flow<String>.
 * flatMapConcat { } waits for inner flow to complete before starting to collect the next one.
 * Here getDetails() is the inner Flow.
 */
@FlowPreview
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat { getDetails(it) }
        .collect {
            println("$it in ${System.currentTimeMillis() - startTime} ms")
        }
}