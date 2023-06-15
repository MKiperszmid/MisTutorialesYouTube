package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun HomeScreenListado(
    viewModel: HomeViewModel,
) {
    val state = viewModel.state
    val refreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.aumentarValor() },
    )

    val elements = (1..400).map { "Item $it" }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().pullRefresh(refreshState)) {
            items(elements) {
                Text(text = it)
            }
        }
        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = refreshState,
            modifier = Modifier.align(
                Alignment.TopCenter,
            ),
        )
    }
}
