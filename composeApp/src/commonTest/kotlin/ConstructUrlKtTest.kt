import org.ernisernis.ratemoviescmp.BuildKonfig.BASE_URL
import org.ernisernis.ratemoviescmp.core.data.networking.constructUrl
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstructUrlKtTest {

    private val testCases = listOf(
        Pair(BASE_URL, BASE_URL),
        Pair("/movies", "${BASE_URL}movies"),
        Pair("movies", "${BASE_URL}movies"),
        Pair("/movies/31/abc", "${BASE_URL}movies/31/abc"),
        Pair("31movies", "${BASE_URL}31movies"),
        Pair("", BASE_URL),
    )

    @Test
    fun `The string should be constructed properly to include base URL`() {
        testCases.forEach { (value, expected) ->
           val actual = constructUrl(value)

            assertEquals(expected, actual, "constructed URL should be $expected")
        }
    }

}