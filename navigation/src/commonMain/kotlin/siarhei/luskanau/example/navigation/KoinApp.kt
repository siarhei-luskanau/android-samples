package siarhei.luskanau.example.navigation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module
import siarhei.luskanau.example.navigation.details.FeatureDetailViewModel
import siarhei.luskanau.example.navigation.list.FeatureListViewModel

@Preview
@Composable
fun KoinApp() = KoinMultiplatformApplication(
    config = KoinConfiguration {
        modules(
            module {
                factory { FeatureListViewModel(appNavigation = it[0]) }
                factory { FeatureDetailViewModel(featureId = it[0], appNavigation = it[1]) }
            }
        )
    }
) {
    NavApp()
}
