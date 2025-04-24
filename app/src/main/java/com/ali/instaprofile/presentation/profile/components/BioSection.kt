package com.ali.instaprofile.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun BioSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Asharali V U",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = "Android Developer",
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = "Creating awesome content daily 📱✨\nExploring the world one post at a time\nwww.asharali.com",
            fontSize = 14.sp,
            lineHeight = 18.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}