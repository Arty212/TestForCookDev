package com.example.testforcookdev;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testforcookdev.api.APIHelper;
import com.example.testforcookdev.model.Recipe;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recipesList;
    private RecipeAdapter recipeAdapter;
    private SwipeRefreshLayout swipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesList = findViewById(R.id.list);
        recipesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                loadRecipes();
            }
        });

        recipeAdapter = new RecipeAdapter();
        recipesList.setAdapter(recipeAdapter);
        recipeAdapter.setRecipeAdapterListener(new RecipeAdapter.RecipeAdapterListener() {
            @Override
            public void onClick(Recipe recipe) {
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                intent.putExtra(RecipeActivity.RECIPE_URL, recipe.content);
                startActivity(intent);
            }
        });

        swipeRefresh.setRefreshing(true);
        loadRecipes();
    }

    private void loadRecipes() {
        APIHelper.getInstance().getRecipes(14, new APIHelper.APIHelperCallback<List<Recipe>>() {
            @Override
            public void onResponse(List<Recipe> response) {
                recipeAdapter.setData(response);
                recipeAdapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onError() {
                Snackbar
                        .make(recipesList, "Что-то пошло не так...", Snackbar.LENGTH_LONG)
                        .show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }


}
