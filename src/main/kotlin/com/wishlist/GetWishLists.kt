package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.*
import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("unused")
class GetWishLists: RequestHandler<ApigatewayRequest.Input, ApiGatewayResponse> {


    override fun handleRequest(input: ApigatewayRequest.Input, context: Context): ApiGatewayResponse {
        val userID = input.requestContext?.authorizer?.claims?.get("sub").orEmpty()

        LOG.info("Retrieving wish lists for user: $userID")

        val wishLists = Database().retrieveMyWishLists(userID)
        val newWishLists = transaction {
             wishLists.map { WishListResponseObject(it) }
        }

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = WishListResponse(newWishLists)
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
