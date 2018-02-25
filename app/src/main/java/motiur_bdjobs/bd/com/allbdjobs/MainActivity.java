package motiur_bdjobs.bd.com.allbdjobs;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    public static String FACEBOOK_URL = "https://www.facebook.com/chakrirkhobornets/";
    public static String FACEBOOK_PAGE_ID = "381462145568985";


    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ImageButton)findViewById(R.id.btn1);
        btn2 = (ImageButton)findViewById(R.id.btn2);
        btn3 = (ImageButton)findViewById(R.id.btn3);
        btn4 = (ImageButton)findViewById(R.id.btn4);
        btn5 = (ImageButton)findViewById(R.id.btn5);
        btn6 = (ImageButton)findViewById(R.id.btn6);
        btn7 = (ImageButton)findViewById(R.id.btn7);
        btn8 = (ImageButton)findViewById(R.id.btn8);
        btn9 = (ImageButton)findViewById(R.id.btn9);
        btn10 = (ImageButton)findViewById(R.id.btn10);
        btn11 = (ImageButton)findViewById(R.id.btn11);

        ActionBar motiurbar=getSupportActionBar();
        if (motiurbar != null) {
            motiurbar.setDisplayHomeAsUpEnabled(true);
            motiurbar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView_main);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        MobileAds.initialize(this, "ca-app-pub-4951262838901192~9336209793");



    }


    public void govjob(View view){

        String webURL = "http://bangladesh.gov.bd/site/view/job_category";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);



    }


    public void bdJobs(View view){

        String webURL = "http://www.bdjobs.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);



    }

    public void chakri(View view){

        String webURL = "http://www.chakri.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);



    }

    public void bdcareer(View view){

        String webURL = "http://www.bd-career.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }
    public void jobsbd(View view){

        String webURL = "http://www.jobsbd.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }
    public void chakrirkhobor(View view){

        String webURL = "https://chakrirkhobor.net/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }
    public void newbdjobs(View view){

        String webURL = "http://bdjobs.com.bd/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }

    public void bdcareerorg(View view){

        String webURL = "https://bd-career.org/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }

    public void noyadigonto(View view){

        String webURL = "http://www.nayadigantajobs.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }


    public void ejobs(View view){

        String webURL = "https://ejobscircular.com/";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }

    public void banglajobs(View view){

        String webURL = "http://bangla-jobs.com";

        Intent intent = new Intent(getApplicationContext(), webActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);

    }

    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_facebook) {

            if (isNetworkConnected()) {
                Intent facebookIntent = openFacebook(this);
                startActivity(facebookIntent);
            } else {
                Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
            }




        }

        if(id==R.id.action_ratings){

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=chakrirkhobor.bd.com.chakrirkhobor"));
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
            }


        }


        if(id==R.id.action_apps){

            if (isNetworkConnected()) {
                Intent devAccount = new Intent(Intent.ACTION_VIEW);
                devAccount.setData(Uri.parse("http://play.google.com/store/apps/dev?id=6031616565948906744"));
                startActivity(devAccount);
            } else {
                Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
            }


        }



        return super.onOptionsItemSelected(item);
    }

    //End menu view


    //Open facebook page

    public static Intent openFacebook(Context context){


        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

            boolean activated =  packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if(activated){
                if ((versionCode >= 3002850)) {

                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://facewebmodal/f?href=" + FACEBOOK_URL));

                } else {
                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));

                }
            }else{
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse(FACEBOOK_URL));
            }
        } catch (PackageManager.NameNotFoundException e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(FACEBOOK_URL));
        }
    }

    //End opne facebook page
}
