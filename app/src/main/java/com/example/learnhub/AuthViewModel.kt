package com.example.learnhub

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = mutableStateOf<AuthState>(AuthState.Unauthenticated)
    val authState: MutableState<AuthState> = _authState
    init{
        checkAuthStatus()
    }
    fun login(email:String,password:String) {
        if (email.isEmpty() || password.isEmpty())
        {
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Login failed")

                    }
                }
        }
    }

    fun checkAuthStatus(){
        if(auth.currentUser != null){
            _authState.value = AuthState.Authenticated
        }else{
            _authState.value = AuthState.Unauthenticated
        }
    }
    fun SignUp(email:String,password:String) {
        if (email.isEmpty() || password.isEmpty())
        {
            _authState.value = AuthState.Loading
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Login failed")

                    }
                }
        }
    }
    fun logout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    sealed class AuthState{
        object Unauthenticated: AuthState()
        object Authenticated: AuthState()
        object Loading: AuthState()
        data class Error(val message:String): AuthState()
    }


}

