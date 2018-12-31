package com.wishlist.triggers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.Database
import com.wishlist.triggers.common.CognitoTriggerInput
import org.apache.logging.log4j.LogManager


@Suppress("unused")
class PostConfirmation: RequestHandler<CognitoTriggerInput, CognitoTriggerInput> {

    override fun handleRequest(input: CognitoTriggerInput, context: Context): CognitoTriggerInput {

        val userID   = input.request?.userAttributes?.sub.orEmpty()
        val userName = input.userName.orEmpty()

        if(userID.isEmpty() || userName.isEmpty()) {
            LOG.error("Cannot create user with empty user ID ('$userID') or userName ('$userName')")
        } else {
            LOG.info("Creating user with ID: $userID and user name: $userName")
            Database().createUser(userName, userID)
        }
        return input
    }

    companion object {
        private val LOG = LogManager.getLogger(PostConfirmation::class.java)
    }
}
