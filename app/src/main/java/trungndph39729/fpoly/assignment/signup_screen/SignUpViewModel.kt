package trungndph39729.fpoly.assignment.signup_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import trungndph39729.fpoly.assignment.Models.CategoryModel
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.Models.User
import trungndph39729.fpoly.assignment.Models.UserRequest
import trungndph39729.fpoly.assignment.login_screen.SignInState
import trungndph39729.fpoly.lab7.retrofit.RetrofitService
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    val _signUpState = Channel<SignUpState> ()
    val signUpState = _signUpState.receiveAsFlow()

    fun createUser(userRequest: UserRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.register(userRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    _user.postValue(apiResponse?.result?.toUser())
                    _signUpState.send(SignUpState(isSuccess = "Register Success"))
                    Log.d("TAG", "createUser: " + apiResponse?.result)
                } else {
                    _user.postValue(null)
                    _signUpState.send(SignUpState(isError = response.message()))
                    Log.e("TAG", "createUser: " + response.message())
                }
            } catch (e: Exception) {
                _user.postValue(null)
                _signUpState.send(SignUpState(isError = e.message))
                Log.e("TAG", "createUserE: " + e.message)
            }
        }
    }

}