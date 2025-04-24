package com.ali.instaprofile.presentation.profile

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {

    val lazyListState = rememberLazyListState()

    val isScrollingUp by rememberIsScrollingUp(lazyListState)

    val topBarHeightPx = with(LocalDensity.current) { 56.dp.toPx() }
    val topBarOffsetHeightPx = remember { Animatable(-topBarHeightPx) }

    LaunchedEffect(isScrollingUp) {
        topBarOffsetHeightPx.animateTo(
            targetValue = if (isScrollingUp) 0f else -topBarHeightPx,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item { Spacer(modifier = Modifier.height(56.dp)) }
                items(100) {
                    Text("Item $it")
                }

            }
        }
    }
    TopAppBar(
        modifier = Modifier
            .offset { IntOffset(0, topBarOffsetHeightPx.value.toInt()) }
            .fillMaxWidth()
            .background(Color.White)
    )
}

@Preview
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "username123",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                modifier = Modifier.size(20.dp)
            )
        }

        Row {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(24.dp)
                    .clickable {  }
            )
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }
    }
}

@Composable
fun rememberIsScrollingUp(lazyListState: LazyListState): State<Boolean> {
    val previousScrollOffset = remember { mutableStateOf(0) }
    val previousItemIndex = remember { mutableStateOf(0) }
    val hasScrolled = remember { mutableStateOf(false) }

    return remember {
        derivedStateOf {
            val currIndex = lazyListState.firstVisibleItemIndex
            val currOffset = lazyListState.firstVisibleItemScrollOffset

            val isUp = when {
                currIndex < previousItemIndex.value -> true
                currIndex > previousItemIndex.value -> {
                    hasScrolled.value = true
                    false
                }

                else -> {
                    val scrollingUp = currOffset < previousScrollOffset.value
                    if (currOffset != previousScrollOffset.value) hasScrolled.value = true
                    scrollingUp
                }
            }

            previousItemIndex.value = currIndex
            previousScrollOffset.value = currOffset

            if (!hasScrolled.value) true else isUp
        }
    }
}