package com.ali.instaprofile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = { }, modifier = Modifier.weight(1f).defaultMinSize(minHeight = 1.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            ), contentPadding = PaddingValues(vertical = 6.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Edit profile", fontWeight = FontWeight.Bold, fontSize = 14.sp
            )
        }

        OutlinedButton(
            onClick = { }, modifier = Modifier.weight(1f).defaultMinSize(minHeight = 1.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            ), contentPadding = PaddingValues(vertical = 6.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Share profile", fontWeight = FontWeight.Bold, fontSize = 14.sp
            )
        }

        OutlinedButton(
            onClick = { }, modifier = Modifier.weight(0.25f).defaultMinSize(minHeight = 1.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            ), contentPadding = PaddingValues(vertical = 6.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Suggestions",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}