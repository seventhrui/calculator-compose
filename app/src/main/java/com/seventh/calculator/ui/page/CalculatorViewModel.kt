package com.seventh.calculator.ui.page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.seventh.calculator.data.KeyBean
import com.seventh.calculator.utils.CalculatorUtils
import com.seventh.calculator.utils.endWithMethod
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CalculatorViewModel: ViewModel() {
    var viewStates by mutableStateOf(CalculatorViewState())
    private val _viewEvents = Channel<CalculatorViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    fun dispatch(action: CalculatorViewAction) {
        when(action) {
            is CalculatorViewAction.UpdateContent -> updateContent(action.item)
            is CalculatorViewAction.CleanContent -> cleanContent()
            else -> {}
        }
    }

    private fun cleanContent() {
        viewStates = viewStates.copy(content = "")
    }

    private fun updateContent(item: KeyBean) {
        if (item.title == "＝") {
            viewStates = viewStates.copy(content = CalculatorUtils.dispatch(viewStates.content))
        } else if (viewStates.content.endWithMethod() && item.title.endWithMethod()) {
            viewStates = viewStates.copy(
                content = viewStates.content.substring(0,viewStates.content.length - 1)
            )
            viewStates = viewStates.copy(content = viewStates.content + item.title)
        } else if(viewStates.content == "计算错误") {
            viewStates = viewStates.copy(content = item.title)
        }else {
            viewStates = viewStates.copy(content = viewStates.content + item.title)
        }
    }
}

data class CalculatorViewState(
    val item: KeyBean = KeyBean(),
    val content: String = ""
)

sealed class CalculatorViewEvent {
    object ShowResult: CalculatorViewEvent()
}

sealed class CalculatorViewAction {
    object CleanContent: CalculatorViewAction()
    data class UpdateContent(val item: KeyBean): CalculatorViewAction()
}