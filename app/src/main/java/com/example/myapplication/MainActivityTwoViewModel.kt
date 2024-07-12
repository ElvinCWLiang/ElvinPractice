package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException



data class Basketball(val number: Int)

class GetDataUseCase {
    operator fun invoke(): List<Basketball> {
        return listOf(Basketball(1), Basketball(2))
    }
}

class MainActivityTwoViewModel(
    val fetchDataUseCase: GetDataUseCase,
    val basketball: Basketball
) : ViewModel() {


    var currentCount: Int = 0
    private val _countUp = MutableSharedFlow<Int>(0)
    val countUp = _countUp.asSharedFlow()


    var job: Job? = null

    var childJob: Job? = null

    var idd: Int = 0

    init {
        Log.i("evlog MainActivityTwoViewModel", "$11")
    }

//    fun getBaseBall(): Int {
//        baseBall
//    }

    private val _commodityIdFlow = MutableStateFlow<Int>(0)
    val commodityIdFlow = _commodityIdFlow.asStateFlow()

    fun setId(id: Int) {
        _commodityIdFlow.tryEmit(id)
    }

    fun getId(): Int {
        return commodityIdFlow.replayCache.firstOrNull() ?: 0
    }


    fun startGetCalculation(): List<Basketball> {
        return fetchDataUseCase.invoke()
    }



    private suspend fun coroutineWork() {
        coroutineScope {
            flow {
                emit(11)
                Log.i("evlog job2-1", "$11")
                delay(1000)
                Log.i("evlog job2-1", "$22")
                emit(22)
            }.launchIn(this)
        }
    }

    fun returnTwo(): Int {
        return returnThree()
    }

    fun returnThree(): Int {
        return 3
    }

    fun work() = viewModelScope.launch(Dispatchers.IO) {

        childJob = launch {
            delay(10000L)
        }

        flow {
            emit(1)
//            Log.i("evlog flow onEach emit", "${Thread.currentThread()}")
        }
            .flowOn(Dispatchers.IO)
            .onEach {
//                Log.i("evlog flow onEach IO", "${Thread.currentThread()}")
            }
//                .flowOn(Dispatchers.Main)
//                .onEach {
//                    Log.i("evlog flow onEach Main", "${Thread.currentThread()}")
//                }
            .collectLatest {
//                Log.i("evlog flow onEach collectLatest", "${Thread.currentThread()}")
            }
    }

    fun countUp() {
        val cc = _countUp.tryEmit(currentCount)
        println("evlog = $cc")
        currentCount++
    }

    fun getCountUp(): Int {
        return countUp.replayCache.firstOrNull() ?: 0
    }


    fun ff() {}


    fun doTaskA(id: Int) {
        println("[$id][${Thread.currentThread().name}] done task A")
    }

   suspend fun doTaskB(id: Int) {
//       runBlocking {
       println("[$id][${Thread.currentThread().name}] start task B")
           delay(1) // Simulation do something.
//       }
       println("[$id][${Thread.currentThread().name}] done task B")
   }

    fun doTaskC(id: Int) {
        println("[$id][${Thread.currentThread().name}] done task C")
    }
}