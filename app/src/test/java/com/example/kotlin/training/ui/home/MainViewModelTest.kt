package com.example.kotlin.training.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.kotlin.training.data.FakeMediaProvider
import com.example.kotlin.training.data.Filter
import com.example.kotlin.training.data.MediaItem
import com.example.kotlin.training.ui.Event
import com.example.kotlin.training.util.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: MainViewModel
    private val fakeMediaProvider = FakeMediaProvider()

    @Before
    fun setUp() {
        viewModel = MainViewModel(fakeMediaProvider, coroutineTestRule.testDispatcher)
    }

    @Test
    fun progressIsSetToVisibleWhenFilterIsClicked() =
        coroutineTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.progressVisible.observeForever(observer)
        viewModel.onFilteredClicked(Filter.None)
        verify(observer).onChanged(true)
    }

    @Test
    fun onItemClickedNavigateToDetail() = coroutineTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Event<Int>>>()
        viewModel.navigateToDetail.observeForever(observer)
        val mediaItem = MediaItem(1, "", "", MediaItem.Type.PHOTO)
        viewModel.onItemClicked(mediaItem)
        verify(observer).onChanged(Event(1))
    }

    @Test
    fun updateItemsWhenOnFilterClicked() = coroutineTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<List<MediaItem>>>()
        viewModel.items.observeForever(observer)
        viewModel.onFilteredClicked(Filter.None)
        verify(observer).onChanged(fakeMediaProvider.getItems())
    }

}