import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    // Firebase auth state listener to detect auth state changes
    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        if (firebaseAuth.currentUser != null) {
            _authState.postValue(AuthState.Authenticated)
        } else {
            _authState.postValue(AuthState.Unauthenticated)
        }
    }

    init {
        // Attach the listener to detect state at initialization
        auth.addAuthStateListener(authStateListener)
        checkAuthStatus()
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.postValue(AuthState.Authenticated)
                } else {
                    _authState.postValue(
                        AuthState.Error(task.exception?.message ?: "Login failed")
                    )
                }
            }
    }

    fun checkAuthStatus() {
        // Post the initial auth status
        if (auth.currentUser != null) {
            _authState.postValue(AuthState.Authenticated)
        } else {
            _authState.postValue(AuthState.Unauthenticated)
        }
    }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.postValue(AuthState.Authenticated)
                } else {
                    _authState.postValue(
                        AuthState.Error(task.exception?.message ?: "Sign-up failed")
                    )
                }
            }
    }

    fun logout() {
        // Sign out from Firebase and update the auth state
        auth.signOut()
        _authState.postValue(AuthState.Unauthenticated)
    }

    override fun onCleared() {
        super.onCleared()
        // Remove the auth state listener to avoid memory leaks
        auth.removeAuthStateListener(authStateListener)
    }

    sealed class AuthState {
        object Unauthenticated : AuthState()
        object Authenticated : AuthState()
        object Loading : AuthState()
        data class Error(val message: String) : AuthState()
    }
}
