package com.wishlist.common

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable

object WishLists : IntIdTable() {
    val name = varchar("name", 50)
    val owner = varchar("owner", 50)
}

fun Entity<*>.serialize() = this.klass.dependsOnColumns.associate { it.name to this.readValues[it].toString() }

class WishList(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, WishList>(WishLists)

    var name  by WishLists.name
    var owner by WishLists.owner
}
