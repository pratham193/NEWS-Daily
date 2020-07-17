package com.example.news

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_on_click.*


class OnClick : AppCompatActivity() {



    companion object {
        var active :Boolean=false
    }
    override fun onStart() {
        super.onStart()
        active=true
    }

    override fun onStop() {
        super.onStop()
        active=false
    }

    lateinit var webView:WebView
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        val prif=PreferenceClass(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_click)

        if(prif.getcount()>=3){
            if (MainActivity.mInterstitialAd.isLoaded) {
                MainActivity.mInterstitialAd.show()
                prif.clearCount()
            } else {
                prif.setcount(prif.getcount()+1)
                    Toast.makeText(this,"not shown",Toast.LENGTH_SHORT).show()
            }
        }




        webView=findViewById(R.id.Webview)
        progressBar=findViewById(R.id.progressBar)
        checkConnection()





        MobileAds.initialize(this) {}
        var mAdView:AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)





        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        if(MainActivity.url=="http://www.navakal.org/images/epaper.pdf"){

            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.navakal.org/images/epaper.pdf"))
            startActivity(browserIntent)
        }else {
            webView.loadUrl(MainActivity.url)
        }


        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.settings.javaScriptEnabled = true





        webView.webChromeClient = object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                texttitle.text="Loading..."
                progressBar.visibility=View.VISIBLE
                Loadmain.visibility=View.VISIBLE
                progressBar.progress = newProgress


                if(newProgress==100){

                    progressBar.visibility=View.GONE
                    Loadmain.visibility=View.GONE

                    if (view != null) {
                        texttitle.text=view.title

                    } else  texttitle.text=""
                }

                super.onProgressChanged(view, newProgress)
            }
        }
    refresh.setOnClickListener{
        checkConnection()
        webView.reload()
    }

    btnNoConnection.setOnClickListener{
        checkConnection()
    }
    home.setOnClickListener {

        finish()
    }

        Share.setOnClickListener {
            val msg="${webView.title} \n\n You can this Above news on " +
                    "Below given link ${webView.url} \n\n Read this exiting news also on This App which provide news papers of 7 " +
                    "languages,there websites and live channels also (130+ news papers in one App) <link> "

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, msg)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
            return super.onKeyDown(keyCode, event)
    }

    fun checkConnection(){
        val connectivityManager:ConnectivityManager= this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val wifi:NetworkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilenetwork:NetworkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if(wifi.isConnected){
            webView.visibility=View.VISIBLE
            refresh.visibility=View.VISIBLE
            relativeLayout.visibility=View.GONE
           webView.reload()

        }else if(mobilenetwork.isConnected) {
            webView.visibility=View.VISIBLE
            refresh.visibility=View.VISIBLE
            relativeLayout.visibility=View.GONE
           webView.reload()
        }else{
            webView.visibility=View.GONE
            refresh.visibility=View.GONE
            relativeLayout.visibility=View.VISIBLE
        }

    }





}

