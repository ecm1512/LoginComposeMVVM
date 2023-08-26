package com.educode.logincompose.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.educode.logincompose.presentation.components.EventDialog
import com.educode.logincompose.presentation.components.RoundedButton
import com.educode.logincompose.presentation.components.SocialMediaButton
import com.educode.logincompose.presentation.components.TransparentTextField
import com.educode.logincompose.presentation.theme.FACEBOOKCOLOR
import com.educode.logincompose.presentation.theme.GMAILCOLOR

@Composable
fun SignUpScreen(
    state: SignUpState,
    onRegister: (String, String) -> Unit,
    onBack: () -> Unit,
    onDismissDialog: () -> Unit
){

    val emailValue = remember { mutableStateOf("") }

    val passwordValue = remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }

                Text(
                    text = "Create An Account",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Email",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()

                            onRegister(
                                emailValue.value,
                                passwordValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if(passwordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if(passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    text = "Sign Up",
                    displayProgressBar = state.displayProgressBar,
                    onClick = {
                        onRegister(
                            emailValue.value,
                            passwordValue.value
                        )
                    }
                )
                
                ClickableText(
                    text = buildAnnotatedString {
                        append("Already have an account?")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Log In")
                        }
                    },
                    onClick = {
                        onBack()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "OR",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Black
                        )
                    )

                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }
                
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login with",
                    style = MaterialTheme.typography.body1.copy(
                        MaterialTheme.colors.primary
                    ),
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SocialMediaButton(
                    text = "Login with Facebook",
                    onClick = {  },
                    socialMediaColor = FACEBOOKCOLOR
                )

                SocialMediaButton(
                    text = "Login with Gmail",
                    onClick = {  },
                    socialMediaColor = GMAILCOLOR
                )
            }
        }

        if(state.errorMessage != null){
            EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
        }
    }
}