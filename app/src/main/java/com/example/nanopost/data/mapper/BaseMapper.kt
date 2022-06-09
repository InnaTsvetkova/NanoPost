package com.example.nanopost.data.mapper

abstract class BaseMapper<in IN, out OUT> {
    abstract fun transform(entity: IN): OUT
    fun transform(entities: List<IN>): List<OUT>{
        return entities.map{
            transform(it)
        }
    }
}