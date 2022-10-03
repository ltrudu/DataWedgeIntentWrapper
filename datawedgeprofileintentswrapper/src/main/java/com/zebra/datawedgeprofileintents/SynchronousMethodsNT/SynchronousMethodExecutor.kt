package com.zebra.datawedgeprofileintents.SynchronousMethodsNT

import android.content.Context
import android.util.Pair
import com.zebra.datawedgeprofileintents.DWSynchronousMethods
import com.zebra.datawedgeprofileintents.DWSynchronousMethods.EResults
import java.lang.reflect.Method
import java.util.concurrent.TimeoutException


class SynchronousMethodExecutor<T>(private val mContext: Context,
                                   private val mMethodName: String,
                                   private val mParam: Any,
                                   private val mParamClass: Class<*>,
                                   private val mDefaultResult: T) {

    private val mExecutionThread = Thread(SynchronousEnumerateScannersRunnable())
    private var mHasFinished = false
    private var totalElapsedMs = 0L

    var sleepTimerDurationMs = 50L
    var executionTimeoutMs = 3 * 1000L

    var results: Pair<EResults, T> = Pair(EResults.NONE, mDefaultResult)
        private set

    var exception: Exception? = null
        private set

    private inner class SynchronousEnumerateScannersRunnable: Runnable {
        override fun run() {
            try {
                val dwSynchronousMethods = DWSynchronousMethods(mContext)
                val method: Method = DWSynchronousMethods::class.java.getMethod(mMethodName, mParamClass)
                val result = method.invoke(dwSynchronousMethods, mParam)
                results = result as Pair<EResults, T>
            } catch (e: Exception) {
                results = Pair(EResults.FAILED, mDefaultResult)
                exception = e
                e.printStackTrace()
            } finally {
                // Even if an error occurs, we must terminate the action to avoid waiting an infinite loop
                mHasFinished = true
            }
        }
    }

    fun runInThreadAndWaitForEnd() {
        try {
            totalElapsedMs = 0L
            mExecutionThread.start()
            while (!mHasFinished) {
                // Sorry but we are not at school anymore...
                // And this is the only method I found to prevent ANR when
                // us use specific threading model (didn't found why on some
                // multi-thread apps it Countdownlatch usage throws an Android
                // Not Responding (ANR) exception.
                // This awful solution resolve the issue....
                // If you have a better solution, you are welcome
                // to do a PULL request on the code.
                Thread.sleep(sleepTimerDurationMs)

                // However, ANR still occurs. Notably on TC52X. So we add a timeout.
                totalElapsedMs += sleepTimerDurationMs
                if (isTimeoutReached()) {
                    killExecutionThread()
                    throw TimeoutException("Unable to get thread result in ${executionTimeoutMs}ms")
                }
            }
        } catch (e: InterruptedException) {
            // Ré-interruption to current thread if InterruptedException
            killExecutionThread()
        }
    }

    private fun isTimeoutReached() = totalElapsedMs > executionTimeoutMs

    private fun killExecutionThread() {
        if (mExecutionThread.isAlive)
            mExecutionThread.interrupt()
    }
}