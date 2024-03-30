package payback.group.imagefinder.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import payback.group.shared.FResult
import payback.group.shared.FResult.Error.Local
import payback.group.shared.FResult.Error.Remote

abstract class BaseViewModel<Model, ViewAction>(
    initialState: ViewState<Model> = ViewState.Loading(false)
) : ViewModel() {

    private val mutableViewStateFlow = MutableStateFlow(initialState)
    val viewStateFlow = mutableViewStateFlow.asStateFlow()


    protected val state: ViewState<Model>
        get() = mutableViewStateFlow.value

    protected suspend fun setState(state: ViewState<Model>) = mutableViewStateFlow.emit(state)

    protected suspend fun setLoadingState(onFrontOfContent: Boolean) = mutableViewStateFlow.emit(
        ViewState.Loading(onFrontOfContent)
    )

    protected suspend fun setEmptyState(message: String) = mutableViewStateFlow.emit(
        ViewState.Empty(message)
    )

    protected suspend fun setSuccessState(model: Model) = mutableViewStateFlow.emit(
        ViewState.Success(model)
    )
    protected suspend fun setLocalErrorState(
        message: String? = null,
        cause: Throwable?
    ) = mutableViewStateFlow.emit(ViewState.Error.Local(message, cause))
    protected suspend fun setRemoteErrorState(httpCode: Int, message: String?) =
        mutableViewStateFlow.emit(ViewState.Error.Remote(httpCode, message))
    protected suspend fun setErrorState(result: FResult.Error) {
        val errorState = when (result) {
            is Local -> ViewState.Error.Local(message = null, cause = result.cause)
            is Remote -> ViewState.Error.Remote(
                httpCode = result.httpCode,
                message = result.message
            )
        }
        mutableViewStateFlow.emit(errorState)
    }

    open fun dispatch(action: ViewAction) {
        // override if needed
    }
}