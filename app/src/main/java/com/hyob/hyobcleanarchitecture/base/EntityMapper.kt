package com.hyob.hyobcleanarchitecture.base

interface EntityMapper<D, E, V> {

    fun mapDataToEntity(data: D): E

    fun mapEntityToData(entity: E): D

    fun mapViewToData(view: V): D

    fun mapEntityToView(entity: E): V

}