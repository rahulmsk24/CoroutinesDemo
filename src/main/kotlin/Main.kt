import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking{
    launchCoroutinesExample()

    println("async execution started ")
    asyncCoroutineExample()

}

suspend fun makeItDelay(int : Long) {
    delay(int)
}

/* Launch coroutine
*  It does not block the main thread and for blocking we have used runBlocking
*  It returns Job object and with that we can control the coroutine like cancel and wait
*  job.join() waits for co routine to complete it execution
*  job.cancel will cancel the coroutine
* */
fun launchCoroutinesExample() = runBlocking{
    println("launch main program starts : ${Thread.currentThread().name}")

    val job : Job = launch {
        println("launch Fake work thread : ${Thread.currentThread().name}")
        makeItDelay(1000)
        println("launch Fake work thread : ${Thread.currentThread().name}")

    }
    job.join()

    println("launch main program ends : ${Thread.currentThread().name}")
}

/* async coroutine
*  It does not block the main thread and for blocking we have used runBlocking
*  It returns Deferred object and with that we can control the coroutine like cancel and wait also can get return value from the coroutine
*  deferred is the subclass of job class so that we cna access job class methods also
*  difference is that is return the value, and we have seen that in the below example
*  deferred.join() waits for co routine to complete it execution
*  deferred.cancel will cancel the coroutine
* */
fun asyncCoroutineExample() = runBlocking{
    println("async main program starts : ${Thread.currentThread().name}")

    val job : Deferred<Int> = async {
        println("async Fake work thread : ${Thread.currentThread().name}")
        makeItDelay(1000)
        println("async Fake work thread : ${Thread.currentThread().name}")
        26
    }

    val data : Int = job.await()

    println("async main program ends : ${Thread.currentThread().name}")
    println("async value returns from coroutine  : $data")
}