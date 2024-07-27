package com.example.reservations.data

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RealProviderRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ProviderRepository {
    override fun getProviderAvailability(): ProviderAvailability? {
        val json = context.assets.open("provider_availability.json").bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(ProviderAvailability::class.java)
        return adapter.fromJson(json)
    }
}