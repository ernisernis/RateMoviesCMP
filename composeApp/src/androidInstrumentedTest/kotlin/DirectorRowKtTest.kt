import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.DirectorRow
import org.junit.Rule
import org.junit.Test

class DirectorRowKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun director_row_content_displays_correctly() {
        val text1 = "Sam"
        val text2 = "Akinawa"

        composeTestRule.setContent {
            DirectorRow(
                director = text1,
                writer = text2,
            )
        }

        composeTestRule.onNodeWithText(text1, substring = true).assertExists()
        composeTestRule.onNodeWithText(text2, substring = true).assertExists()
    }
}