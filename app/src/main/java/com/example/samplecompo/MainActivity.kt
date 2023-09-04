package com.example.samplecompo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.samplecompo.ui.theme.SampleCompoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleCompoTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    var value by remember { mutableStateOf(0) }
                    customComponent(indicatorValue = value)
                    TextField(
                        value = value.toString(), onValueChange = {
                            value = if (it.isNotEmpty()) {
                               it.toInt()
                           }else{
                               0
                           }


                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ComponentPreview() {
    SampleCompoTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            var value by remember { mutableStateOf(0) }
            customComponent(indicatorValue = value)
            TextField(
                value = value.toString(), onValueChange = {
                    value = if (it.isNotEmpty()) {
                        it.toInt()
                    }else{
                        0
                    }


                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(text = "Hi")
        }

    }
}

