package com.example.kotlin.training.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.util.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: DetailViewModel

    @Mock
    lateinit var fakeMediaProvider: MediaProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(fakeMediaProvider, coroutineTestRule.testDispatcher)
    }

    @Test
    fun showsMediaItemDetail() {
        val observer = mock<Observer<MediaItem>>()
        viewModel.item.observeForever(observer)
        val mediaItem = MediaItem(1,"","",MediaItem.Type.PHOTO)
        whenever(fakeMediaProvider.getItems()).thenReturn(listOf(mediaItem))
        viewModel.onCreate(1)
        verify(observer).onChanged(mediaItem)
    }
}