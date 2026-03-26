package com.example.lab8

class ProductRepository {
    suspend fun fetchProducts(): List<Product> {
        return ApiClient.retrofitService.getProducts()
    }
}
