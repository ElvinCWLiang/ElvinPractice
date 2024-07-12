package com.example.myapplication

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass

import org.junit.Test

class GetDataUseCaseTest {

    @RelaxedMockK
    lateinit var useCase: GetDataUseCase

    @MockK(relaxed = true)
    lateinit var useCasee: GetDataUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    operator fun invoke() {
//        every { useCase.invoke() } returns emptyList()
        val list = useCase.invoke()
        val listt = useCasee.invoke()
        println("evlog $list")
        println("evlog $listt")
//        assertEquals(2, list.size)
    }
}