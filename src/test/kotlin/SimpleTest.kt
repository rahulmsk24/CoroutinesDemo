import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SimpleTest {
    @Test
    fun myDemoTest()= runBlocking{
        makeItDelay(5000)
        Assert.assertEquals(10,5+5)
    }
}