package com.example.globoplay_desafio_mobile

import app.cash.turbine.test
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import com.example.globoplay_desafio_mobile.presentation.principal.my_list.MyListViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MyListViewModelTest {

    private val useCases: UseCases = mockk()
    private lateinit var viewModel: MyListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { useCases.getFavMediaUseCase() } returns flow {
            emit(emptyList<MediaEntity>())
        }

        viewModel = MyListViewModel(useCases)
    }

    @Test
    fun `medias should start with empty list`() = runTest {
        viewModel.medias.test {
            assertEquals(awaitItem(), emptyList<MediaEntity>())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `medias should emit correct list of media entities`() = runTest {
        val mediaList = listOf(
            MediaEntity(
                id = 1,
                type = MediaType.SOAP,
                posterPath = "poster_path"
            )
        )

        every { useCases.getFavMediaUseCase() } returns flow {
            emit(emptyList())
            emit(mediaList)
        }

        viewModel = MyListViewModel(useCases)

        viewModel.medias.test {
            assertEquals(emptyList<MediaEntity>(), awaitItem())
            assertEquals(mediaList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `medias should handle exceptions gracefully`() = runTest {
        every { useCases.getFavMediaUseCase() } returns flow {
            throw RuntimeException("Test Exception")
        }

        viewModel = MyListViewModel(useCases)

        viewModel.medias.test {
            assertEquals(awaitItem(), emptyList<MediaEntity>())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
