package wolf.north.fbwmaster.comps


import CurrentData
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwmaster.R
import wolf.north.fbwmaster.navigation.NavigationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlansListScreen(navController: NavController) {


    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            CurrentData()
                            Text(
                                text = "W3 D6 • Fit Fusion",
                                color = Color.Gray
                            )
                        }
                    },
                    actions = {
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
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
                // Dodanie cienkiej szarej kreski na końcu TopAppBar
                Divider(
                    modifier = Modifier.alpha(0.3f),
                    color = Color.LightGray, // Kolor kreski
                    thickness = 2.dp // Grubość kreski
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues) // Tło strony
                    .padding(16.dp)
            ) {
                // Nagłówek sekcji "Workouts"
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Select ready-to-go plan!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lista kart w LazyColumn
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp) // Odstęp między kartami
                ) {
                    item {
                        PlanCard(
                            imageResId = R.drawable.pushups_img, // Zdjęcie treningu
                            title = "Build PUSH Strenght",
                            duration = "35 mins", onAddPlan = {}, navController
                        )
                        PlanCard(
                            imageResId = R.drawable.arms_fbw1, // Zdjęcie treningu
                            title = "Build well defined arms",
                            duration = "40 mins", onAddPlan = {}, navController
                        )
                        PlanCard(
                            imageResId = R.drawable.abs_workot_img, // Zdjęcie treningu
                            title = "15min ABS Killer",
                            duration = "15 mins", onAddPlan = {}, navController

                        )
                    }

                }
            }
        },
        bottomBar = {
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
                            contentDescription = "Home",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { navController.navigate("plansListScreen") }) {
                        Icon(
                            imageVector = Icons.Default.FormatListNumbered, // Zmień na dowolną ikonę
                            contentDescription = "Exercises list bottom bar",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate("exercisesList") }) {
                        Icon(
                            imageVector = Icons.Default.Search, // Zmień na dowolną ikonę
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { navController.navigate("toolsScreen") }) {
                        Icon(
                            imageVector = Icons.Default.Calculate, // Zmień na dowolną ikonę
                            contentDescription = "Tools",
                            tint = Color.Gray
                        )

                    }
                }
            }
        }
    )
}

@Composable
fun PlanCard(
    imageResId: Int,
    title: String,
    duration: String,
    onAddPlan: () -> Unit,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth() // Karta wypełnia pełną szerokość
            .wrapContentHeight()
            .padding(2.dp) // Ustalony rozmiar dla kart
    ) {
        Box {
            // Tło ze zdjęciem
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .alpha(0.8f),
                contentScale = ContentScale.FillBounds
            )

            // "Dymek" w prawym górnym rogu
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Free",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }

            // Tekst na dole zdjęcia + dodanie przycisku z prawej strony
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                        )
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 4.dp, end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween // Tekst z lewej, przycisk z prawej
                ) {
                    Column {
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
                    }
                    Button(
                        onClick = { showDialog = true }, // Ustawienie dialogu po kliknięciu
                        modifier = Modifier.align(Alignment.Bottom), // Przycisk wyrównany do dołu
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Kolor przycisku
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Dodaj")
                    }
                }
            }
        }

        // Wyświetlenie AlertDialog, jeśli showDialog jest true
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false // Zamknięcie dialogu
                },
                title = {
                    Text(text = "Czy na pewno chcesz dodać ten plan?")
                },
                text = {
                    Text(text = "Plan zostanie dodany do głównego ekranu.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            navController.navigate("mainScreen/$title/$duration/$imageResId")
                            showDialog = false // Zamknięcie dialogu
                        }
                    ) {
                        Text("Tak")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false // Zamknięcie dialogu po kliknięciu "Nie"
                        }
                    ) {
                        Text("Nie")
                    }
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PlanListPreview() {
    PlansListScreen(navController = rememberNavController())
}