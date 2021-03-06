package com.wishlist.common

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable

object WishLists : IntIdTable() {
    val name = varchar("name", 50)
    val owner = reference("owner", Users)
}

class WishList(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, WishList>(WishLists)

    var name  by WishLists.name
    var owner by User referencedOn WishLists.owner
}
