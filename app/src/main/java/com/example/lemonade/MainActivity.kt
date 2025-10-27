package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonTreeTap(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonTreeTap(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(1) }
    var numberOfSqueezed by remember { mutableIntStateOf(0) }
    var randomNumberOfSqueeze by remember { mutableIntStateOf(0) }

    val imageResource = when (state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val imageContentDescriptionResource = when (state) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_lemonade_content_description
        4 -> R.string.empty_glass_content_description
        else -> R.string.lemon_tree_content_description
    }

    val textResource = when (state) {
        1 -> R.string.tap_lemon_tree_text
        2 -> R.string.tap_lemon_text
        3 -> R.string.tap_lemonade_text
        4 -> R.string.tap_glass_text
        else -> R.string.tap_lemon_tree_text
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE9AB17))
            .padding(0.dp, 20.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
        )
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                when(state){
                    4 -> {state = 1}
                    2 -> {
                        if(randomNumberOfSqueeze == 0) {
                            randomNumberOfSqueeze = (2..4).random()
                        }
                        numberOfSqueezed++
                        if (numberOfSqueezed >= randomNumberOfSqueeze) {
                            numberOfSqueezed = 0
                            randomNumberOfSqueeze = 0
                            state++
                        }
                    }
                    else -> {state++}
                }
            },
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(30.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF88E788)
            ),
            shape = RoundedCornerShape(30.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageContentDescriptionResource),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
    }
}