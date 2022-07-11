package com.at.common.demo

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.at.common.ImageConfig
import com.at.common.mvi.BaseMviActivity
import com.at.common.demo.databinding.ActivityMainBinding
import com.at.common.global.Constants
import com.at.common.imageUrl
import com.at.common.load
import com.at.common.utils.LoadingUtil
import com.melancholy.network.ApiFactory
import com.melancholy.widget.TitleBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import okhttp3.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

@AndroidEntryPoint
class MainActivity: BaseMviActivity<ActivityMainBinding, MainViewModel>() {

    override fun initialize(savedInstanceState: Bundle?) {
        getViewModel().print()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.print()
    }


    private suspend fun <T> fastest(defferreds: List<Deferred<T>>):T = select {
        fun cancelAll() = defferreds.forEach { it.cancel() }
        for (deferred in defferreds){
            deferred.onAwait{
//                cancelAll()
                Log.e("request","onAwait:${it}")
                it
            }
        }
    }

    private suspend fun testUrl(url:String):Flow<String>{
//            ApiFactory.getInstance().setBaseUrl(url)
           return flow {
               Log.e("request","url:${url}")
               val res =  ApiFactory.getInstance().create(ApiService::class.java).ping("${url}${Constants.URL_PATH_SUFFIX}ping").execute()
               val body = res.body()
               val contentLength = body?.msg
              if(body!=null && body.success()){
                  emit(url)
              }else{
                  emit("error:${url}")
              }
           }.catch {  }
    }



    private suspend fun testUrl1(url:String):String{
//            ApiFactory.getInstance().setBaseUrl(url)
            Log.e("request","url:${url}")
            try {
                val res =  ApiFactory.getInstance().create(ApiService::class.java).ping("${url}${Constants.URL_PATH_SUFFIX}ping").execute()
                val body = res.body()
                val contentLength = body?.msg
                if(body!=null && body.success()){
                    return url
                }else{
                    return ""
                }
            }catch (e:Exception){
                return ""
            }

    }

    override fun loadData() {



        val ijkMediaPlayer: IjkMediaPlayer? = null
//        lifecycleScope.launch(Dispatchers.IO) {
//            val img = "https://api.luofeiyu.tech/file/advertisement/2022/06/17/16554538982ulcoWty.webp"
//
//            val drawable = load(ImageConfig(img))
//             withContext(Dispatchers.Main) {
//                requireBinding().ivImage.setImageDrawable(drawable)
//            }
//        }
        requireTitleBar().apply {
            val width = resources.getDimension(com.at.common.R.dimen.dp_40).toInt()
            val height = resources.getDimension(com.at.common.R.dimen.dp_30).toInt()
            val logoParams = TitleBar.LayoutParams(width, height)
            logoParams.leftMargin = 20
            val logoBtn = addLeftImageButton(R.drawable.home_ic_title_bar_logo, android.R.id.content,logoParams)
             logoBtn.scaleType = ImageView.ScaleType.CENTER_CROP
        }

        GlobalScope.launch {

//          val deffreds =  Constants.BASE_URL.split(",").asFlow().map {
//                    testUrl("${it}/")
//            }
//
//            combine(deffreds){ data ->
//               for (item in data){
//                   item.collect{
//                       Log.e("request","res:${it}")
//                   }
//               }
//            }.collect()

        val startTime = System.currentTimeMillis()
         val deffreds =  Constants.BASE_URL.split(",").map {
                   async {
                       testUrl1("${it}/")
                   }
            }

            fun cancelAll() = deffreds.forEach { it.cancel() }
            val urls = arrayListOf<String>()

            for (def in deffreds){
                val res = def.await()
                if(res.isNotEmpty()){
                    urls.add(res)
//                    cancelAll()
                    Log.e("request","time:${System.currentTimeMillis() - startTime},res:${res}")
                }
            }

            if(urls.isNotEmpty()){
                Constants.BASE_URL = urls[0]
                ApiFactory.getInstance().setBaseUrl(urls[0] + Constants.URL_PATH_SUFFIX)
            }

            val bean = ApiFactory.getInstance().create(ApiService::class.java).getAgreements()
            if(bean.success()){
                Log.e("request","res:${bean}")
            }else{
                Log.e("request","res:${bean}")
            }

//            val defs = arrayListOf<Deferred<String?>>()
//            deffreds.collect{it ->defs.add(it)}
//            val result = fastest(deffreds)
//             Log.e("request","time:${System.currentTimeMillis() - startTime},res:${result}")
//            if(!result.isNullOrEmpty()){
//                ApiFactory.getInstance().setBaseUrl(result)
//            }
//            Log.e("request","res:${result}")
        }


        val url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.k73.com%2Fup%2Fsoft%2F2022%2F0520%2F171405_59710751.jpeg&refer=http%3A%2F%2Fpic.k73.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659150491&t=b37ed050c9cdc0ee2f9540880820f3db".imageUrl()
        val img = "https://p0.pipi.cn/mmdb/d2dad592c7e7e121f071f7129972a55a8f757.jpg?imageView2/1/w/160/h/220"
        val webp = "https://api.luofeiyu.tech/file/advertisement/2022/06/17/16554538982ulcoWty.webp"
        val gif = "https://api.luofeiyu.tech/file/advertisement/2022/07/04/1656897698V8tRv7fW.gif"
        requireBinding().ivImage.load(ImageConfig(gif))
//        requireBinding().ivImage.load(ImageConfig(img))

//        val request = ImageRequest.Builder(this@MainActivity)
//            .data(img)
//            .target(requireBinding().ivImage)
//            .error(R.mipmap.ic_launcher)
//            .build()
//        val disposable = imageLoader.enqueue(request)

        /*requireBinding().ivImage.load(config = ImageConfig("https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        placeholderId = R.drawable.ic_launcher_foreground, errorId = R.drawable.ic_launcher_background))*/
//        ToastUtils.show("开始加载数据")
//        lifecycleScope.launch(Dispatchers.IO) {
//            ApiFactory.getInstance().create(ApiService::class.java)
//                .baidu("http://www.baidu.com")
//        }


//        val okHttpClient =  ApiFactory.getInstance().getOkHttpClient()
//        val requestNet = Request.Builder().url("https://api.luofeiyu.tech").get().build()
//        okHttpClient.newCall(requestNet).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.d("TestOkHttp","fail${e.toString()}")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                Log.d("TestOkHttp",response.body.toString())
//            }
//
//        })
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onBackPressedSupport(): Boolean {
        LoadingUtil.showLoading(this)
        return true
    }

}