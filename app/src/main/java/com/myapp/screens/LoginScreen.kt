package com.myapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapp.R
import com.myapp.components.textComponents.sampledata.CustomText
import com.myapp.components.textComponents.sampledata.TextData
import com.myapp.components.textInputComponents.sampleData.CustomOutlinedTextField
import com.myapp.components.textInputComponents.sampleData.OutlinedTextFieldData

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Primer contenedor Row
            Row {
                CustomText(
                    data = TextData(
                        text = stringResource(id = R.string.welcome_to),
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomText(
                    data = TextData(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 18.sp
                    )
                )
            }
            // Segundo contenedor Column
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        data = TextData(
                            text = stringResource(id = R.string.no_account),
                            fontSize = 12.sp
                        )
                    )
                    CustomText(
                        data = TextData(
                            modifier = Modifier.clickable { navController.navigate("register_user") },
                            text = stringResource(id = R.string.sign_up),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                CustomText(data = TextData(text = stringResource(id = R.string.email)))
                CustomOutlinedTextField(
                    data = OutlinedTextFieldData(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.widthIn(max = OutlinedTextFieldDefaults.MinWidth),
                        colors = OutlinedTextFieldDefaults.colors(),
                        placeholder = {
                            Text(
                                text = "email",
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                CustomText(data = TextData(text = stringResource(id = R.string.password)))
                CustomOutlinedTextField(
                    data = OutlinedTextFieldData(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.widthIn(max = OutlinedTextFieldDefaults.MinWidth),
                        colors = OutlinedTextFieldDefaults.colors(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                )
                Box(Modifier.align(alignment = Alignment.End)) {
                    CustomText(
                        data = TextData(
                            text = stringResource(id = R.string.forgot_password),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { }
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Box(modifier = Modifier.align(alignment = Alignment.End)) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                        ) {
                            CustomText(data = TextData(text = stringResource(id = R.string.sign_in)))
                        }
                    }
                }
            }
        }
    }
}
