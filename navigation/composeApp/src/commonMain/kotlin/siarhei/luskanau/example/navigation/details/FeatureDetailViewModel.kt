package siarhei.luskanau.example.navigation.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import siarhei.luskanau.example.navigation.AppNavigation

class FeatureDetailViewModel(featureId: String, private val appNavigation: AppNavigation) :
    ViewModel() {
    val viewState: StateFlow<FeatureDetailViewState> =
        MutableStateFlow(FeatureDetailViewState(featureId = featureId))

    fun onBackClick() {
        appNavigation.onBackClick()
    }
}
