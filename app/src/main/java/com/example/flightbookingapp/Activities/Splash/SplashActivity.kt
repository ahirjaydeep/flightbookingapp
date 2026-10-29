package com.example.flightbookingapp.Activities.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.example.flightbookingapp.Activities.Dashboard.DashboardActivity
import com.example.flightbookingapp.MainActivity
import com.example.flightbookingapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent{
            SplashScreen{
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetStartedClick: () -> Unit = {}) {

    StatusTopBarColor()

    // Use Box instead of Column to allow full screen background
    Box(modifier = Modifier.fillMaxSize()) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (backgroundImg, content) = createRefs()

            // Background image takes full screen
            Image(
                painter = painterResource(R.drawable.splash_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop, // This ensures the image covers the entire screen
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize()
            )

            // Content with proper system bars padding
            Column(
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize()
                    .statusBarsPadding() // Add padding for status bar
                    .navigationBarsPadding() // Add padding for navigation bar
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                val styledText = buildAnnotatedString {
                    append("Discover your\nDream ")
                    withStyle(style = SpanStyle(color = colorResource(R.color.orange))) {
                        append("Flight")
                    }
                    append("\nEasily")
                }

                Text(
                    text = styledText,
                    fontSize = 53.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.subtitle_splash),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Bottom button
                GradiantButton(onGetStartedClick, "Get Started", 32)

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun StatusTopBarColor() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}