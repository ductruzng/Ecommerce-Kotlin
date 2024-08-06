package trungndph39729.fpoly.assignment.Models

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    val title: String,
    val price: Double,
    val numberInCart: Int?,
    val rating: Double?,
    val picUrl: List<String>?,
    val description: String?,
    val review: String?,
    val id_category: String
)

data class ProductResponse(
    val _id: String,
    val title: String,
    val price: Double,
    val numberInCart: Int?,
    val rating: Double?,
    val picUrl: List<String>?,
    val description: String?,
    val review: String?,
    val id_category: String,

) {
    fun toProduct(): Product {
        return Product(
            id = _id,
            title = title,
            price = price,
            numberInCart = numberInCart,
            rating = rating,
            picUrl = picUrl,
            description = description,
            review = review,
            idCategory = id_category,

        )
    }
}

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    var numberInCart: Int?,
    val rating: Double?,
    val picUrl: List<String>?,
    val description: String?,
    val review: String?,
    val idCategory: String,

)
