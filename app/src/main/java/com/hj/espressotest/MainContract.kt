package com.hj.espressotest

interface MainContract {
    interface View {
        fun showSuccess()
        fun showFail()
    }

    interface Presenter {
        fun unsubscribe()
        fun login(userId: String, userPw: String)
    }
}