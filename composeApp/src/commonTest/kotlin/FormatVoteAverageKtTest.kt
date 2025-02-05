import org.ernisernis.ratemoviescmp.core.presentation.formatVoteAverage
import kotlin.test.Test
import kotlin.test.assertEquals

// The value from the backend is always between 0.0 and 10.0
class FormatVoteAverageKtTest {

    private val testCases = listOf(
        Pair(9.11, "9.1"),
        Pair(8.0, "8"),
        Pair(7.00, "7"),
        Pair(7.67, "7.7"),
        Pair(7.65, "7.6"),
        Pair(7.60, "7.6"),
        Pair(5.132560, "5.1"),
    )

    @Test
    fun `Check if formatter outputs correct values`() {
        testCases.forEach { (value, expected) ->
            val actual = value.formatVoteAverage()
            assertEquals(expected, actual, "String should be $expected")
        }
    }
}