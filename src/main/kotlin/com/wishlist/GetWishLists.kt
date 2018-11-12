package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.ApiGatewayResponse
import com.wishlist.common.WishList
import com.wishlist.common.WishListResponse
import com.wishlist.common.serialize
import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("unused")
class GetWishLists: RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    private fun retrieveAllWishLists() : List<WishList> {
        Database.connect("jdbc:postgresql://wishlist.cr5mv2wtlfks.eu-central-1.rds.amazonaws.com/wishlist", driver = "org.postgresql.Driver",
                user = "kerp", password = "")

        return transaction {
            WishList.all().toList()
        }
    }

    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())

        val serializedWishLists = retrieveAllWishLists().map(WishList::serialize)

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = WishListResponse(serializedWishLists)
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
