package com.wishlist.triggers.common

import com.wishlist.common.ApiGatewayRequest
import com.wishlist.common.Response

data class CognitoTriggerInput(var region: String? = null,
                               var request: Request? = null,
                               var response: Response? = null,
                               var triggerSource: String? = null,
                               var userName: String? = null,
                               var UserPoolId: String? = null,
                               var resourcePath: String? = null,
                               var version: Int? = null,
                               var callerContext: ApiGatewayRequest.CallerContext? = null)

data class Request(var userAttributes: UserAttributes? = null,
                   var ValidationData: Any? = null)

@Suppress("unused")
data class Response(var autoConfirmUser: Boolean? = null,
                    var autoConfirmEmail: Boolean? = null,
                    var autoConfirmPhoneNumber: Boolean? = null)

data class UserAttributes(var email: String? = null,
                          var phoneNumber: String? = null,
                          var sub: String? = null,
                          var cognitoUserStatus: String? = null,
                          var phoneNumberVerified: Boolean? = null,
                          var emailVerified: Boolean? = null,
                          var cognitoEmailAlias: String? = null)
