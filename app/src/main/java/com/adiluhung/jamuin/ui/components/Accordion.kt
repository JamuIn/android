package com.adiluhung.jamuin.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.model.ContentModel
import com.adiluhung.jamuin.data.model.SectionModel
import com.adiluhung.jamuin.ui.theme.DodgerBlue


@Composable
fun Accordion(itemModel: ContentModel, onClickItem: () -> Unit, expanded: Boolean) {
    Card(colors = CardDefaults.cardColors(containerColor = White)) {
        Column {
            HeaderView(
                onClickItem = onClickItem,
            )
            ExpandableView(answerText = itemModel.Sections, isExpanded = expanded)
        }
    }
}

@Composable
fun HeaderView(onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickItem
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Metode Pembayaran",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.verified),
                contentDescription = "Verified",
                modifier = Modifier.size(16.dp),
                tint = DodgerBlue
            )
        }
    }
}


@Composable
fun ExpandableView(answerText: List<SectionModel>, isExpanded: Boolean) {
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 2.dp)
        ) {
            answerText.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gopay_logo),
                        contentDescription = "payment",
                        modifier = Modifier.size(53.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }
}


@Preview
@Composable
fun AccordionPreview() {
    Accordion(
        itemModel = ContentModel(
            Id = 1,
            Title = "Gopay",
            Sections = listOf(
                SectionModel(Id = 1, Title = "Gopay", ImageResId = R.drawable.gopay_logo),
            )
        ),
        onClickItem = {},
        expanded = true
    )
}
