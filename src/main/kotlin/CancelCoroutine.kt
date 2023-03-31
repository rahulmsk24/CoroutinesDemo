import kotlinx.coroutines.*

/* sometimes we need to cancel coroutines
 * there may have many cases like if we are accessing data from server, and it is taking 10 sec plus time
 * in that case we can not wait for the coroutine for that much time so in that kind of cases we always need to cancel the coroutines
 *
 * To cancel the coroutines, our coroutines should be co-operative to cancel
 * and there are two ways we explain below
 *
 * commented by rahul maske on 30-03-2023
 *  */
fun main(args: Array<String>) {
    println("main program starts : ${Thread.currentThread().name}")
    //firstWayToCheckCancelCoroutines()  /*both functions are working we are checking one by one so that the functions are commented*/
    secondWayToCheckCancelCoroutines();


}
/* Que - What makes coroutines cooperative?
*  -> First Way -
*     Periodically invoke a suspending function that checks for the cancellation.
*     Only those suspending functions that belongs to kotlinx.coroutines package will make coroutine cancelable
*     Those functions are delay(), yield(), withContext(), withTimeOut() etc.
*
*   That means if we use those functions in coroutines then that function will check every time that coroutine is cancelled or not
*   And if it is cancelled then entire coroutine get cancelled..
* */
fun firstWayToCheckCancelCoroutines()  = runBlocking{

        val job : Job = launch(Dispatchers.Default) {
            for (i in 0..500){
                print("$i ")
                delay(10)   // this is suspending function and it will check that the coroutine is get cancelled or not and if it is get cancelled as define on line num 37 then coroutine will cancelled
            //Thread.sleep(1)   //sleep function is not suspending function from kotlinx.coroutines package and if we use this, coroutine will not be cooperative hence it won't get cancelled
            }
        }

        delay(50)
        job.cancelAndJoin()
        println("main program end : ${Thread.currentThread().name}")
}

/* Second way -
* -> Explicitly check for the cancellation status within coroutine
*    there is a boolean value property which is "isActive" which is an extension on coroutine scope class and
*    then this flag can use to cancellation status of coroutine
* -> When the coroutine is active then flag is active : isActive =true
* -> When the coroutine is cancelled  then flag is active : isActive =false
* */
fun secondWayToCheckCancelCoroutines() = runBlocking{

    val job : Job = launch(Dispatchers.Default) {
        for (i in 0..500){
            if (!isActive){                 // isActive is another way to check coroutine is cancelled or not
                break
            }
            print("$i ")
            Thread.sleep(1)
        }
    }

    delay(10)
    job.cancelAndJoin()
    println("main program end : ${Thread.currentThread().name}")
}
