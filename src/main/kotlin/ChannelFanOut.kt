package coroutines.practice.channel.fanout

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce {
    var x = 0
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (message in channel) {
        println("Processor: $id, received: $message")
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val producer = produceNumbers()
    repeat(5) {
        launchProcessor(it, producer)
    }
    delay(1000)
    producer.cancel()
}