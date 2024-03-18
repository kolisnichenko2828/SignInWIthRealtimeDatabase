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
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var database: FirebaseDatabase
private lateinit var reference: DatabaseReference

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current
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
                val query = reference.orderByChild("username").equalTo(username)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        println(error.message)
                    }
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            println(snapshot)
                            println(snapshot.child(username))
                            val passwordFromDb = snapshot.child(username).child("password").getValue(String::class.java)
                            println(passwordFromDb)
                            if (passwordFromDb == password) {
                                navController.navigate("MainScreen")
                            }
                        }
                    }
                })
            }
        ) {
            Text(
                text = "SIGN IN",
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
                navController.navigate("SignupScreen")
            }
        ) {
            Text(
                text = "OR SIGN UP",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}