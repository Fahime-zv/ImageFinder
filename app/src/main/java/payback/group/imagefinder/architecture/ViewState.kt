package payback.group.imagefinder.architecture

sealed class ViewState<out Model> {

    data class Loading(val onFrontOfContent: Boolean) : ViewState<Nothing>()

    data class Success<Model>(val model: Model) : ViewState<Model>()

    data class Empty(val message: String) : ViewState<Nothing>()

    sealed class Error : ViewState<Nothing>() {

        data class Local(val message: String?, val cause: Throwable?) : Error()

        data class Remote(val httpCode: Int, val message: String?) : Error()
    }
}