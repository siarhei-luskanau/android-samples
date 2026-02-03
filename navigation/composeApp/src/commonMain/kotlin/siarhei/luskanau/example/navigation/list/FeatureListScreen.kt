package siarhei.luskanau.example.navigation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FeatureListScreen(viewModelProvider: () -> FeatureListViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    FeatureListContent(
        viewStateFlow = viewModel.viewState,
        onItemClick = { item ->
            viewModel.onItemClick(item)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureListContent(
    viewStateFlow: StateFlow<FeatureListViewState>,
    onItemClick: (String) -> Unit
) {
    val viewState = viewStateFlow.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Feature List") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewState.value.items) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onItemClick(item) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeatureListContentPreview() {
    FeatureListContent(
        viewStateFlow = MutableStateFlow(
            FeatureListViewState(items = listOf("Item 1", "Item 2", "Item 3"))
        ),
        onItemClick = {}
    )
}
