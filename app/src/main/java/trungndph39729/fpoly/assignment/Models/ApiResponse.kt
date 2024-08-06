package trungndph39729.fpoly.assignment.Models

data class ApiResponse<T>(
    val status: Int,
    val messenger: String,
    val result: T
)