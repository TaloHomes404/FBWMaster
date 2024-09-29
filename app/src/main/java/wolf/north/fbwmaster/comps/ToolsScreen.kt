package wolf.north.fbwmaster.comps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wolf.north.fbwmaster.R
import wolf.north.fbwmaster.comps.ui.theme.FBWMasterTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ToolsScreen() {
    // Stan rozwijania/zwijania dla każdego elementu listy
    var isBmiCalculatorVisible by remember { mutableStateOf(false) }
    var isOtherToolVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = "Thu, 3 Aug",
                                color = Color.Black
                            )
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
                Spacer(modifier = Modifier.height(4.dp))
                Divider(
                    modifier = Modifier.alpha(0.3f),
                    color = Color.LightGray,
                    thickness = 2.dp
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Nagłówek sekcji "Tools and Calculators"
                Text(
                    text = "Tools and Calculators",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Lista opcji kalkulatorów
                Divider(color = Color.LightGray, thickness = 1.dp)

                // BMI Calculator Option
                OptionItem(
                    title = "BMI Calculator",
                    isVisible = isBmiCalculatorVisible,
                    onClick = { isBmiCalculatorVisible = !isBmiCalculatorVisible }
                ) {
                    BMICalculatorForm()
                }

                Divider(color = Color.LightGray, thickness = 1.dp)

                // Inna opcja narzędzia (np. inny kalkulator)
                OptionItem(
                    title = "Other Tool",
                    isVisible = isOtherToolVisible,
                    onClick = { isOtherToolVisible = !isOtherToolVisible }
                ) {
                    // Zawartość rozwijana dla innego narzędzia
                    Text("Tu pojawią się inne narzędzia lub kalkulatory.", modifier = Modifier.padding(16.dp))
                }

                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* Akcja dla pierwszej ikony */ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    }
                    IconButton(onClick = { /* Akcja dla drugiej ikony */ }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = { /* Akcja dla trzeciej ikony */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { /* Akcja dla czwartej ikony */ }) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = "Tools"
                        )
                    }
                }
            }
        }
    )
}

// Funkcja komponentu OptionItem
@Composable
fun OptionItem(
    title: String,
    isVisible: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isVisible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null
            )
        }
        // Animacja rozwijania/zawijania
        AnimatedVisibility(visible = isVisible) {
            content()
        }
    }
}

@Composable
fun BMICalculatorForm() {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<Gender?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Wprowadzenie wieku
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Wybór płci
        Text(text = "Gender", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            GenderButton(
                gender = Gender.Male,
                isSelected = selectedGender == Gender.Male,
                onClick = { selectedGender = Gender.Male }
            )
            GenderButton(
                gender = Gender.Female,
                isSelected = selectedGender == Gender.Female,
                onClick = { selectedGender = Gender.Female }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Wprowadzenie wzrostu
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Wprowadzenie wagi
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Przycisk do obliczenia BMI
        Button(
            onClick = {
                // Logika obliczania BMI
                val bmi = calculateBMI(height.toFloatOrNull(), weight.toFloatOrNull())
                // Można dodać logikę wyświetlania wyniku
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate BMI")
        }
    }
}

@Composable
fun GenderButton(gender: Gender, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(1.dp, if (isSelected) Color.Black else Color.Gray, CircleShape)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = if (gender == Gender.Male) Icons.Default.Male else Icons.Default.Female,
            contentDescription = gender.name,
            tint = if (isSelected) Color.Black else Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = gender.name,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

// Enum dla płci
enum class Gender {
    Male, Female
}

// Funkcja obliczająca BMI
fun calculateBMI(height: Float?, weight: Float?): Float? {
    if (height == null || weight == null || height == 0f) {
        return null
    }
    return weight / ((height / 100) * (height / 100))
}

@Preview(showSystemUi = true)
@Composable
private fun ToolsScreenPreview() {
    ToolsScreen()
}