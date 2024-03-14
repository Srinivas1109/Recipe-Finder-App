package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Header(
    modifier: Modifier = Modifier,
    name: String,
    hasNewNotifications: Boolean = false,
    randomImage: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = randomImage,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = "Welcome, 👋🏻",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        if (hasNewNotifications) {
            Box(modifier = Modifier.size(40.dp)) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(40.dp),
                    colors = IconButtonDefaults.outlinedIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "notifications",
                        modifier = Modifier.size(24.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color = Color.Red, shape = CircleShape)
                        .align(Alignment.TopEnd)
                )
            }
        } else {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(40.dp),
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "notifications",
                    modifier = Modifier.size(24.dp),
                )
            }
        }

    }
}

@Preview(showBackground = true, name = "No Notifications")
@Composable
fun HeaderPreviewWithNoNotifications(modifier: Modifier = Modifier) {
    Header(
        name = "Srinivas",
        randomImage = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        hasNewNotifications = false
    )
}

@Preview(showBackground = true, name = "With Notifications")
@Composable
fun HeaderPreviewWithNotifications(modifier: Modifier = Modifier) {
    Header(
        name = "Srinivas",
        randomImage = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        hasNewNotifications = true
    )
}