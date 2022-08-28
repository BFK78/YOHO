package com.example.yoho.presentation.screens.main.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoho.common.util.Resource
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.usecase.MeetingUseCase
import com.example.yoho.presentation.screens.main.util.MeetingModel
import com.example.yoho.presentation.screens.main.util.ScheduledMeetingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val meetingUseCase: MeetingUseCase
): ViewModel() {

    private val _scheduleMeetingState = mutableStateOf(MeetingModel())
    val scheduleMeetingState: State<MeetingModel> = _scheduleMeetingState

    private val _scheduledMeetingState = mutableStateOf(ScheduledMeetingModel())
    val scheduledMeetingState: State<ScheduledMeetingModel> = _scheduledMeetingState

    fun scheduleMeeting(meeting: Meeting) = viewModelScope.launch {
        meetingUseCase.scheduleMeeting(meeting = meeting).onEach {
            when(it) {
                is Resource.Loading -> {
                    _scheduleMeetingState.value = scheduleMeetingState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {

                    _scheduleMeetingState.value = scheduleMeetingState.value.copy(
                        data = it.data,
                        isLoading = false
                    )

                }
                is Resource.Error -> {

                    _scheduleMeetingState.value = scheduleMeetingState.value.copy(
                        message = it.message!!,
                        isLoading = false
                    )

                }
            }
        }.launchIn(this)
    }

    fun getAllCompletedMeeting() = viewModelScope.launch {

        meetingUseCase.getAllCompletedMeeting().onEach {

            when(it) {
                is Resource.Loading -> {

                    _scheduledMeetingState.value = scheduledMeetingState.value.copy(
                        isLoading = true,
                        data = it.data?:emptyList()
                    )

                }

                is Resource.Success -> {


                    _scheduledMeetingState.value = scheduledMeetingState.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }

                is Resource.Error -> {

                    _scheduledMeetingState.value = scheduledMeetingState.value.copy(
                        isLoading = false,
                        message = it.message!!,
                        data = it.data
                    )

                }
            }

        }.launchIn(this)

    }

}