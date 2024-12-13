@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.biometricx.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biometricx.ui.theme.AlegreyaSansFontFamily


@Composable
fun CTextField(
    onValueChange: (String) -> Unit,
    hint: String,
    value: String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFFBEC2C2)
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color(0xFFBEC2C2),
            unfocusedIndicatorColor = Color(0xFFBEC2C2)
        )
    )
}

@Composable
fun CTextFieldPassword(
    onValueChange: (String) -> Unit,
    hint: String,
    value: String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFFBEC2C2)
                )
            )
        },
        //visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color(0xFFBEC2C2),
            unfocusedIndicatorColor = Color(0xFFBEC2C2)
        )
    )
}

