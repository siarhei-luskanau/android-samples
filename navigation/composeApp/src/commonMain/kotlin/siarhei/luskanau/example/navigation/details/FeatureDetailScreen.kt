package siarhei.luskanau.example.navigation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FeatureDetailScreen(viewModelProvider: () -> FeatureDetailViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    FeatureDetailContent(
        viewStateFlow = viewModel.viewState,
        onBackClick = { viewModel.onBackClick() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeatureDetailContent(
    viewStateFlow: StateFlow<FeatureDetailViewState>,
    onBackClick: () -> Unit
) {
    val viewState = viewStateFlow.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feature Detail") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Feature Detail",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Selected Item: ${viewState.value.featureId}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "This is the detail screen for the selected feature item.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back to List")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeatureDetailContentPreview() {
    FeatureDetailContent(
        viewStateFlow = MutableStateFlow(
            FeatureDetailViewState(featureId = "Sample Item")
        ),
        onBackClick = {}
    )
}
