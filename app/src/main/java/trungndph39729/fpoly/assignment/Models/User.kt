package trungndph39729.fpoly.assignment.Models

data class UserAddress(
    val name: String,
    val address: String,
    val city: String,
    val phoneNo: String,
    val isDefault: Boolean,
)

data class UserRequest(
    val _id: String? = null,
    val password: String? = null,
    val email: String? = null,
    val name: String? = null,
    val phoneNo: String? = null,
    val addresses: List<UserAddress>? = null,
    val orders: List<String>? = null,
    val avatar: String? = null,
)


data class User(
    val _id: String,
    val password: String,
    val email: String,
    val name: String?,
    val phoneNo: String?,
    val avatar: String?,
    val addresses: List<UserAddress>?,
    val orders:List<String>?,
    val createdAt: String,
    val updatedAt: String
)


data class UserResponse(
    val _id: String,
    val password: String,
    val email: String,
    val name: String?,
    val phoneNo: String?,
    val avatar: String?,
    val addresses: List<UserAddress>?,
    val orders:List<String>?,
    val createdAt: String,
    val updatedAt: String
) {
    fun toUser(): User {
        return User(
            _id = _id,
            password = password,
            email = email,
            name = name,
            phoneNo = phoneNo,
            avatar = avatar,
            addresses = addresses,
            orders = orders,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
