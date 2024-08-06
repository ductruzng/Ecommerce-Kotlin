package trungndph39729.fpoly.assignment.Models

data class CategoryRequest(
    val title: String,
    val picUrl: String

)

data class CategoryResponse(
    val _id: String,
    val title: String,
    val picUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
) {
    fun toCategory(): CategoryModel {
        return CategoryModel(
            _id = _id,
            title = title,
            picUrl = picUrl
        )
    }
}

data class CategoryModel(
    val _id: String,
    val title: String,
    val picUrl: String
)
