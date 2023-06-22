package com.mkiperszmid.emptyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkiperszmid.emptyapp.home.HomeScreen
import com.mkiperszmid.emptyapp.home.HomeViewModel
import com.mkiperszmid.emptyapp.ui.theme.EmptyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val viewModel by viewModels<HomeViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return HomeViewModel() as T
                            }
                        }
                    })
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}
