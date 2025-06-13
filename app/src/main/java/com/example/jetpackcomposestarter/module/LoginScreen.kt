package com.example.jetpackcomposestarter.module

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposestarter.module.travel.travelAuthorisationManagers.LoginViewModel
import com.example.jetpackcomposestarter.R

@Composable
fun LoginScreen(
//    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
    onNavigateToActivation: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    val cardShape = RoundedCornerShape(16.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, cardShape)
                .clip(cardShape)
                .background(Color.White, cardShape),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg3),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username"
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField()
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onNavigateToAuthenticatedRoute() },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AuthLink(
                        text = "Forgot Password",
                        onClick = onNavigateToForgotPassword
                    )
                    AuthLink(
                        text = "Mobile Activation",
                        onClick = onNavigateToActivation
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    Text("  or  ", color = Color.Gray)
                    Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { /* Handle Microsoft sign in */ },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    MicrosoftIcon(modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sign in with Microsoft")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("© 2025 ")
                            }
                            withStyle(SpanStyle(color = Color(0xFF0080C5), fontSize = 14.sp)) {
                                append("Agathae Business Solutions")
                            }
                            append("\n")
                            withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("AgathaeHR Mobile 3.0.3")
                            }
                        },
                        textAlign = TextAlign.Center
                    )
                }

            }

        }
    }
}

@Composable
fun EmptyComingSoon(onNavigateBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.empty_screen_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.empty_screen_subtitle),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline,
        )
        AuthLink(
            text = "Back to Login",
            onClick = onNavigateBack
        )
    }
}

@Composable
fun ForgotPasswordScreen(onNavigateBack: () -> Unit, onNavigateToAuthenticatedRoute: () -> Unit,) {
    var username by remember { mutableStateOf("") }
    var employeeNo by remember { mutableStateOf("") }
    var identificationNo by remember { mutableStateOf("") }
    val cardShape = RoundedCornerShape(16.dp)

    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, cardShape)
                .clip(cardShape)
                .background(Color.White, cardShape),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg3),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username"
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormTextField(
                    value = employeeNo,
                    onValueChange = { employeeNo = it },
                    label = "Employee No"
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormTextField(
                    value = identificationNo,
                    onValueChange = { identificationNo = it },
                    label = "NRIC"
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onNavigateToAuthenticatedRoute() },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reset Password")
                }
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { onNavigateBack() },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("© 2025 ")
                            }
                            withStyle(SpanStyle(color = Color(0xFF0080C5), fontSize = 14.sp)) {
                                append("Agathae Business Solutions")
                            }
                            append("\n")
                            withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("AgathaeHR Mobile 3.0.3")
                            }
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = if (isFocused) MaterialTheme.colorScheme.primary else Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                if (value.isEmpty()) {
                    Text(label, color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}


@Composable
fun PasswordTextField() {
    val state = remember { TextFieldState() }
    var showPassword by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    BasicSecureTextField(
        state = state,
        textObfuscationMode = if (showPassword) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        textStyle = TextStyle(fontSize = 16.sp),
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = if (isFocused) MaterialTheme.colorScheme.primary else Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.text.isEmpty()) {
                            Text("Password", color = Color.Gray)
                        }
                        innerTextField()
                    }

                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { showPassword = !showPassword }
                    )
                }
            }
        }
    )
}

@Composable
fun MicrosoftIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width / 2
        val h = size.height / 2
        drawRect(Color(0xFFF25022), size = Size(w, h), topLeft = Offset(0f, 0f))
        drawRect(Color(0xFF7FBA00), size = Size(w, h), topLeft = Offset(w, 0f))
        drawRect(Color(0xFF00A4EF), size = Size(w, h), topLeft = Offset(0f, h))
        drawRect(Color(0xFFFFB900), size = Size(w, h), topLeft = Offset(w, h))
    }
}

@Composable
fun AuthLink(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.bodyMedium,
//        textDecoration = TextDecoration.Underline
    )
}


//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    JetpackComposeTheme {
//        LoginScreen()
//    }
//}

