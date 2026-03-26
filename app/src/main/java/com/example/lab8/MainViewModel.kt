package com.example.lab8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repo: ProductRepository = ProductRepository()) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadProducts() {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val list = repo.fetchProducts()
                _products.value = list
            } catch (e: Exception) {
                _error.value = e.message ?: "Lỗi không xác định"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
