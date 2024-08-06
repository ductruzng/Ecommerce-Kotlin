package trungndph39729.fpoly.assignment.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import trungndph39729.fpoly.assignment.R
import trungndph39729.fpoly.assignment.Screens.FavoriteScreen
import trungndph39729.fpoly.assignment.home_screen.HomeScreen
import trungndph39729.fpoly.assignment.Screens.NotificationScreen
import trungndph39729.fpoly.assignment.Screens.ProfileScreen
import trungndph39729.fpoly.assignment.home_screen.HomeViewModel
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel

data class NavItemState(
    val title: String,
    val selectedIcon: ImageBitmap,
    val unselectedIcon: ImageBitmap,
    val hasBadge: Boolean,
    val badgeNum: Int

)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(navController: NavController, homeViewModel: HomeViewModel,signInViewModel: SignInViewModel) {
    val items = listOf(
        NavItemState(
            title = "Home",
            selectedIcon = ImageBitmap.imageResource(id = R.drawable.clarity_home_solid),
            unselectedIcon = ImageBitmap.imageResource(id = R.drawable.clarity_home_solid_1),
            hasBadge = false,
            badgeNum = 0
        ),
        NavItemState(
            title = "Favorite",
            selectedIcon = ImageBitmap.imageResource(id = R.drawable.marker_1),
            unselectedIcon = ImageBitmap.imageResource(id = R.drawable.marker_1_1),
            hasBadge = false,
            badgeNum = 0
        ),
        NavItemState(
            title = "Notification",
            selectedIcon = ImageBitmap.imageResource(id = R.drawable.bell_1),
            unselectedIcon = ImageBitmap.imageResource(id = R.drawable.bell),
            hasBadge = true,
            badgeNum = 1
        ),
        NavItemState(
            title = "Profile",
            selectedIcon = ImageBitmap.imageResource(id = R.drawable.bi_person_fill),
            unselectedIcon = ImageBitmap.imageResource(id = R.drawable.bi_person),
            hasBadge = false,
            badgeNum = 0
        ),
    )

    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
                    .graphicsLayer {
                        shadowElevation = 10.dp.toPx()
                        shape = RectangleShape
                        clip = true
                    }
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavState == index,
                        onClick = { bottomNavState = index },
                        icon = {
                            BadgedBox(badge = {
                                if (item.hasBadge) Badge(
                                    Modifier.size(6.dp)
                                ) {}

                            }) {
                                Image(
                                    bitmap = if (bottomNavState == index) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp)
                                )

                            }

                        },

                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.White,

                            ),
                    )

                }
            }
        }
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            switchScreen(bottomNavState, navController = navController, homeViewModel = homeViewModel,signInViewModel = signInViewModel)
        }

    }

}

@Composable
fun switchScreen(index: Int, navController: NavController, homeViewModel: HomeViewModel,signInViewModel: SignInViewModel) {
    when (index) {
        0 -> {
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }

        1 -> {
            FavoriteScreen(navController = navController)
        }

        2 -> {
            NotificationScreen(navController = navController)
        }

        3 -> {
            ProfileScreen(navController = navController, signInViewModel = signInViewModel)
        }
    }
}