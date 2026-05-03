package com.dcbrh.pisogecko.presentation.details

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.dcbrh.pisogecko.R
import com.dcbrh.pisogecko.domain.models.CryptoCurrencyDetails
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    onClickClose: () -> Unit,
    id: String,
) {
    val TAG = "DetailsScreen"

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCurrency(id)
    }

    Scaffold(
        topBar = {
            DetailsTopBar(
                onClickClose = onClickClose
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is DetailsUiState.Success -> DetailsContent(currency = state.currency)

                is DetailsUiState.Loading -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }

                is DetailsUiState.Error -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(state.error)
                        Button(onClick = { viewModel.getCurrency(id) }) { Text("Retry") }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsContent(
    currency: CryptoCurrencyDetails,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currency.image.small)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.currency_image) + currency.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(48.dp),
            )
            Spacer(modifier = modifier.width(8.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(currency.name)
                    VerticalDivider(
                        modifier = modifier
                            .width(2.dp)
                            .height(16.dp)
                    )
                    Text(currency.symbol)
                }
                Row {
                    Text("Rank: ${currency.rank}")
                    Spacer(modifier = modifier.width(8.dp))
                    Text("$${currency.marketPrices.prices["usd"]}")
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(currency.description.en)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onClickClose: () -> Unit
) {
    TopAppBar(
        title = { Text("Details") },
        actions = {
            IconButton(
                onClick = onClickClose
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = stringResource(R.string.button_close),
                )
            }
        }
    )
}
