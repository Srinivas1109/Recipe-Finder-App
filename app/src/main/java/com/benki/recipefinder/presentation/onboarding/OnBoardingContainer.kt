package com.benki.recipefinder.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benki.recipefinder.presentation.components.IndicatorContainer
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingContainer(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {
        3
    }
    val coroutineScope = rememberCoroutineScope()
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> OnBoardingScreen1()
                    1 -> OnBoardingScreen2()
                    2 -> OnBoardingScreen3()
                }
            }
            AnimatedVisibility(
                visible = pagerState.currentPage < 2, modifier = modifier.align(Alignment.TopEnd)
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(2)
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    ), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Skip", fontSize = 14.sp)
                }
            }
            Column(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val buttonWidth by animateFloatAsState(
                    targetValue = if (pagerState.currentPage < 2) 0.3f else 1f,
                    label = "Animate button width",
                    animationSpec = tween(1000)
                )
                IndicatorContainer(currentPage = pagerState.currentPage) { pageNumber ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pageNumber, animationSpec = tween(1000))
                    }
                }
                Spacer(modifier = modifier.height(16.dp))
                Box(modifier = modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1,
                                    animationSpec = tween(1000)
                                )
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = modifier
                            .align(Alignment.BottomEnd)
                            .fillMaxWidth(buttonWidth)
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = if (pagerState.currentPage < 2) "Next" else "Get Started",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingContainerPreview() {
    OnBoardingContainer()
}