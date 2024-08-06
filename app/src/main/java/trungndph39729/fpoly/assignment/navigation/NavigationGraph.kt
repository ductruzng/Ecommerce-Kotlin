package trungndph39729.fpoly.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import trungndph39729.fpoly.assignment.Screens.AddShippingAddressScreen
import trungndph39729.fpoly.assignment.Screens.CartScreen
import trungndph39729.fpoly.assignment.Screens.CheckOutScreen
import trungndph39729.fpoly.assignment.Screens.HistoryOrderScreen
import trungndph39729.fpoly.assignment.home_screen.HomeScreen
import trungndph39729.fpoly.assignment.Screens.IntroScreen
import trungndph39729.fpoly.assignment.Screens.ProductDetailScreen
import trungndph39729.fpoly.assignment.Screens.SearchProductScreen
import trungndph39729.fpoly.assignment.Screens.ShippingAddressScreen
import trungndph39729.fpoly.assignment.Screens.SuccessScreen
import trungndph39729.fpoly.assignment.home_screen.HomeViewModel
import trungndph39729.fpoly.assignment.login_screen.LoginScreen
import trungndph39729.fpoly.assignment.login_screen.SignInViewModel
import trungndph39729.fpoly.assignment.signup_screen.SignUpScreen
import trungndph39729.fpoly.assignment.signup_screen.SignUpViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    val homeViewModel: HomeViewModel = viewModel()
    val signInViewModel: SignInViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.IntroScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            LoginScreen(navController = navController, viewModel = signInViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController, viewModel = signUpViewModel)
        }
        composable(route = Screens.IntroScreen.route) {
            IntroScreen(navController = navController)
        }
        composable(route = Screens.NavBar.route) {
            NavBar(navController = navController, homeViewModel = homeViewModel, signInViewModel = signInViewModel)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(route = "${ Screens.DetailScreen.route}/{id}",arguments = listOf(navArgument("id"){type = NavType.StringType})) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                ProductDetailScreen(navController = navController, id = id, homeViewModel = homeViewModel)
            }
        }

        composable(route = Screens.CartScreen.route) {
            CartScreen(navController = navController)
        }
        composable(route = Screens.CheckOutScreen.route) {
            CheckOutScreen(navController = navController, signInViewModel = signInViewModel)
        }
        composable(route = Screens.SuccessScreen.route) {
            SuccessScreen(navController = navController)
        }
        composable(route = Screens.HistoryOrderScreen.route) {
            HistoryOrderScreen(navController = navController,signInViewModel)
        }
        composable(route = Screens.ShippingAddressScreen.route) {
            ShippingAddressScreen(navController = navController,signInViewModel = signInViewModel)
        }
        composable(route = Screens.AddShippingAddressScreen.route) {
            AddShippingAddressScreen(navController = navController, signInViewModel = signInViewModel)
        }
        composable(route = Screens.SearchProductScreen.route) {
            SearchProductScreen(navController = navController, homeViewModel = homeViewModel)
        }

    }

}