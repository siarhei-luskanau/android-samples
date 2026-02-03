package siarhei.luskanau.example.navigation.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import siarhei.luskanau.example.navigation.AppNavigation

class FeatureListViewModel(private val appNavigation: AppNavigation) : ViewModel() {

    val viewState: StateFlow<FeatureListViewState> =
        MutableStateFlow(
            FeatureListViewState(
                items = listOf(
                    "Feature Item 1",
                    "Feature Item 2",
                    "Feature Item 3",
                    "Feature Item 4",
                    "Feature Item 5"
                )
            )
        )

    fun onItemClick(item: String) {
        appNavigation.onItemClick(item = item)
    }
}
