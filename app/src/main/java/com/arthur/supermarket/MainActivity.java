package com.arthur.supermarket;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arthur.supermarket.Model.Product;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TicketAdapter adapter;
    private FloatingActionButton addProductFloatingButton;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private Activity activity;
    final ArrayList<Product> products = new ArrayList<Product>();

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initToolbar();
        initFloatingButton();
        initRecyclerView();



        products.clear();


        adapter = new TicketAdapter(products ,getApplicationContext(), recyclerView);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Voce cancelou o scanner", Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(this, "Item adicionado ao carrinho", Toast.LENGTH_LONG).show();
                products.add(new Product(result.getContents() , "", "0,0", 1));
                adapter = new TicketAdapter(products ,getApplicationContext(), recyclerView);
                recyclerView.setAdapter(adapter);

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by defaul


        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                //adapter.getFilter().filter(newText);
                //adapter.getFilter().filter(newText+";"+locationButton.getText()+";"+categoryButton.getText());
                return true;
            }

            public boolean onQueryTextSubmit(String query) {

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_options:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void initFloatingButton(){
        addProductFloatingButton = (FloatingActionButton) findViewById(R.id.scan_fab);

        addProductFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }
            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void hideViews() {
        //appBarLayout.animate().translationY(-appBarLayout.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        //FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) createTicketFloatingButton.getLayoutParams();
        //int fabBottomMargin = lp.bottomMargin;
        //createTicketFloatingButton.animate().translationY(createTicketFloatingButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        //appBarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //createTicketFloatingButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
}
