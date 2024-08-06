package trungndph39729.fpoly.assignment.login_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import trungndph39729.fpoly.assignment.Models.Order
import trungndph39729.fpoly.assignment.Models.OrderRequest
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.Models.User
import trungndph39729.fpoly.assignment.Models.UserRequest
import trungndph39729.fpoly.assignment.data.AuthRepository
import trungndph39729.fpoly.assignment.data.Resource
import trungndph39729.fpoly.assignment.signup_screen.SignUpState
import trungndph39729.fpoly.lab7.retrofit.RetrofitService
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>  get()= _user

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>>  get()= _orders

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order> get()= _order

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun signIn(userRequest: UserRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.login(userRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {

                        _user.postValue(apiResponse.result.toUser())
                        _signInState.send(SignInState(isSuccess = "Login Success"))

                    } else {
                        _user.postValue(null)
                        _signInState.send(SignInState(isError = "Wrong Email or Password"))
                    }
                } else {
                    _user.postValue(null)
                    _signInState.send(SignInState(isError = "Wrong Email or Password"))
                    Log.e("TAG", "login: " + response.message())
                }
            } catch (e: Exception) {
                _user.postValue(null)
                _signInState.send(SignInState(isError = "Login Failed"))
                Log.e("TAG", "loginE: " + e.message)
            }
        }
    }

    fun updateProfile(id: String, userRequest: UserRequest){
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.updateProfile(id,userRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        _user.postValue(apiResponse.result.toUser())

                    } else {
                        _user.postValue(null)
                    }
                } else {
                    _user.postValue(null)
                    Log.e("TAG", "ud: " + response.message())
                }
            } catch (e: Exception) {
                _user.postValue(null)
                Log.e("TAG", "udE: " + e.message)
            }
        }
    }

    fun addOrder(orderRequest: OrderRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.addOrder(orderRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        _order.postValue(apiResponse.result.toOrder())

                    } else {
                        _order.postValue(null)
                    }
                } else {
                    _order.postValue(null)
                    Log.e("TAG", "aorder: " + response.message())
                }
            } catch (e: Exception) {
                _order.postValue(null)
                Log.e("TAG", "aorderE: " + e.message)
            }
        }
    }

    fun getOrders(userId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.getOrders(userId)

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        val orders = apiResponse.result.map {
                            it.toOrder()
                        }
                        _orders.postValue(orders)

                    } else {
                        _orders.postValue(emptyList())
                    }
                } else {
                    _orders.postValue(emptyList())
                    Log.e("TAG", "order: " + response.message())
                }
            } catch (e: Exception) {
                _orders.postValue(emptyList())
                Log.e("TAG", "orderE: " + e.message)
            }
        }
    }


}