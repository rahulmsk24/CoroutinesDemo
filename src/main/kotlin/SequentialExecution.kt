import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime


/*Here we are checking the sequential execution of coroutines
* and the coroutine are by default execute sequentially lets see the example*/

    fun main(args: Array<String>) = runBlocking {
        println("main method execution starts with thread - ${Thread.currentThread().name}")
        val time = measureTimeMillis {
            methodOne()
            methodTwo()
        }


        println("main method execution ends with thread - ${Thread.currentThread().name} Time taken = $time")
    }

    suspend fun methodOne(){
        println("method one execution starts with thread - ${Thread.currentThread().name}")
        delay(1000)
        println("method one execution ends with thread - ${Thread.currentThread().name}")
    }

    suspend fun methodTwo(){
        println("method Two execution starts with thread - ${Thread.currentThread().name}")
        delay(1000)
        println("method Two execution ends with thread - ${Thread.currentThread().name}")
    }

//Output
//main method execution starts with thread - main
//method one execution starts with thread - main
//method one execution ends with thread - main
//method Two execution starts with thread - main
//method Two execution ends with thread - main
//main method execution ends with thread - main Time taken = 2030

//if you see the output then we can find the execution flow that after method one, method two is executed
//that means this is the sequentially executed and taken 1000 sec per method and total time 2030
//SO SEQUENTIAL EXECUTION IS THE DEFAULT BEHAVIOR OF THE COROUTINE