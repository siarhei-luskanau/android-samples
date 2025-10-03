package siarhei.luskanau.example.navigation

import androidx.navigation.NavHostController

class AppNavigation(private val navHostController: NavHostController) {
    fun onBackClick() {
        navHostController.popBackStack()
    }

    fun onItemClick(item: String) {
        navHostController.navigate(AppRoutes.FeatureDetail(item))
    }
}
