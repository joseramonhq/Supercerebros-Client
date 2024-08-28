package com.supercerebros.api


import com.supercerebros.data.ChildResponse
import com.supercerebros.data.UserResponse
import com.supercerebros.models.Child
import com.supercerebros.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/register")
    fun registerUser(@Body user: User): Call<UserResponse>
    @POST("children/registerChildren")
    fun registerChild(@Body child: Child):Call<ChildResponse>

}
