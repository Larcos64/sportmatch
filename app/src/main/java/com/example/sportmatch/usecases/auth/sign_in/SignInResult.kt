package com.example.sportmatch.usecases.auth.sign_in

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val email: String?,
    val profilePictureUrl: String?,
    val role: String?
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "user_id" to this.userId,
            "username" to this.username,
            "email" to this.email,
            "profile_picture_url" to this.profilePictureUrl,
            "role" to this.role
        ).filterValues { it != null }.toMutableMap()
    }
}
