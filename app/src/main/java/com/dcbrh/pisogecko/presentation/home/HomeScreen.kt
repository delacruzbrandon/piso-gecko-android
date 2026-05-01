package com.dcbrh.pisogecko.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.dcbrh.pisogecko.domain.models.CryptoCurrency
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var listPage by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit, listPage) {
        viewModel.getCurrencies(listPage)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                when (val state = uiState) {
                    is HomeUiState.Loading -> Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }

                    is HomeUiState.Success -> CurrencyList(
                        currencies = state.currencies,
                        modifier = Modifier
                            .padding(innerPadding)
                    )

                    is HomeUiState.Error -> Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(state.error)
                            Button(onClick = { viewModel.getCurrencies(listPage) }) { Text("Retry") }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { if (listPage > 1) listPage-- }
                ) { Text("Previous") }
                Button(
                    onClick = { listPage++ }
                ) { Text("Next") }
            }
        }
    }
}

@Composable
fun CurrencyList(
    currencies: List<CryptoCurrency>,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = currencies.size,
            itemContent = { index ->
                CurrencyCard(currency = currencies[index])
            }
        )
    }
}

@Composable
fun CurrencyCard(
    currency: CryptoCurrency,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currency.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.description_currency) + currency.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(48.dp),
            )
            Column(
                modifier = Modifier
                    .padding(all = 8.dp),
            ) {
                Text(currency.symbol)
                Text("#${currency.rank} ${currency.name}")
            }
            Spacer(modifier.weight(1f))
            Text("$${currency.currentPrice}")
        }
    }
}
