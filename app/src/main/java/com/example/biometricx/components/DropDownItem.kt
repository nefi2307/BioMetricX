package com.example.biometricx.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSex(lista: List<String>){
    val parentescosImportantes = listOf(
        "Padre",
        "Madre",
        "Hijo",
        "Hija",
        "Hermano",
        "Hermana",
        "Abuelo",
        "Abuela",
        "Otro"
    )
    var isExtended by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(lista[0]) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = isExtended,
            onExpandedChange = {isExtended = !isExtended}
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value =selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExtended) }
            )
            ExposedDropdownMenu(expanded = isExtended, onDismissRequest = {isExtended = false}) {
                parentescosImportantes.forEachIndexed{index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text)},
                        onClick = {
                            selectedText = lista[index]
                            isExtended = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}