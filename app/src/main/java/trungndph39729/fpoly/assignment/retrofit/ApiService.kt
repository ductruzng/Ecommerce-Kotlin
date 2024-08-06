package trungndph39729.fpoly.lab7.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import trungndph39729.fpoly.assignment.Models.ApiResponse
import trungndph39729.fpoly.assignment.Models.CategoryResponse
import trungndph39729.fpoly.assignment.Models.OrderRequest
import trungndph39729.fpoly.assignment.Models.OrderResponse
import trungndph39729.fpoly.assignment.Models.ProductResponse
import trungndph39729.fpoly.assignment.Models.User
import trungndph39729.fpoly.assignment.Models.UserRequest
import trungndph39729.fpoly.assignment.Models.UserResponse

interface ApiService {
    @GET("categories")
    suspend fun getListCategory(): Response<ApiResponse<List<CategoryResponse>>>

    @GET("get-list-products")
    suspend fun getListProduct(): Response<ApiResponse<List<ProductResponse>>>

    @POST("register")
    suspend fun register(@Body userRequest: UserRequest): Response<ApiResponse<UserResponse>>

    @POST("login")
    suspend fun login(@Body userRequest: UserRequest): Response<ApiResponse<UserResponse>>

    @PUT("add-info-user-by-id/{userId}")
    suspend fun updateProfile(@Path("userId") id: String,@Body userRequest: UserRequest): Response<ApiResponse<UserResponse>>

    @POST("add-order")
    suspend fun addOrder(@Body orderRequest: OrderRequest): Response<ApiResponse<OrderResponse>>

    @GET("orders/{userId}")
    suspend fun getOrders(@Path("userId") userId: String): Response<ApiResponse<List<OrderResponse>>>
}