package kotlinx.coroutines.guide.exampleFlow02

/**
 * The foo() function that we created here is like a generator function in Javascript.
 * In Kotlin, generator functions are created using [Sequence] and yield().
 * yield() is a suspending function. When the next() method is called on the Iterator object,
 * the function that calls yield() function, returns the value passed to the yield() function
 * and gets suspended at that point. It is resumed when the next() function is called on Iterator
 * object again.
 *
 * So, think of the yield() function as "return, and next time start from where you stopped".
 * This is how lazy iterators are created.
 * These can also be used to create infinite elements unlike collections.
 */

fun foo(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it
        yield(i)          // yield next value
    }
}

fun controlProgramFlow() = sequence {
    println("Execution started")
    yield(0)
    println("Execution resumed")
    yield(1)
    println("Execution resumed")
}

fun main() {
    val iterator = foo().iterator()
    println(iterator.next())    // 1
    println(iterator.next())    // 2
    println(iterator.next())    // 3
    //foo().forEach { value -> println(value) }

    val controlIterator = controlProgramFlow().iterator()
    println(controlIterator.next())
    println(controlIterator.next())
}