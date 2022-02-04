package com.example.jetpackcompose.Retorfit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class ApiLogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val builder = request.newBuilder()
        val token = "691|VqvVWa40U9TOfZ6Hmo3CJqB3Z1UibPjPNmySS4SY"
        builder.header("x-api-version", "1.7")
        if (token != null && token.length != 0) {
            builder.header("Authorization", "Bearer " + token)
        }
        val newRequest = builder.build()

        val response = chain.proceed(newRequest)

        Log.e(LOG_TAG, "URL:" + newRequest.url)
        Log.e(LOG_TAG, "REQ:" + bodyToString(newRequest))

        return response
    }



    companion object {
        private const val LOG_TAG = "REQ_LOG"
        fun bodyToString(request: Request): String {
            return try {
                if (request.body != null) {
                    val copy = request.newBuilder().build()
                    val buffer = Buffer()
                    copy.body!!.writeTo(buffer)
                    return buffer.readUtf8()
                }
                ""
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}