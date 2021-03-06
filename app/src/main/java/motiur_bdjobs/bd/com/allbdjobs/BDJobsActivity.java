package motiur_bdjobs.bd.com.allbdjobs;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.onesignal.OneSignal;

public class BDJobsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //    public static String FACEBOOK_URL = "https://www.facebook.com/chakrirkhobornets/";
//    public static String FACEBOOK_PAGE_ID = "381462145568985";
    private Animation myAnim;
    private Animation hyperspaceJumpAnimation;

    private ProgressBar proBar;
    private WebView jobacha;


  //  ImageButton btn11;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdjobs);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        // OneSignal Initialization
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //End toolbar

        //Floting action button

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                if (isNetworkConnected()) {
//
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//
//                    i.setData(Uri.parse("market://details?id=motiur_bdjobs.bd.com.allbdjobs"));
//                    startActivity(i);
//                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                } else {
//                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        //Floting action button

        //Button Inisilize
      //  btn11 = findViewById(R.id.btn11);


        // Animation
        myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);
        // btn11.setAnimation(myAnim);
        hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.motiur_anim);
      //  btn11.startAnimation(hyperspaceJumpAnimation);

        //End Animation


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#008000"));
        }

        //Improve wevView performance

        jobacha = (WebView) findViewById(R.id.web1);
        jobacha.clearCache(true);
        WebSettings webSettings = jobacha.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(false);

        //Test
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //

        webSettings.setDisplayZoomControls(false);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setBuiltInZoomControls(true);


        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);


        webSettings.setEnableSmoothTransition(true);

        jobacha.setInitialScale(1);
        jobacha.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        jobacha.setHorizontalScrollBarEnabled(false);


        ///


        jobacha.loadUrl("https://jobache.com/");
        jobacha.setWebViewClient(new mywebClient());

        proBar = (ProgressBar) findViewById(R.id.progressBar1);

//        jobacha.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent,
//                                        String contentDisposition, String mimetype,
//                                        long contentLength) {
//
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//            }
//        });

        jobacha.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading (WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                    return true;
                }
                return false;
            }
        });



    }

    //For webview progress bar loading

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
            setTitle(view.getTitle());

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
            //setTitle("Loading.....");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    //End webview progress bar



    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (jobacha.canGoBack()) {
            jobacha.goBack();
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    public void govjob(View view) {
//
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//
//        view.startAnimation(myAnim);
//
//        String webURL = "http://bangladesh.gov.bd/site/view/job_category";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//
//    }
//
//
//    public void bdJobs(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//
//        // String webURL = "http://jobs.bdjobs.com/jobsearch.asp";
//        String webURL = "http://jobs.bdjobs.com/m/searchcategories.aspx";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//
//    }
//
//    public void chakri(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//
//        String webURL = "http://www.chakri.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//
//    }
//
//    public void bdcareer(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://www.bd-career.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void jobsbd(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://www.jobsbd.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void chakrirkhobor(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "https://chakrirkhobor.net/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//
//    }
//
//    public void newbdjobs(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://bdjobs.com.bd/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void bdcareerorg(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "https://bd-career.org/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void noyadigonto(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://www.nayadigantajobs.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//
//    public void ejobs(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "https://ejobscircular.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void banglajobs(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://bangla-jobs.com";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void chakri_bd(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://www.chakrirkhobor.com.bd/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }
//
//    public void bdjobstoday(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
//        view.startAnimation(myAnim);
//
//        String webURL = "http://www.bdjobstoday.com/";
//
//        Intent intent = new Intent(getApplicationContext(), webActivity.class);
//        intent.putExtra("URL", webURL);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//
//    }

    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        // Locate MenuItem with ShareActionProvider
//        MenuItem item = menu.findItem(R.id.action_home);
//
//        // Fetch and store ShareActionProvider
//        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
//
//        if (mShareActionProvider != null) {
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=motiur_bdjobs.bd.com.allbdjobs");
//            shareIntent.setType("text/plain");
//            mShareActionProvider.setShareIntent(shareIntent);
//        }
//
//
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

//        if (id == R.id.action_facebook) {
//
//            if (isNetworkConnected()) {
//                Intent facebookIntent = openFacebook(this);
//                startActivity(facebookIntent);
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//            } else {
//                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
//            }
//
//
//        }

        if (id == R.id.action_home) {

            if (isNetworkConnected()) {

                Intent homeIntent = new Intent(this, BDJobsActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        if (id == R.id.action_ratings) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=motiur_bdjobs.bd.com.allbdjobs"));
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }

        if (id == R.id.action_update) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=motiur_bdjobs.bd.com.allbdjobs"));
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        if (id == R.id.action_more) {

            if (isNetworkConnected()) {
                Intent devAccount = new Intent(Intent.ACTION_VIEW);
                devAccount.setData(Uri.parse("http://play.google.com/store/apps/dev?id=6031616565948906744"));
                startActivity(devAccount);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }

        return super.onOptionsItemSelected(item);
    }

    //End menu view


    //Open facebook page

//    public static Intent openFacebook(Context context) {
//
//
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
//
//            boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
//            if (activated) {
//                if ((versionCode >= 3002850)) {
//
//                    return new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("fb://facewebmodal/f?href=" + FACEBOOK_URL));
//
//                } else {
//                    return new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));
//
//                }
//            } else {
//                return new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(FACEBOOK_URL));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            return new Intent(Intent.ACTION_VIEW,
//                    Uri.parse(FACEBOOK_URL));
//        }
//    }

    //End opne facebook page


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.primary) {
//            if (mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.");
//            }
            // Handle the camera action

            String webURL = "https://jobache.com/psc-results/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.resultMarksheet) {
            // Handle the camera action

            String webURL = "https://jobache.com/ssc-hsc-jsc-results/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.nuresult) {
            // Handle the camera action

            String webURL = "https://jobache.com/national-university-result/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }else if (id == R.id.moreResult) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=motiur_bdresult.bd.com.bdresult"));
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.reciteQuran) {
            String webURL = "http://tanzil.net/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.Quran) {
            String webURL = "https://quran.com/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.new_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/Otherjobs.aspx?jobtype=new";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.govt_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/Otherjobs.aspx?jobtype=government";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.non_govt_jobs) {
            String webURL = "http://www.chakrirkhobor.com.bd/non-government-jobs/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.it_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/jobsearch.aspx?fcatid=8";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.engineering_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/jobsearch.aspx?fcatid=5";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.textile_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/jobsearch.aspx?fcatid=6";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }else if (id == R.id.marketing_jobs) {
            String webURL = "http://jobs.bdjobs.com/m/jobsearch.aspx?fcatid=9";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }  else if (id == R.id.bank_jobs) {

            String webURL = "http://jobs.bdjobs.com/m/jobsearch.aspx?fcatid=2";


           // String webURL = " http://result.dpe.gov.bd/";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.forreign_jobs) {

            //String webURL = "https://www.naukri.com/international-jobs";
            String webURL = "http://jobs.bdjobs.com/m/Locationwisejobs.aspx?jobtype=overseas";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.notice) {

            String webURL = "http://www.bpsc.gov.bd/site/view/notices/%E0%A6%A8%E0%A7%8B%E0%A6%9F%E0%A6%BF%E0%A6%B6-";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        } else if (id == R.id.bcs_notice) {

            String webURL = "http://www.bpsc.gov.bd/site/view/psc_exam/BCS%20Examination/%E0%A6%AC%E0%A6%BF%E0%A6%B8%E0%A6%BF%E0%A6%8F%E0%A6%B8-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";

            Intent intent = new Intent(getApplicationContext(), webActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
