# SuperCerebros

SuperCerebros es una aplicación diseñada para ayudar a niños con TDAH mediante ejercicios de respiración y juegos educativos. Utiliza **Jetpack Compose** para una interfaz de usuario moderna y fluida, y está construida sobre el framework de **Android**. La aplicación incluye múltiples pantallas para diferentes funcionalidades como el inicio de sesión, recuperación de contraseña, registro de usuario, juegos de rompecabezas y ejercicios de respiración.

## Descripción

SuperCerebros tiene como objetivo mejorar las habilidades cognitivas de los niños a través de actividades lúdicas y ejercicios de respiración que les ayudan a concentrarse y relajarse. La aplicación tiene un enfoque simple pero eficaz, diseñada para ser fácil de usar tanto para niños como para padres y tutores.

## Requisitos previos

- **Android Studio** (última versión)
- **Kotlin** (el lenguaje principal)
- **Jetpack Compose** (para la interfaz de usuario)
- **Gradle** (para la construcción del proyecto)
- **Retrofit** (para la gestión de servicios RESTful)
- **Material3** (para diseño de UI/UX)
- **Google Fonts API** (para fuentes personalizadas)

## Instalación

1. Clona el repositorio:

   ```bash
   https://gitlab.com/joseramonhq/supercerebros_client.git
   ```

2. Abre el proyecto en **Android Studio**.
3. Asegúrate de que todos los dependencias se instalen correctamente. Si hay problemas con las dependencias, puedes forzar su actualización usando:

   ```bash
   ./gradlew build --refresh-dependencies
   ```

4. Conecta un dispositivo Android o usa un emulador para ejecutar la aplicación.

## Ejecución

Para ejecutar la aplicación en modo de desarrollo:

1. Asegúrate de tener un emulador o un dispositivo Android conectado.
2. Haz clic en **Run** en Android Studio o usa el siguiente comando:

   ```bash
   ./gradlew installDebug
   ```

## Arquitectura del Proyecto

El proyecto sigue una arquitectura **MVVM (Model-View-ViewModel)**, con los siguientes componentes clave:

- **UI (Jetpack Compose)**: La interfaz de usuario está construida usando Jetpack Compose y Material3, lo que permite construir UIs reactivas.
- **ViewModel**: Gestiona el estado y la lógica de la UI.
- **Retrofit**: Para la comunicación con la API.
- **Modelos**: Para la representación de datos y su manipulación.
- **Servicios API**: Gestión de autenticación (login, recuperación de contraseña) y ejercicios de respiración a través de servicios RESTful.

### Estructura del proyecto

```
/src/main/java/com/supercerebros/api                   # Servicios API
|   |-- ApiService.kt
|   |-- AuthService.kt
|   |-- RetrofitInstance.kt
|
/src/main/java/com/supercerebros/screens/breathing      # Pantallas de ejercicios de respiración
|   |-- BreathingExerciseAnimationScreen.kt
|   |-- BreathingExerciseScreen.kt
|
/src/main/java/com/supercerebros/data                   # Modelos y datos
|   |-- BreathingExerciseSession.kt
|   |-- BreathRequest.kt
|   |-- BreathResponse.kt
|   |-- ChildResponse.kt
|   |-- LoginRequest.kt
|   |-- LoginResponse.kt
|   |-- UserResponse.kt
|
/src/main/java/com/supercerebros/screens                # Otras pantallas de la app
|   |-- ChildOptionsMenuScreen.kt
|   |-- ChildOptionsScreen.kt
|   |-- ChildRegistrationScreen.kt
|   |-- LoginScreen.kt
|   |-- PasswordRecoveryScreen.kt
|   |-- RegistrationScreen.kt
|   |-- SplashScreen.kt
|   |-- SucessScreen.kt
|   |-- UnderConstructionScreen.kt
|   |-- TutorMenuScreen.kt
|   |-- SucessScreen2.kt 
|   |-- breathing                                       # Pantallas juego de respirar
|   |   |-- BreathingExerciseAnimationScreen.kt
|   |   |-- BreathingExerciseScreen.kt
|   |-- puzzle                                          # Pantallas juego de puzzle y lógica
|   |   |-- PuzzleGameScreen.kt
|   |   |-- PuzzleGrid.kt
|   |   |-- PuzzleState.kt
|   |   |-- PuzzleTile.kt
|   |   |-- PuzzleUtils.kt
|
/src/main/java/com/supercerebros/navigation             # navegación interna
|   |-- ChildOptionsMenuScreen.kt
|
/src/main/java/com/supercerebros/models                 # Modelos de datos
|   |-- Child.kt
|   |-- ChildResponse.kt
|   |-- BreathingSession.kt
|
/src/main/java/com/supercerebros/screens/puzzle         # Pantallas de juego de rompecabezas
|   |-- PuzzleGameScreen.kt
|   |-- PuzzleGrid.kt
|   |-- PuzzleState.kt
|   |-- PuzzleTile.kt
|   |-- PuzzleUtils.kt
|
/src/main/java/com/supercerebros/ui/theme               # Colores, tipografías y temas personalizados
|   |-- Color.kt
|   |-- Theme.kt
|   |-- Type.kt
|
/src/main/java/com/supercerebros/components             # Componentes reutilizables
|   |-- ExitConfirmationModal.kt
|
/src/main/java/com/supercerebros/utils                  # Utilidades generales, validaciones
|   |-- Validators.kt
|
/src/main/java/com/supercerebros                       # Archivos principales
|   |-- MainActivity.kt          # Actividad principal
|   |-- MyApplication.kt         # Punto de entrada de la app
```

## Uso

- **Login y Registro**: Los usuarios (niños y padres) pueden registrarse, iniciar sesión y recuperar su contraseña.
- **Juegos educativos**: Incluye un juego de rompecabezas que ayuda a mejorar habilidades cognitivas.
- **Ejercicios de respiración**: Ofrece sesiones guiadas de ejercicios de respiración para ayudar a los niños a relajarse y concentrarse.

## Contribución

Las contribuciones son bienvenidas. Si deseas contribuir, sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama:

   ```bash
   git checkout -b feature-nueva-funcionalidad
   ```

3. Realiza los cambios y haz un commit:

   ```bash
   git commit -m "Añadir nueva funcionalidad"
   ```

4. Empuja tus cambios a tu fork:

   ```bash
   git push origin feature-nueva-funcionalidad
   ```

5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Si tienes preguntas o comentarios sobre el proyecto, no dudes en contactar:

- Email: joseramonhq@gmail.com
- GitLab: [joseramonhq](https://gitlab.com/users/joseramonhq/projects)
- Github: [joseramonhq](https://github.com/joseramonhq?tab=repositories) 