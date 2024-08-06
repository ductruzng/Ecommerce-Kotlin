package trungndph39729.fpoly.assignment.Models

import androidx.compose.runtime.snapshots.SnapshotStateList

data class OrderRequest(
    val _id: String? = null,
    val user: User,
    val products: SnapshotStateList<Product>,
    val totalPrice: Double,
)


data class OrderResponse(
    val _id: String,
    val user: User, // Đảm bảo giá trị này không null
    val products: SnapshotStateList<Product>,
    val totalPrice: Double,
    val createdAt: String,
    val status: String,
) {
    fun toOrder(): Order {
        return Order(
            _id = _id,
            user = user, // Đảm bảo giá trị này không null
            products = products,
            totalPrice = totalPrice,
            status = status,
            createdAt = createdAt
        )
    }
}

data class Order(
    val _id: String,
    val user: User, // Assuming user is a String representing ObjectId
    val products: SnapshotStateList<Product>,
    val totalPrice: Double,
    val createdAt: String,
    val status: String,
)