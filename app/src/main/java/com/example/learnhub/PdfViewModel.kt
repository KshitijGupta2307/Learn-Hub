package com.example.learnhub

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PdfViewModel : ViewModel() {
    val pdfUrl = mutableStateOf<String?>(null)

    fun fetchPdfUrl(pdfPath: String) {
        viewModelScope.launch {
            val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child(pdfPath)
            val urlTask = storageRef.downloadUrl
            try {
                val url = urlTask.await()
                pdfUrl.value = url.toString()
            } catch (e: Exception) {
                // Handle exceptions here
            }
        }
    }
}