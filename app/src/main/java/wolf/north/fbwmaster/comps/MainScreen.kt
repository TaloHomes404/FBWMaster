package wolf.north.fbwmaster.comps

import CurrentData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwmaster.R
import wolf.north.fbwmaster.comps.ui.theme.FBWMasterTheme
import wolf.north.fbwmaster.navigation.NavigationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    planName: String? = null,
    duration: String? = null,
    imageResId: Int? = null
) {

    Scaffold(topBar = {
        Column {
            TopAppBar(title = {
                Column {
                    CurrentData()
                    Text(
                        text = stringResource(R.string.w1_d1_beginner), color = Color.Gray
                    )
                }
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Calendar"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.randomperson), // Placeholder na zdjęcie użytkownika
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
            // Dodanie cienkiej szarej kreski na końcu TopAppBar
            Spacer(modifier = Modifier.height(4.dp))
            Divider(
                modifier = Modifier.alpha(0.3f), color = Color.LightGray, // Kolor kreski
                thickness = 2.dp // Grubość kreski
            )
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues) // Tło strony
                .padding(16.dp)
        ) {
            // Nagłówek sekcji "Workouts"
            Text(
                text = "Workouts",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Karta
            WorkoutCard(
                imageResId = imageResId ?: R.drawable.arms_fbw1, // Zdjęcie treningu
                title = planName ?: "Full Body Mobility Routine",
                duration =  duration ?: "36 mins"
            )
        }
    }, bottomBar = {
        BottomAppBar(
            containerColor = Color.Black, // Ustawienie czarnego tła BottomBar
            contentColor = Color.White // Kolor ikon
        ) {
            // Równomierne rozłożenie ikon w BottomBar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Równomierne rozmieszczenie ikon
            ) {
                IconButton(onClick = { navController.navigate("mainScreen") }) {
                    Icon(
                        imageVector = Icons.Default.Home, // Zmień na dowolną ikonę
                        contentDescription = "Home", tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("plansListScreen") }) {
                    Icon(
                        imageVector = Icons.Default.FormatListNumbered, // Zmień na dowolną ikonę
                        contentDescription = "Exercises list bottom bar", tint = Color.Gray
                    )
                }
                IconButton(onClick = { navController.navigate("exercisesList") }) {
                    Icon(
                        imageVector = Icons.Default.Search, // Zmień na dowolną ikonę
                        contentDescription = "Search", tint = Color.Gray
                    )
                }
                IconButton(onClick = { navController.navigate("toolsScreen") }) {
                    Icon(
                        imageVector = Icons.Default.Calculate, // Zmień na dowolną ikonę
                        contentDescription = "Tools", tint = Color.Gray
                    )
                }
            }
        }
    })
}

@Composable
fun WorkoutCard(imageResId: Int, title: String, duration: String) {
    Card(
        shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            // Tło ze zdjęciem
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )

            // "Dymek" w prawym górnym rogu
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Selected",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }

            // Tekst na dole zdjęcia
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                        )
                    )
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = duration,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.7f, // Wartość domyślna do zastąpienia
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = Color.Magenta // Tymczasowy kolor
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}