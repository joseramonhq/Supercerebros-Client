package com.supercerebros.controllers

import androidx.compose.runtime.Composable
import com.supercerebros.MyApplication
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.ChildResponse
import com.supercerebros.models.Child
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun registerChild(child: Child, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
    val apiService = RetrofitInstance.apiService
    val app = MyApplication()

    apiService.registerChild(child).enqueue(object : Callback<ChildResponse> {
        override fun onResponse(call: Call<ChildResponse>, response: Response<ChildResponse>) {
            if (response.isSuccessful) {
                val registeredChild = response.body()
                if (registeredChild != null) {
                    // Obtener la lista actual de childrenIds, crear una nueva lista mutable, agregar el nuevo ID y actualizar
                    val currentChildrenIds = app.currentUser?.childrenIds?.toMutableList() ?: mutableListOf()
                    registeredChild.id?.let { currentChildrenIds.add(it) }
                    app.currentUser = app.currentUser?.copy(childrenIds = currentChildrenIds)

                    onSuccess()
                } else {
                    onFailure(Throwable("Registro fallido, el cuerpo de la respuesta es nulo."))
                }
            } else {
                onFailure(Throwable("Registro fallido, respuesta no exitosa."))
            }
        }

        override fun onFailure(call: Call<ChildResponse>, t: Throwable) {
            onFailure(t)
        }
    })
}

