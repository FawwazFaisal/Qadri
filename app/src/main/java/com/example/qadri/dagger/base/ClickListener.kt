package com.example.qadri.dagger.base

interface ClickListener {
    fun <T> onClick(data :T,createNested:Boolean = false)
}