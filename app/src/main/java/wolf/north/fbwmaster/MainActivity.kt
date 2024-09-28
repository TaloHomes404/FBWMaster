package wolf.north.fbwmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import wolf.north.fbwmaster.ui.theme.FBWMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FBWMasterTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun EntryScreen(modifier: Modifier = Modifier) {
    // Lista zdjęć
    val images = listOf(
        R.drawable.first_mainscreen_img,
        R.drawable.strong_woman_mainscreen_imgbw,
        R.drawable.goalphis_mainscreen_bwimg
    )

    // Lista tytułów z zasobów
    val titles = listOf(
        stringResource(R.string.first_slide_title),
        stringResource(R.string.second_slide_title),
        stringResource(R.string.third_slide_title)
    )

    // Lista opisów z zasobów
    val descriptions = listOf(
        stringResource(R.string.first_slide_description),
        stringResource(R.string.second_slide_description),
        stringResource(R.string.third_slide_description)
    )

    var currentSlide by remember { mutableStateOf(0) }

    // Obsługa automatycznej zmiany slajdów co 6 sekund
    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            currentSlide = (currentSlide + 1) % images.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.main_screen_bg))
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Crossfade dla obrazka
        Crossfade(
            targetState = currentSlide,
            animationSpec = tween(durationMillis = 1000) // Czas trwania crossfade
        ) { selectedSlide ->
            Image(
                painter = painterResource(id = images[selectedSlide]),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
                    .align(Alignment.End)
                    .offset(y = 25.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Zaokrąglona karta z tekstem, przyciskiem i kulkami
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.main_card_bg),
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Trzy kulki
            DotsIndicator(selectedIndex = currentSlide, numberOfDots = images.size)

            Spacer(modifier = Modifier.height(16.dp))

            // Tytuł
            Text(
                text = titles[currentSlide],
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Opis
            Text(
                text = descriptions[currentSlide],
                fontSize = 18.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Action */ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.black)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .height(50.dp)
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Start now",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.ArrowRight,
                        contentDescription = "Register Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun DotsIndicator(selectedIndex: Int, numberOfDots: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        for (index in 0 until numberOfDots) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = if (index == selectedIndex) Color.Black else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewEntryScreen() {
    EntryScreen()
}