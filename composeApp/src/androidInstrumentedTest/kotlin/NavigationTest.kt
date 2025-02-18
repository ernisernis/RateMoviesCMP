import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.touchlab.kermit.Logger
import org.ernisernis.ratemoviescmp.app.RmNavHost
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContains
import kotlin.test.assertNotNull


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RmNavHost(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Now Playing").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Now Playing").assertIsDisplayed()
    }

    @Test
    fun appNavHost_swipeLeftAndRight_alItemsAreClickable() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodes(hasScrollAction()).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNode(hasScrollAction())
            .performTouchInput { swipeLeft() }

        composeTestRule.onNode(hasScrollAction())
            .performTouchInput { swipeRight() }

        composeTestRule.onAllNodes(hasClickAction()).assertAll(hasClickAction())
    }

    @Test
    fun appNavHost_clickMovie_navigateToDetail() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodes(hasScrollAction()).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        val id = navController.currentBackStackEntry?.arguments?.getInt("id")
        val route = navController.currentBackStackEntry?.destination?.route ?: ""

        assertNotNull(id)
        assertContains(route, "MovieDetail", message = "Route does not contain expected: MovieDetail, actual: $route")
    }
}