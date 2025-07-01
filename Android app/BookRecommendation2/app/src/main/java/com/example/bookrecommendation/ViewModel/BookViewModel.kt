package com.example.bookrecommendation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookrecommendation.API.RetrofitInstance
import com.example.bookrecommendation.Model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookViewModel: ViewModel() {
    private val api = RetrofitInstance.api

    private val _popularBooks = MutableStateFlow<List<Book>>(emptyList())
    val popularBooks: StateFlow<List<Book>> = _popularBooks.asStateFlow()

    private val _recommendedBooks = MutableStateFlow<List<Book>>(emptyList())
    val recommendedBooks: StateFlow<List<Book>> = _recommendedBooks.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun fetchPopularBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try{
                val response = RetrofitInstance.api.getPopularBooks()
                _popularBooks.value = response
            }catch (e: IOException) {
                _error.value = "Network error"
            } catch (e: HttpException) {
                _error.value = "Server error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchRecommendedBooks(bookTitle: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try{
                val body = mapOf("book_title" to bookTitle)
                val response = RetrofitInstance.api.getRecommendations(body)
                _recommendedBooks.value = response

            }catch (e: IOException) {
                _error.value = "Network error"
            } catch (e: HttpException) {
                _error.value = "Book not found or server error"
            } finally {
                _isLoading.value = false
            }
        }

    }

    fun clearRecommendations() {
        _recommendedBooks.value = emptyList()

    }

}