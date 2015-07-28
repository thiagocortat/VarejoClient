package br.com.devianto.anjo;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


import br.com.devianto.anjo.fragments.ProdutosFragment;
import br.com.thiagocortat.mylibrary.activities.BaseActivity;
import br.com.thiagocortat.mylibrary.base.DemoFragment;


public class MainActivity extends BaseActivity {

    private Drawer.Result result = null;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Add Profile
        final IProfile profile = new ProfileDrawerItem()
                .withName("João").withEmail("joao.teste@projetandoo.com")
                .setEnabled(false);
//                .withIcon(getResources().getDrawable(R.drawable.profile5));

        // Create the AccountHeader
        AccountHeader.Result headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.img_header)
                .addProfiles(profile)
                .withAlternativeProfileHeaderSwitching(false)
                .withProfileImagesClickable(false)
                .withSelectionListEnabled(false)
                .build();

        result = new Drawer()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Produtos").withIdentifier(0),
//                        new PrimaryDrawerItem().withName("Produtos").withIdentifier(1),
//                        new PrimaryDrawerItem().withName("RH").withIdentifier(2),
//                        new PrimaryDrawerItem().withName("CRM").withIdentifier(3),
//                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Configuração"),
                        new SecondaryDrawerItem().withName("Logout")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id, IDrawerItem drawerItem) {

                        if (i == 0) {
                            Fragment f = ProdutosFragment.newInstance();
                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                        } else {
                            Fragment f = DemoFragment.newInstance("");
                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
                        }
//                          else if (i == 1) {
//                            Fragment f = ProductFilterFragment.newInstance();
//                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
//                        } else if (i == 2) {
//                            Fragment f = EmployeeFilterFragment.newInstance();
//                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
//                        } else if (i == 3) {

//                        } else if (i == 5) {
//                            SettingsFragment f = SettingsFragment.newInstance();
//                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
//                        }

                    }

                })
                        //just use this with the Drawer.Builder
                .withSelectedItem(0)
                .withSavedInstance(savedInstanceState)
                .withFireOnInitialOnClick(true)
                .build();


//        result.openDrawer();
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        //react on the keyboard
        result.keyboardSupportEnabled(this, true);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (result.isDrawerOpen())
                    result.closeDrawer();
                else
                    result.openDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    public void getApi(){
//
//        final String apiKey = "07dce392be760422f40d67bc7945d7f1aaac7f4e";
//        final String JSON_FORMAT = "json";
//
//        GiantBombRestGameRepository rest = new GiantBombRestGameRepository();
//        rest.getApiService().obtainGamesByPage(apiKey, JSON_FORMAT, 0, 20,
//                new Callback<GiantBombResponse>() {
//                    @Override
//                    public void success(GiantBombResponse giantBombResponse, Response response) {
//                        Toast.makeText(getApplicationContext(), "Data found!", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Toast.makeText(getApplicationContext(), "OPS, some kind of problem had happend! :(", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//    }


//    public void getApiEstados(){
//
//
//        ProductRepository rest = new ProductRepository();
//        rest.getApiService().obtainEstados(new Callback<ApiEstados>() {
//            @Override
//            public void success(ApiEstados apiEstados, Response response) {
//                Toast.makeText(getApplicationContext(), "Data found!", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getApplicationContext(), "OPS, some kind of problem had happend! :(", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

//    public void getApiEstados2(){
//
//        final String credentials = "marcelosrodrigues@gmail.com" + ":" + "2pk0#3ty?";
//        String string = null;
//        try {
//            string = Base64.encodeToString(credentials.getBytes("UTF-8"), Base64.NO_WRAP);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        EstadosRepository rest = new EstadosRepository();
//        rest.getApiService().obtainEstados( "Basic bWFyY2Vsb3Nyb2RyaWd1ZXNAZ2xvYm8uY29tOjJwazAjM3R5Pw==", new Callback<String>() {
//            @Override
//            public void success(String apiEstados, Response response) {
//                Toast.makeText(getApplicationContext(), "Data found!", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getApplicationContext(), "OPS, some kind of problem had happend! :(", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }


}
