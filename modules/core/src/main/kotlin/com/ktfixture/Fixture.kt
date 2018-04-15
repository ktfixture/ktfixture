package com.ktfixture

import com.ktfixture.template.Template

object Fixture {

    inline fun <reified T> prepare(noinline defination: Template<T>.() -> Unit) =
        Template(T::class.java, defination)//.actions()

}