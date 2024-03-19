package com.benki.recipefinder.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.benki.recipefinder.presentation.viewmodels.DetailScreenViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    mealId: String,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val mealResponse by viewModel.mealResponse.collectAsStateWithLifecycle()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = if (mealResponse.loading) Arrangement.Center else Arrangement.Top,
                horizontalAlignment = if (mealResponse.loading) Alignment.CenterHorizontally else Alignment.Start
            ) {
                if (mealResponse.loading) {
                    Box(modifier = modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else if (!mealResponse.isSuccess) {
                    Box(modifier = modifier.fillMaxSize()) {
                        mealResponse.errorMessage?.let {
                            Text(
                                text = it, modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                } else {
                    if (mealResponse.data.meals.isNullOrEmpty()) {
                        Box(modifier = modifier.fillMaxSize()) {
                            mealResponse.errorMessage?.let {
                                Text(
                                    text = it, modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    } else {
                        val meal = mealResponse.data.meals!!.first()
                        AsyncImage(
                            model = meal.strMealThumb,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            meal.strMeal?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.primary

                                )
                            }
                            meal.strArea?.let {
                                Text(
                                    text = "$it Recipe",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Instructions",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            meal.strInstructions?.let {
                                val instructions = it.split("\r\n")
                                instructions.forEachIndexed() { index, instruction ->
                                    Text(text = "${index + 1}. $instruction")
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Ingredients",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            val ingredients = listOf(
                                meal.strIngredient1,
                                meal.strIngredient2,
                                meal.strIngredient3,
                                meal.strIngredient4,
                                meal.strIngredient5,
                                meal.strIngredient6,
                                meal.strIngredient7,
                                meal.strIngredient8,
                                meal.strIngredient9,
                                meal.strIngredient10,
                                meal.strIngredient11,
                                meal.strIngredient12,
                                meal.strIngredient13,
                                meal.strIngredient14,
                                meal.strIngredient15,
                                meal.strIngredient16,
                                meal.strIngredient17,
                                meal.strIngredient18,
                                meal.strIngredient19,
                                meal.strIngredient20,
                            )

                            val measures = listOf(
                                meal.strMeasure1,
                                meal.strMeasure2,
                                meal.strMeasure3,
                                meal.strMeasure4,
                                meal.strMeasure5,
                                meal.strMeasure6,
                                meal.strMeasure7,
                                meal.strMeasure8,
                                meal.strMeasure9,
                                meal.strMeasure10,
                                meal.strMeasure11,
                                meal.strMeasure12,
                                meal.strMeasure13,
                                meal.strMeasure14,
                                meal.strMeasure15,
                                meal.strMeasure16,
                                meal.strMeasure17,
                                meal.strMeasure18,
                                meal.strMeasure19,
                                meal.strMeasure20,
                            )

                            val ingredientsWithMeasures = ingredients.zip(measures)

                            ingredientsWithMeasures.forEach {
                                if (!it.first.isNullOrEmpty()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = it.first!!)
                                        Text(text = it.second!!)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Tags",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .background(
                                            MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(8.dp)
                                )
                                Row(modifier = modifier.fillMaxWidth()) {
                                    meal.strTags?.let {
                                        val tags: List<String> = it.split(",")
                                        tags.forEach { tag ->
                                            Card(
                                                colors = CardDefaults.cardColors(
                                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                                                ),
                                                modifier = Modifier.padding(8.dp),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(text = tag, modifier = Modifier.padding(8.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
            IconButton(
                onClick = navigateBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                colors = IconButtonDefaults.filledIconButtonColors()
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
            }
            if (mealResponse.isSuccess) {
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(0.4f)
                        .clickable {
                            if (!mealResponse.data.meals.isNullOrEmpty()) {
                                val uri = mealResponse.data.meals!!.first().strYoutube
                                uri?.let {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.setData(Uri.parse(it))
                                    context.startActivity(intent)
                                }
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.VideoCall, contentDescription = null)
                        Text(text = "Watch Video", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetails(mealId)
    }
}