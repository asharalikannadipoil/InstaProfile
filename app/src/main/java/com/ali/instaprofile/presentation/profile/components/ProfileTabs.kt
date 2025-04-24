package com.ali.instaprofile.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    Column {
        Divider(color = Color.LightGray, thickness = 0.5.dp)

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TabItem(
                isSelected = selectedTabIndex == 0,
                icon = Icons.Default.Menu,
                contentDescription = "Posts",
                onClick = { onTabSelected(0) },
                modifier = Modifier.weight(1f)
            )
            TabItem(
                isSelected = selectedTabIndex == 1,
                icon = Icons.Default.PlayArrow,
                contentDescription = "Reels",
                onClick = { onTabSelected(1) },
                modifier = Modifier.weight(1f)
            )
            TabItem(
                isSelected = selectedTabIndex == 2,
                icon = Icons.Default.AccountBox,
                contentDescription = "Tagged",
                onClick = { onTabSelected(2) },
                modifier = Modifier.weight(1f)
            )
        }

        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}

@Composable
fun TabItem(
    isSelected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (isSelected) Color.Black else Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(if (isSelected) Color.Black else Color.Transparent)
        )
    }
}