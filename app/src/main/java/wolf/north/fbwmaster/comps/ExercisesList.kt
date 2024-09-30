package wolf.north.fbwmaster.comps

import CurrentData
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwmaster.R
import wolf.north.fbwmaster.navigation.NavigationComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesListScreen(navController: NavController) {

    // Przykładowe kategorie
    val workoutCategories = listOf("Full Body", "Stretching", "Chest", "Triceps", "Legs", "Biceps")

    // Przykładowa lista ćwiczeń z opisami
    val exercises = listOf(
        Exercise(
            "Push-ups",
            "Chest",
            R.drawable.pushup,
            "Push-ups help to strengthen the chest, shoulders, and triceps, while also engaging the core."
        ),
        Exercise(
            "Bench Press",
            "Chest",
            R.drawable.bench_press,
            "The bench press is one of the best exercises for building chest muscles and upper body strength."
        ),
        Exercise(
            "Barbell Rows",
            "Back",
            R.drawable.barbell_rows,
            "Barbell rows are great for developing the upper back, improving posture, and increasing pulling strength."
        ),
        Exercise(
            "Dumbbell Rows",
            "Back",
            R.drawable.db_rows,
            "Dumbbell rows target the back muscles and can improve stability and balance when performed correctly."
        ),
        Exercise(
            "Pull-ups",
            "Back",
            R.drawable.pull_ups,
            "Pull-ups are excellent for building upper back strength and also work the biceps."
        ),
        Exercise(
            "Arm Circles",
            "Stretching",
            R.drawable.arm_circles,
            "Arm circles help to warm up the shoulders and increase flexibility in the upper body."
        ),
        Exercise(
            "RKC Plank",
            "Full Body",
            R.drawable.plank,
            "The RKC plank is an advanced plank variation that strengthens the entire core and improves stability."
        ),
        Exercise(
            "Burpees",
            "Full Body",
            R.drawable.burpee,
            "Burpees are a full-body workout that improves cardiovascular fitness, strength, and coordination."
        ),
        Exercise(
            "Deadlift",
            "Back",
            R.drawable.deadlift,
            "The deadlift is a compound movement that targets the lower back, glutes, and hamstrings."
        ),
        Exercise(
            "Squats",
            "Legs",
            R.drawable.squat,
            "Squats are excellent for building strength in the legs, glutes, and core."
        ),
        Exercise(
            "Lunges",
            "Legs",
            R.drawable.lunges,
            "Lunges improve lower body strength and balance, targeting the quads, hamstrings, and glutes."
        ),
        Exercise(
            "Bicep Curls",
            "Biceps",
            R.drawable.biceps_curls,
            "Bicep curls isolate the biceps, helping to build arm strength and size."
        ),
        Exercise(
            "Tricep Dips",
            "Triceps",
            R.drawable.chair_dips,
            "Tricep dips are great for targeting the triceps and can be performed almost anywhere."
        ),
        Exercise(
            "Chair Dips",
            "Triceps",
            R.drawable.chair_dips,
            "Chair dips are another tricep-focused exercise, which also engages the shoulders and chest."
        )
    )

    // Wybrane kategorie (na początek pusta lista)
    val selectedCategories = remember { mutableStateListOf<String>() }

    // Stan dla okna dialogowego
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }


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
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                containerColor = Color.Black,
                contentColor = Color.Gray,
                tonalElevation = 5.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { navController.navigate("mainScreen") }) {
                        Icon(
                            imageVector = Icons.Default.Home, // Zmień na dowolną ikonę
                            contentDescription = "Home",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { navController.navigate("plansListScreen")}) {
                        Icon(
                            imageVector = Icons.Default.FormatListNumbered, // Zmień na dowolną ikonę
                            contentDescription = "Exercises list bottom bar",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { navController.navigate("exercisesList") }) {
                        Icon(
                            imageVector = Icons.Default.Search, // Zmień na dowolną ikonę
                            contentDescription = "Search",
                            tint = Color.White
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
        },
        content = {
            // Treść ekranu
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color.White),
            ) {
                Spacer(modifier = Modifier.size(15.dp))

                // LazyRow z kategoriami
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(workoutCategories) { category ->
                        WorkoutTypeComposable(
                            workoutType = category,
                            isSelected = selectedCategories.contains(category),
                            onCategorySelected = {
                                if (selectedCategories.contains(category)) {
                                    selectedCategories.remove(category)
                                } else {
                                    selectedCategories.add(category)
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                // LazyColumn z ćwiczeniami
                ExercisesListShow(
                    exercises = exercises.filter { it.category in selectedCategories || selectedCategories.isEmpty() },
                    onExerciseSelected = { exercise ->
                        selectedExercise = exercise
                    }
                )

                // Wyświetlanie okienka dialogowego z opisem ćwiczenia
                selectedExercise?.let { exercise ->
                    ExerciseDetailsDialog(
                        exercise = exercise,
                        onDismiss = { selectedExercise = null })
                }

            }
        }
    )
}

@Composable
fun WorkoutTypeComposable(
    workoutType: String,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Black else Color.White
    val textColor = if (isSelected) Color.White else Color.Black
    val borderColor = if (isSelected) Color.Red else Color.Black

    OutlinedButton(
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(6.dp),
        border = BorderStroke(width = 2.dp, color = borderColor),
        onClick = onCategorySelected,
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(text = workoutType, color = textColor, fontSize = 18.sp)
    }
}

@Composable
fun ExercisesListShow(exercises: List<Exercise>, onExerciseSelected: (Exercise) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(exercises) { exercise ->
            ExerciseCard(exercise = exercise, onClick = { onExerciseSelected(exercise) })
        }
    }
}

@Composable
fun ExerciseDetailsDialog(exercise: Exercise, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = exercise.imageRes),
                    contentDescription = exercise.name,
                    modifier = Modifier.size(250.dp) // Duży obraz ćwiczenia
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = exercise.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )

                Text(
                    text = exercise.category,
                    color = Color.Gray,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = exercise.description, // Wyświetlenie opisu ćwiczenia
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Przycisk zamykający okienko
                Button(onClick = { onDismiss() }) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(onClick = onClick), // Kliknięcie otwiera okienko
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.main_card_bg))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = exercise.imageRes),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = exercise.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = exercise.category,
                    color = Color.Gray
                )
            }
        }
    }
}


// Model danych
data class Exercise(
    val name: String,
    val category: String,
    val imageRes: Int,
    val description: String
)


// Podgląd
@Preview(showSystemUi = true)
@Composable
fun ExercisesListPreview() {
    ExercisesListScreen(navController = rememberNavController())
}
