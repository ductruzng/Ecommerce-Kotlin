package trungndph39729.fpoly.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

import dagger.hilt.android.AndroidEntryPoint
import trungndph39729.fpoly.assignment.theme.AssignmentTheme
import trungndph39729.fpoly.assignment.navigation.NavigationGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AssignmentTheme {
               NavigationGraph()
//                var navController = rememberNavController()

            }
        }
    }


}


