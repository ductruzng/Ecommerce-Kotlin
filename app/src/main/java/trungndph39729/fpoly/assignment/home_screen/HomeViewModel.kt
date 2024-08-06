package trungndph39729.fpoly.assignment.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import trungndph39729.fpoly.assignment.Models.CategoryModel
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.lab7.retrofit.RetrofitService
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        getCategories()
        getProducts()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.getListCategory()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        val categories = apiResponse.result.map {
                            it.toCategory()
                        }
                        _categories.postValue(categories)

                    } else {
                        _categories.postValue(emptyList())
                    }
                } else {
                    _categories.postValue(emptyList())
                }
            } catch (e: Exception) {
                _categories.postValue(emptyList())
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().MyApiService.getListProduct()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        val products = apiResponse.result.map {
                            it.toProduct()
                        }
                        _products.postValue(products)

                    } else {
                        Log.d("TAG", "getMoviesE: " )
                        _products.postValue(emptyList())
                    }
                } else {
                    Log.d("TAG", "getMoviesE: " )
                    _products.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("TAG", "getMoviesE: " + e.message)
                _products.postValue(emptyList())
            }
        }
    }


}

