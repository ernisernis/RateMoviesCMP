import org.ernisernis.ratemoviescmp.core.presentation.formatVoteAverage
import kotlin.test.Test
import kotlin.test.assertEquals

// The value from the backend is always between 0.0 and 10.0
class FormatVoteAverageKtTest {

    @Test
    fun `Case 1`() {
        val value = 9.11
        val expected = "9.1"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }

    @Test
    fun `Case 2`() {
        val value = 8.0
        val expected = "8"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }

    @Test
    fun `Case 3`() {
        val value = 7.00
        val expected = "7"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }

    @Test
    fun `Case 4`() {
        val value = 7.67
        val expected = "7.7"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }

    @Test
    fun `Case 5`() {
        val value = 7.65
        val expected = "7.6"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }

    @Test
    fun `Case 6`() {
        val value = 7.60
        val expected = "7.6"

        val actual = value.formatVoteAverage()

        assertEquals(expected, actual, "String should be $expected")
    }
}