package com.invictus.common.sampleCode

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext


/** --------------------------------------- ViewModel ------------------------------------**/


//class CommunityCommonViewModel(
//    initialState: CommunityCommonState,
//    val repository: CommunityCommonRepository,
//) : MavericksViewModel<CommunityCommonState>(initialState) {
//
//    fun setOpenFromData(openFrom: CommunityOpenFromIdentifier) = setState { copy(openFrom = openFrom) }
//    fun initData(){
//
//    }
//
//
//
//    companion object : MavericksViewModelFactory<CommunityCommonViewModel, CommunityCommonState> {
//        override fun create(viewModelContext: ViewModelContext, state: CommunityCommonState): CommunityCommonViewModel {
//            val repository = CommunityCommonRepository()
//            return CommunityCommonViewModel(state, repository)
//        }
//    }
//
//}
//
//data class CommunityCommonState(
//    val openFrom: CommunityOpenFromIdentifier = CommunityOpenFromIdentifier.BOTTOM_NAVIGATION,
//
//    ) : MavericksState


/** --------------------------------------- ApplicationClass ------------------------------------ **/

//class MyApplication : Application() {
//
//    companion object {
//        private const val TAG = "MyApplication"
//        private var instance: MyApplication? = null
//        fun context(): Context {
//            return instance!!.applicationContext
//        }
//    }
//
//
//    override fun onCreate() {
//        super.onCreate()
//    }
//}

/** -------------------------------------- ComposeNavigation ------------------------------------ **/

//sealed class TestScreen(val route: String) {
//    object Intro : TestScreen("intro")
//    object Questions : TestScreen("questions/{page}") {
//        fun createRoute(page: Int) = "questions/${page}"
//    }
//    object Analysis : TestScreen("analysis")
//}

//@Composable
//fun Box{
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = destination
//    ) {
//        composable(TestScreen.Intro.route) { CommunityPostList(navController) }
//    }
//}