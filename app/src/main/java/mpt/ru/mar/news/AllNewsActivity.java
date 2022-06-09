package mpt.ru.mar.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import mpt.ru.mar.news.adapters.NewsAdapter;

public class AllNewsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    NewsAdapter adapter;
    RecyclerView recyclerNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        initialize();
        setRecyclerView();
    }

    private void initialize(){
        recyclerNews = findViewById(R.id.recyclerNews);
        databaseHelper = new DatabaseHelper(this);
    }

    private void setRecyclerView() {
        recyclerNews.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this, databaseHelper.getNewsData());
        recyclerNews.setAdapter(adapter);
    }
}