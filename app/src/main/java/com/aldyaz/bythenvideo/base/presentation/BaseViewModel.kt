package com.aldyaz.bythenvideo.base.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<INTENT> : ViewModel() {

    abstract fun onIntentReceived(intent: INTENT)

}