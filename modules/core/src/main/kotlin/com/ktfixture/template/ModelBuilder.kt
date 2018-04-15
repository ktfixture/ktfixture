package com.ktfixture.template

class ModelBuilder<out T> {

    fun single(): T? = null

    fun multiple(numberOfItems: Int): List<T> = listOf()

}