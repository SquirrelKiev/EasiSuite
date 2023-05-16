package com.ttsgroup.easisuite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ttsgroup.easisuite.ui.theme.EasiSuiteTheme

class MainActivity : ComponentActivity() {
    var homePage: HomePage = HomePage()
    var audioPlayerPage: AudioPlayerPage = AudioPlayerPage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasiSuiteTheme {
                ShowContent()
            }
        }
    }

    @Composable
    @Preview
    private fun ShowContent() {
        val navController: NavHostController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var backStackEntry = navController.currentBackStackEntryAsState()
            var route: String? = backStackEntry
                .value?.destination?.route

            if (route == null)
                route = "unknown"

            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar =
                {
                    MediumTopAppBar(
                        title =
                        {
                            var screenId = Screen.getScreenByRoute(route)?.resourceId
                            var screenName = "unknown"

                            if (screenId != null) {
                                screenName = resources.getString(screenId)
                            }

                            Text(screenName)
                        },
                        navigationIcon = {
                            if (Screen.defaultScreen != Screen.getScreenByRoute(route)) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                content =
                { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.defaultScreen.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Screen.Home.route) {
                            homePage.ShowHomeContent(applicationContext, navController)
                        }
                        composable(Screen.AudioPlayer.route) {
                            audioPlayerPage.ShowAudioPlayerContent()
                        }
                    }
                }
            )
        }
    }
}
