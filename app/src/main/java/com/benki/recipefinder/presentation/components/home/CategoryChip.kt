package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.benki.recipefinder.network.models.lists.ListByMealCategory

@Composable
fun CategoryChip(
    modifier: Modifier = Modifier,
    category: ListByMealCategory,
    selected: Boolean = false,
    onClick: (ListByMealCategory) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(category) },
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            category.strCategory?.let { categoryName ->
                AsyncImage(
                    model = category.strCategoryThumb,
                    contentDescription = category.strCategory,
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(text = categoryName, fontSize = 14.sp)
            }
        }
    }
}