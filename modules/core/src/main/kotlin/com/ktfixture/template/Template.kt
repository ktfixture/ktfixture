package com.ktfixture.template

class Template<T> private constructor(private val classType: Class<T>) {

    companion object {
        internal val structure: MutableMap<String, TemplateMapping> = mutableMapOf()
    }

    constructor(kClass: Class<T>, init: Template<T>.() -> Unit) : this(kClass) {
        init()
        structure[kClass.name] = TemplateMapping(kClass)
    }

    fun template(templateName: String, templateModelBuilder: TemplateModelBuilder.() -> Unit) {
        val template = TemplateMapping(classType).append(templateName, templateModelBuilder().fields())
    }

    override fun toString(): String = "Template(classType=$classType,structure=$structure)"

//    fun actions(): ModelBuilder {
//        return ModelBuilder()
//    }

}

typealias Field = Pair<String, Any>

class TemplateModelBuilder(private val structure: TemplateMapping, private val templateName: String) {
    constructor(templateStructure: TemplateMapping, templateName: String, actions: TemplateModelBuilder.() -> Unit) :
        this(templateStructure, templateName) {
        actions()
    }

    fun using(data: TemplateModelBuilder.() -> Field) = apply { structure.append(templateName, data()) }

    fun fields() {

    }
}

class TemplateMapping(private val classType: Class<*>) {
    private val values: MutableMap<String, Field> = mutableMapOf()

    fun append(templateName: String, data: Field) {
        values[templateName] = data
    }
}

infix fun <A, B> A.withValue(that: B): Pair<A, B> = Pair(this, that)