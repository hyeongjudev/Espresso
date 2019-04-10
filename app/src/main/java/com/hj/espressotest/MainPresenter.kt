package com.hj.espressotest

import android.util.Log


class MainPresenter(
        private val view: MainContract.View

) : MainContract.Presenter {

    companion object {
        private val TAG = MainPresenter::class.java.simpleName
        private const val USER_ID = "link"
        private const val USER_PASSWORD = "SamsungDev1!"
    }

    override fun unsubscribe() {
    }

    override fun login(userId: String, userPw: String) {
        if (userId == USER_ID && userPw == USER_PASSWORD) {
            view.showSuccess()
        } else {
            view.showFail()
        }

    }
}