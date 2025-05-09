package com.ali.instaprofile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali.instaprofile.R

@Preview
@Composable
fun ProfileInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(2.5f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable { }
            ) {
              /*  Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    tint = Color.White
                )*/
                Image(
                    painter = painterResource(id = R.drawable.im_3),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxSize().clip(CircleShape),
                    contentScale = ContentScale.Crop, // or Fit, Inside, etc. depending on your need
                )
            }
        }

        Column(
            modifier = Modifier.weight(7.5f).padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Asharali V U",
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatColumn("75", "Posts")
                StatColumn("1.5K", "Followers")
                StatColumn("1,025", "Following")
            }
        }
    }
}

@Preview
@Composable
fun StatColumn(count: String = "75", label: String = "Posts") {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.clickable { }
    ) {
        Text(
            text = count,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = label,
            fontSize = 14.sp
        )
    }
}
