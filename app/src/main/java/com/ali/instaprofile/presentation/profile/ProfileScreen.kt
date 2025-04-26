package com.ali.instaprofile.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ali.instaprofile.presentation.profile.components.ActionButtons
import com.ali.instaprofile.presentation.profile.components.BioSection
import com.ali.instaprofile.presentation.profile.components.PostsGrid
import com.ali.instaprofile.presentation.profile.components.ProfileInfo
import com.ali.instaprofile.presentation.profile.components.ProfileTabs
import com.ali.instaprofile.presentation.profile.components.ReelsGrid
import com.ali.instaprofile.presentation.profile.components.StoryHighlights
import com.ali.instaprofile.presentation.profile.components.TaggedGrid
import com.ali.instaprofile.presentation.profile.components.TopAppBar
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Starting of Profile screen
 * Contains all the scrolling and sticky behaviour of the UI
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val lazyListState = rememberLazyListState()
    val childState = rememberLazyGridState()
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val toolbarHeight = 56.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    // Used to manage the parent scrolling ad grid scrolling
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // For toolbar
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-toolbarHeightPx, 0f)

                // For parent and child
                val consumed = lazyListState.dispatchRawDelta(-available.y)
                return Offset(0f, -consumed)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val parentConsumed = lazyListState.dispatchRawDelta(-available.y)
                return Offset(0f, -parentConsumed)
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // All contents are added from here
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .nestedScroll(nestedScrollConnection)
            ) {
                // Added space for the top bar
                item { Spacer(modifier = Modifier.height(56.dp)) }

                item { ProfileInfo() }

                item { BioSection() }

                item { ActionButtons() }

                item { StoryHighlights() }

                item {
                    ProfileTabs(
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { index ->
                            selectedTabIndex = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }

                item {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) { page ->
                        when (page) {
                            0 -> PostsGrid(childState, nestedScrollConnection)
                            1 -> ReelsGrid(childState, nestedScrollConnection)
                            2 -> TaggedGrid(childState, nestedScrollConnection)
                        }
                    }
                }
            }
        }
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    TopAppBar(
        modifier = Modifier
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.floatValue.roundToInt()) }
            .fillMaxWidth()
            .height(toolbarHeight)
            .background(Color.White)
    )
}
