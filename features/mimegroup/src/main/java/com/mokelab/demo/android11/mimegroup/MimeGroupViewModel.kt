package com.mokelab.demo.android11.mimegroup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MimeGroupViewModel(private val app: Application) : AndroidViewModel(app) {
    val mimeTypes = MutableLiveData<Set<String>>()

    fun loadTypes() {
        val pm = app.packageManager
        val mimeTypes = pm.getMimeGroup(MIME_GROUP)
        this.mimeTypes.value = mimeTypes
    }

    fun addType(type: String) {
        val types = this.mimeTypes.value?.toMutableSet() ?: mutableSetOf()
        types.add(type)
        this.mimeTypes.value = types
    }

    fun removeType(type: String) {
        val types = this.mimeTypes.value?.toMutableSet() ?: mutableSetOf()
        types.remove(type)
        this.mimeTypes.value = types
    }

    fun submitTypes() {
        val pm = app.packageManager
        val types = this.mimeTypes.value ?: mutableSetOf()
        pm.setMimeGroup(MIME_GROUP, types)
    }


    companion object {
        private const val MIME_GROUP = "com.mokelab:demoFeature"
    }
}