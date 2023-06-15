package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val state = viewModel.state
    val refreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.aumentarValor() },
    )
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize().pullRefresh(refreshState).verticalScroll(
            scrollState,
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Valor Actual: ${state.currentValue}")
        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = refreshState,
            modifier = Modifier.align(
                Alignment.TopCenter,
            ),
        )
    }
}
