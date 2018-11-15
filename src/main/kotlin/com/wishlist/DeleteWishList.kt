package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.ApiGatewayResponse
import com.wishlist.common.Database
import org.apache.logging.log4j.LogManager


@Suppress("unused")
class DeleteWishList : RequestHandler<Map<String, Any>, ApiGatewayResponse> {
    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())

        val db = Database()

        val id = input["id"] as Int
        db.deleteWishList(id)

        return ApiGatewayResponse.build {
            statusCode = 200
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
