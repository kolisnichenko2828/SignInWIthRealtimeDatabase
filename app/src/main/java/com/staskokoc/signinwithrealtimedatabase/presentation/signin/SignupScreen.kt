package com.staskokoc.signinwithrealtimedatabase.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database: FirebaseDatabase
private lateinit var reference: DatabaseReference

@Composable
fun SignupScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current
        var name by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Name") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { name = "" },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
                )
            },
        )
        var email by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Email") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { email = "" },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
                )
            },
        )
        var username by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Username") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { username = "" },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
                )
            },
        )
        var password by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Password") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { password = "" },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
                )
            },
        )
        OutlinedButton(
            modifier = Modifier
                .padding(all = 4.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                database = FirebaseDatabase.getInstance()
                reference = database.getReference("users")
                val userData = UserData(
                    name = name,
                    email = email,
                    username = username,
                    password = password
                )
                reference.child(username).setValue(userData)
                navController.navigate("MainScreen")
            }
        ) {
            Text(
                text = "SIGN UP",
                style = TextStyle(fontSize = 18.sp)
            )
        }
        OutlinedButton(
            modifier = Modifier
                .padding(all = 4.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                navController.navigate("LoginScreen")
            }
        ) {
            Text(
                text = "OR LOG IN",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

