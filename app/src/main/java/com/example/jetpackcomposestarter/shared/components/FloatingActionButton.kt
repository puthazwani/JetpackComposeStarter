package com.example.jetpackcomposestarter.shared.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MultiFabItem(
    val icon: Int,
    val label: String,
)

sealed class MultiFabState {

    object Collapsed : MultiFabState()
    object Expanded : MultiFabState()

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expanded
    }

    fun isExpanded() = this == Expanded
}

@Composable
fun rememberMultiFabState() = remember { mutableStateOf<MultiFabState>(MultiFabState.Collapsed) }

@Immutable
interface FabIcon {

    @Stable
    val iconRes: Int

    @Stable
    val iconResAfterRotate: Int

    @Stable
    val iconRotate: Float?
}

private class FabIconImpl(
    override val iconRes: Int,
    override val iconResAfterRotate: Int,
    override val iconRotate: Float?
) : FabIcon

fun FabIcon(
    @DrawableRes iconRes: Int,
    @DrawableRes iconResAfterRotate: Int,
    iconRotate: Float? = null
): FabIcon =
    FabIconImpl(iconRes = iconRes, iconResAfterRotate = iconResAfterRotate, iconRotate = iconRotate)

@Immutable
interface FabOption {
    @Stable
    val iconTint: Color

    @Stable
    val backgroundTint: Color

    @Stable
    val showLabels: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabels: Boolean
) : FabOption

@Composable
fun FabOption(
    backgroundTint: Color = MaterialTheme.colorScheme.secondary,
    iconTint: Color = contentColorFor(backgroundTint),
    showLabels: Boolean = false
): FabOption =
    FabOptionImpl(iconTint = iconTint, backgroundTint = backgroundTint, showLabels = showLabels)

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    showLabel: Boolean,
    miniFabColor: Color,
    miniFabBackgroundColor: Color,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        if (showLabel) {
            Text(
                item.label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        FloatingActionButton(
            modifier = Modifier.size(64.dp),
            onClick = { onFabItemClicked(item) },
            backgroundColor = miniFabBackgroundColor
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = "multifab ${item.label}",
                tint = miniFabColor,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Composable
fun MultiFloatingActionButton(
    fabIcon: FabIcon,
    fabTitle: String?,
    showFabTitle: Boolean,
    modifier: Modifier = Modifier,
    itemsMultiFab: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabOption: FabOption = FabOption(),
    miniFabOption: FabOption = fabOption,
    onFabItemClicked: (fabItem: MultiFabItem) -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expanded) fabIcon.iconRotate ?: 0f else 0f
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End,
    ) {
        AnimatedVisibility(
            visible = fabState.value == MultiFabState.Expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                itemsIndexed(itemsMultiFab) { _, item ->
                    MiniFabItem(
                        item = item,
                        showLabel = miniFabOption.showLabels,
                        miniFabColor = miniFabOption.iconTint,
                        miniFabBackgroundColor = miniFabOption.backgroundTint,
                        onFabItemClicked = { onFabItemClicked(item) })
                }

                item {}
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (fabState.value.isExpanded() && showFabTitle)
                Text(text = fabTitle!!, modifier = Modifier.padding(end = 16.dp), style = MaterialTheme.typography.titleMedium)
            FloatingActionButton(
                onClick = {
                    fabState.value = fabState.value.toggleValue()
                    stateChanged(fabState.value)
                },
                backgroundColor = fabOption.backgroundTint,
                contentColor = fabOption.iconTint,
                modifier = Modifier.size(90.dp)
            ) {
                Icon(
                    painter = if (fabState.value.isExpanded())
                        painterResource(fabIcon.iconResAfterRotate)
                    else
                        painterResource(fabIcon.iconRes),
                    modifier = Modifier.rotate(rotation).size(54.dp),
                    contentDescription = null,
                    tint = fabOption.iconTint
                )
            }
        }
    }
}