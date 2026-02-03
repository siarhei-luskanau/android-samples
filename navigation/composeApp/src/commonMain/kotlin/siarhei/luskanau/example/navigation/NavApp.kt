package siarhei.luskanau.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.example.navigation.details.FeatureDetailScreen
import siarhei.luskanau.example.navigation.list.FeatureListScreen

@Composable
fun NavApp() {
    val koin = getKoin()
    val navController = rememberNavController()
    val appNavigation = AppNavigation(navHostController = navController)
    NavHost(navController = navController, startDestination = AppRoutes.FeatureList) {
        composable<AppRoutes.FeatureList> {
            FeatureListScreen(
                viewModelProvider = { koin.get { parametersOf(appNavigation) } }
            )
        }
        composable<AppRoutes.FeatureDetail> {
            val args: AppRoutes.FeatureDetail = it.toRoute()
            FeatureDetailScreen(
                viewModelProvider = { koin.get { parametersOf(args.featureId, appNavigation) } }
            )
        }
    }
}

internal sealed interface AppRoutes {
    @Serializable
    object FeatureList : AppRoutes

    @Serializable
    data class FeatureDetail(val featureId: String) : AppRoutes
}
