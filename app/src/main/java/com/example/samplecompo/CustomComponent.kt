package com.example.samplecompo


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun customComponent(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 100f,
    bigTextFontSize: TextUnit = MaterialTheme.typography.headlineLarge.fontSize,
    bigTextColor: Color = MaterialTheme.colorScheme.onSurface,
    bigTextSuffix: String = "GB",
    smallText: String = "Remaining",
    smallTextSize: TextUnit = MaterialTheme.typography.headlineSmall.fontSize,
    smallTextColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
) {

    var animatedIndicatorValue by remember {
        mutableStateOf(0f)
    }
    var allowedIndicatorValue by remember {
        mutableStateOf(
            maxIndicatorValue
        )
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue)
        indicatorValue
    else
        maxIndicatorValue



    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()

    }
    val percentage = ((animatedIndicatorValue) / maxIndicatorValue) * 100
    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )
    val amimatexdBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f) else bigTextColor,
        animationSpec = tween(1000)
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {

                val componentSize = size / 1.25f

                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                    indicatorStrokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    foregroundStrokeWidth = foregroundIndicatorStrokeWidth,
                    indicatorStrokeCap = indicatorStrokeCap
                )
            }
    ) {
        EmbeddedElements(
            bigText = allowedIndicatorValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = amimatexdBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextSize = smallTextSize)
    }
}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextSize: TextUnit,

    ) {

    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextSize,
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}


fun DrawScope.backgroundIndicator(
    indicatorStrokeCap: StrokeCap,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        )
    )
}


fun DrawScope.foregroundIndicator(
    indicatorStrokeCap: StrokeCap,
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    foregroundStrokeWidth: Float

) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = foregroundStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        )


    )
}

@Preview(showBackground = true)
@Composable
fun customComponentPreview() {
    customComponent()
}