package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.toLastViewed

@Composable
fun MealsByMainIngredientsItem(
    modifier: Modifier = Modifier,
    mealByMainIngredient: FilterByMainIngredient,
    navigateToDetails: (String) -> Unit,
    addToLastViewed: (LastViewed) -> Unit,
    addToSaved: (FilterByMainIngredient) -> Unit
) {
    Card(
        modifier = modifier
            .width(240.dp)
            .height(300.dp)
            .padding(horizontal = 8.dp)
            .clickable {
                mealByMainIngredient.idMeal?.let {
                    addToLastViewed(mealByMainIngredient.toLastViewed())
                    navigateToDetails(it)
                }
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        mealByMainIngredient.strMeal?.let { mealName ->
            Box(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .width(240.dp)
                ) {
                    AsyncImage(
                        model = mealByMainIngredient.strMealThumb,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(240.dp)
                            .height(260.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = mealName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                IconButton(
                    onClick = { addToSaved(mealByMainIngredient) },
                    colors = IconButtonDefaults.outlinedIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = if (mealByMainIngredient.saved) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.align(Alignment.TopEnd),
                ) {
                    Icon(
                        imageVector = if (mealByMainIngredient.saved) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "bookmark"
                    )
                }
            }
        }
    }
}