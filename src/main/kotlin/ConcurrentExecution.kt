import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/*Here we are checking the concurrent execution of coroutines
* and the coroutine are by default execute sequentially if we want to execute some data concurrently
* then we have to make it concurrent as below */
fun main(args: Array<String>) = runBlocking {
    println("main method execution starts with thread - ${Thread.currentThread().name}")
    val time = measureTimeMillis {
        val messageOne :Deferred<String> = async { concurrentMethodOne() }
        val messageTwo :Deferred<String> = async { concurrentMethodTwo() }
        println("message from methods - ${messageOne.await() + messageTwo.await()}")
    }
    println("main method execution ends with thread - ${Thread.currentThread().name} time taken - $time")
}

suspend fun concurrentMethodOne() : String{
    println("method one execution starts with thread - ${Thread.currentThread().name}")
    delay(1000)
    println("method one execution ends with thread - ${Thread.currentThread().name}")
    return "method one message"
}

suspend fun concurrentMethodTwo() : String{
    println("method Two execution starts with thread - ${Thread.currentThread().name}")
    delay(1000)
    println("method Two execution ends with thread - ${Thread.currentThread().name}")
    return "method two message"
}

//Output
//main method execution starts with thread - main
//method one execution starts with thread - main
//method Two execution starts with thread - main
//method one execution ends with thread - main
//method Two execution ends with thread - main
//message from methods - method one messagemethod two message
//main method execution ends with thread - main time taken - 1017

//here if you see method one and method two both started one after one concurrently
//that means this is the concurrent execution and it is possible when we created 2
// async coroutine inside the main runBlocking coroutine
// so concurrent coroutine is possible with run or async builder