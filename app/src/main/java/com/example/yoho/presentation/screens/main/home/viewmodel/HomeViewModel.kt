package com.example.yoho.presentation.screens.main.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yoho.domain.usecase.MeetingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val meetingUseCase: MeetingUseCase
): ViewModel() {

    private val _scheduleMeetingState = mutableStateOf("")
    val scheduleMeetingState: State<>
}