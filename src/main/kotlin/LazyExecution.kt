import kotlinx.coroutines.*

/*In this example we are going to check lazy coroutine execution
* to make coroutine lazy we can pass "(start = CoroutineStart.LAZY)" to coroutine builder
* If user will use the result of the coroutine then only that methods will execute otherwise method will skip to execute
* Here we are creating 2 methods and one method result we are going to use for printing message and second method result we are not
* going to use, and then we will check that output for which method is executed and which method is skipped*/

fun main(args: Array<String>) = runBlocking {
    val messageOne : Deferred<String> = async(start = CoroutineStart.LAZY) { lazyMethodOne() }
    val messageTwo : Deferred<String> = async(start = CoroutineStart.LAZY) { lazyMethodTwo() }

    println("message one is -> ${messageOne.await()}")
}

suspend fun lazyMethodOne() : String{
    println("method one execution starts with thread - ${Thread.currentThread().name}")
    delay(1000)
    println("method one execution ends with thread - ${Thread.currentThread().name}")
    return "method one message"
}

suspend fun lazyMethodTwo() : String{
    println("method Two execution starts with thread - ${Thread.currentThread().name}")
    delay(1000)
    println("method Two execution ends with thread - ${Thread.currentThread().name}")
    return "method two message"
}
//output
//method one execution starts with thread - main
//method one execution ends with thread - main
//message one is -> method one message

//In this output we can see that only lazyMethodOne() is executed because we are using its result


