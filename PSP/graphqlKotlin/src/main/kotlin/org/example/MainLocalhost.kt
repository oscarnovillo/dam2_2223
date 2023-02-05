package org.example

import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.runBlocking
import org.example.localhost.PostQuery
import org.example.localhost.AddPostMutation
import org.example.rickMorty.FirstQuery

fun main(args: Array<String>) {


    val apolloClient = ApolloClient.Builder()
        .serverUrl("http://localhost:8080/graphql")
        .build()

    runBlocking {

        apolloClient.mutation(AddPostMutation("title","text","2222")).execute().data?.let {
            println(it.createPost.id)
        }
    }

    runBlocking {
        apolloClient.query(PostQuery()).execute().data?.let { data ->
            data.allPosts
               .forEach{ println(it?.id) }
        }
    }


}

