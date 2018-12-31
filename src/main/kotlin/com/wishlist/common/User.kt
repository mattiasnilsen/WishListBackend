package com.wishlist.common

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IdTable

object Users : IdTable<String>() {
    override var id = varchar("accountID", 50).entityId()
    val name = varchar("name", 50)
}

class User(id: EntityID<String>) : Entity<String>(id) {
     companion object : EntityClass<String, User>(Users)

    var name  by Users.name

    override fun toString(): String {
        return "User() $id $name"
    }
}

