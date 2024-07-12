package com.example.myapplication

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MockkTest {

    @MockK
    lateinit var useCase: GetDataUseCase

    @MockK
    lateinit var basketBall: Basketball

    lateinit var viewModel: MainActivityTwoViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainActivityTwoViewModel(useCase, basketBall)
    }


    @Test
    fun MockViewModelTest() {
        every { useCase.invoke() } returns emptyList()


        val list = viewModel.startGetCalculation()

        viewModel.setId(2330)

        assertEquals(list, emptyList<Basketball>())
        assertEquals(viewModel.getId(), 2334)
    }

//    @MockK
//    lateinit var mother: Mother
//
//    lateinit var kid: Kid
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//        kid = Kid(mother)
//    }
//
//    @Test
//    fun wantMoney() {
//        every { mother.giveMoney() } returns 30
//        every { mother.inform(any()) } just Runs
//        kid.wantMoney()
//        assertEquals(30, kid.money)
//    }
//
//    class Mother {
//        fun inform(money: Int) {
//            println("媽媽我現在有 $money 元，我要跟你拿錢！")
//        }
//
//        fun giveMoney(): Int {
//            return 100
//        }
//    }
//
//    class Kid(private val mother: Mother) {
//        var money = 0
//            private set
//
//        fun wantMoney() {
//            mother.inform(money)
//            money += mother.giveMoney()
//        }
//    }
}