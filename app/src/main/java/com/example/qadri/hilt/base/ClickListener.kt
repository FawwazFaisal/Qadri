package com.example.qadri.hilt.base

interface ClickListener {
    fun <T> onClick(data :T,createNested:Boolean = false)
}