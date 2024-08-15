import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supercerebros.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) } // Para la animación de desaparición

    LaunchedEffect(Unit) {
        // Animación de escala inicial
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )

        // Retardo antes de empezar la animación de desaparición
        delay(3000L)

        // Animación de desaparición (fade-out)
        alpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 200
            )
        )


    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.alpha(alpha.value) // Aplicar la animación de transparencia
        ) {
            Image(
                painter = painterResource(id = R.drawable.supercerebros_logo),
                contentDescription = "Supercerebros Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Supercerebros",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Atentos",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
        SplashScreen()
    }

