package trungndph39729.fpoly.assignment.data

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String, password:String): Flow<Resource<AuthResult>>
    fun registerUser(email:String, password:String): Flow<Resource<AuthResult>>
}