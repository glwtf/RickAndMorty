package ru.glwtf.rickandmorty

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import ru.glwtf.rickandmorty.data.network.ApiClient

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ru.glwtf.rickandmorty", appContext.packageName)
    }

    @Test
    fun test_request() = runBlocking{
        val page = 1
        val response = ApiClient.getCharacter(page)
        println(response.toString())
    }
}