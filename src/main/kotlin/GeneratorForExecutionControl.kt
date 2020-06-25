package generatorfor.controlflow

/**
 * Generator can be used to control the execution of the program flow from outside.
 * The function allows to pause its execution and pass control(fate) of the remainder of the
 * function execution to the caller.
 */
fun controlProgramFlow() = sequence {
    println("Execution started")
    yield(0)
    println("Execution resumed")
    yield(1)
    println("Execution resumed")
}

fun main() {
    val controlIterator = controlProgramFlow().iterator()
    println(controlIterator.next())
    println(controlIterator.next())
    // Throws Exception if no more elements. Use hasNext() to check.
    // println(controlIterator.next())
}