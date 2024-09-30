import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CurrentData() {
    val currentDate = remember { SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(Date()) }

    Column {
        Text(
            text = currentDate,
            color = Color.Black
        )
    }
}