package com.ali.instaprofile.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostsGrid(childState: LazyGridState, nestedScrollConnection: NestedScrollConnection) {
    LazyVerticalGrid(
        state = childState,
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxWidth()
            .height(900.dp), // Set a fixed height that's large enough to be scrollable
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(30) { index ->
            GridPostItem(index, type = "post")
        }
    }
}

@Composable
fun ReelsGrid(childState: LazyGridState, nestedScrollConnection: NestedScrollConnection) {
    LazyVerticalGrid(
        state = childState,
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxWidth()
            .height(1300.dp),
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(24) { index ->
            GridPostItem(index, type = "reel")
        }
    }
}

@Composable
fun TaggedGrid(childState: LazyGridState, nestedScrollConnection: NestedScrollConnection) {
    LazyVerticalGrid(
        state = childState,
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxWidth()
            .height(900.dp),
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(18) { index ->
            GridPostItem(index, type = "tagged")
        }
    }
}

@Composable
fun GridPostItem(index: Int, type: String) {
    Box(
        modifier = Modifier
            .aspectRatio(if(type == "reel") .8f else 1f)
            .background(
                when (type) {
                    "post" -> Color.LightGray.copy(alpha = 0.7f)
                    "reel" -> Color.Gray.copy(alpha = 0.5f)
                    else -> Color.DarkGray.copy(alpha = 0.3f)
                }
            )
            .clickable { /* Item click action */ }
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )

            Text(
                text = when (type) {
                    "post" -> "Post"
                    "reel" -> "Reel"
                    else -> "Tagged"
                },
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 40.dp)
            )
        }

        when (type) {
            "reel" -> {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Video",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(16.dp),
                    tint = Color.White
                )
            }

            "post" -> {
                if (index % 7 == 0) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Multiple Images",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .size(16.dp),
                        tint = Color.White
                    )
                }
            }

            "tagged" -> {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Tagged",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                        .size(16.dp),
                    tint = Color.White
                )
            }
        }
    }
}
