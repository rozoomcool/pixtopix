package com.rozoomcool.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

class NavigationState (
    val navHostController: NavHostController
) {

    fun navigatePushTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id)
        }
    }

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun pop() {
        navHostController.popBackStack()
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return getViewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//
//    return getViewModel(parentEntry)
//}