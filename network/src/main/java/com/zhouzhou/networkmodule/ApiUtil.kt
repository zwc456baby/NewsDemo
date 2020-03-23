package com.zhouzhou.networkmodule

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class ApiUtil private constructor() {
    private val retrofitHashMap = HashMap<String, Retrofit>()
    private var mOkHttpClient: OkHttpClient? = null

    private val mBaseUrl: String = "https://news.zwc365.com"

    companion object {
        val getInstant: ApiUtil = ApiUtil()

        fun <T> getService(service: Class<T>): T {
            return getInstant.getRetrofit(service).create(service)
        }
    }

    protected fun getRetrofit(service: Class<*>): Retrofit {
        val mRetrofit: Retrofit? = retrofitHashMap.get(mBaseUrl)
        if (mRetrofit != null) {
            return mRetrofit
        }
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(mBaseUrl)
        retrofitBuilder.client(getOkHttpClient())
//        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        val retrofit = retrofitBuilder.build()
        retrofitHashMap.put(mBaseUrl, retrofit)
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        var okHttpClient: OkHttpClient? = mOkHttpClient
        if (okHttpClient == null) {
            val okHttpClientBuilder = OkHttpClient.Builder()
            // 添加缓存、日志等拦截器
//            if (getInterceptor() != null) {
//                okHttpClientBuilder.addInterceptor(getInterceptor())
//            }
//            val cacheSize = 100 * 1024 * 1024 // 10MB
//            okHttpClientBuilder.cache(Cache(iNetworkRequiredInfo.getApplicationContext().getCacheDir(), cacheSize))
//            okHttpClientBuilder.addInterceptor(CommonRequestInterceptor(iNetworkRequiredInfo))
//            okHttpClientBuilder.addInterceptor(CommonResponseInterceptor())
//            if (iNetworkRequiredInfo != null && iNetworkRequiredInfo.isDebug()) {
//                val httpLoggingInterceptor = HttpLoggingInterceptor()
//                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
//            }
            okHttpClient = okHttpClientBuilder.build()
            mOkHttpClient = okHttpClient
            return okHttpClient
        }
        return okHttpClient
    }

    fun <T> applySchedulers(observer: Observer<T>): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream ->
            val observable = upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            //                    .map(getAppErrorHandler())
            //                    .onErrorResumeNext(HttpErrorHandler<T>()) as Observable<T>
            observable.subscribe(observer)
            observable
        }
    }


}