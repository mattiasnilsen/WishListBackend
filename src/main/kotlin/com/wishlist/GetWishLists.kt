package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.*
import org.apache.logging.log4j.LogManager

@Suppress("unused")
class GetWishLists: RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())

        val db = Database()
        val serializedWishLists = db.retrieveAllWishLists().map(WishList::serialize)

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = WishListResponse(serializedWishLists)
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
