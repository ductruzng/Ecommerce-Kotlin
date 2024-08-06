package trungndph39729.fpoly.assignment.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object HomeScreen : Screens(route = "Home_Screen")
    object DetailScreen : Screens(route = "Detail_Screen")
    object CartScreen : Screens(route = "Cart_Screen")
    object ProfileScreen : Screens(route = "Profile_Screen")
    object IntroScreen : Screens(route = "Intro_Screen")
    object NavBar : Screens(route = "NavBar")
    object CheckOutScreen : Screens(route = "CheckOut_Screen")
    object FavoriteScreen : Screens(route = "Favorite_Screen")
    object NotificationScreen : Screens(route = "Notification_Screen")
    object SuccessScreen : Screens(route = "Success_Screen")
    object HistoryOrderScreen : Screens(route = "HistoryOrder_Screen")
    object ShippingAddressScreen : Screens(route = "ShippingAddress_Screen")
    object AddShippingAddressScreen : Screens(route = "AddShippingAddress_Screen")
    object SearchProductScreen : Screens(route = "SearchProduct_Screen")
}