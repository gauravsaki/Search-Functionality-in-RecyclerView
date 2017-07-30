package com.example.rashi.databinding;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rashi.databinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    List<Player> playerList;
    LinearLayoutManager linearLayoutManager;
    PlayerAdapter playerAdapter;
    EditText searchBox ;
    boolean isLoading =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        searchBox = (EditText) findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        //activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
     /*   User user = new User();
        user.setName("Gaurav");
        user.setEmail("gauravsa@fdf.com");
        activityMainBinding.setUser(user);
        activityMainBinding.setActivity(this);*/
        playerList = new ArrayList<>();
        loadData(0,5);
        playerAdapter= new PlayerAdapter(playerList);
        recyclerView.setAdapter(playerAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int visibleThreshold = 5;
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    onLoadMore();
                    isLoading = true;
                }
            }
        });
    }
    public void setLoaded() {
        isLoading = false;
    }


    public void loadData(int start,int end){
    for(int i=start;i<end;i++) {
        Player sachin = new Player();
        sachin.setPlayerName("Sachin");
        sachin.setImage("https://i0.wp.com/thelifehistory.com/wp-content/uploads/2017/01/sachin-tendulkar-profile-picture.jpg?resize=200%2C200");
        sachin.setCountry("India");
        sachin.setAboutPlayer("Sachin Tendulkar is regarded as one of the finest & greatest player of all times in the history of cricket. He has made the record of achieving century of centuries. Sachin also known as little master is the leading run scorer in ODI & tests. His early life history, a good list of records, achievements […]");
        playerList.add(sachin);

        Player virat = new Player();
        virat.setPlayerName("Virat");
        virat.setImage("http://thelifehistory.com/wp-content/uploads/2017/01/virat-kohli-profile.jpg");
        virat.setCountry("India");
        virat.setAboutPlayer("Virat Kohli, the captain of Indian Under-19 Cricket team under whom captaincy India had won the world cup is currently the best International batsman. This article will be related to various awards & achievements which he got for his excellent batting & captaincy skills. Virat Kohli Awards & Achievements List Previously, we had written an […]");
        playerList.add(virat);

        Player sehwag = new Player();
        sehwag.setPlayerName("Sehwag");
        sehwag.setImage("http://thelifehistory.com/wp-content/uploads/2017/01/virender-sehwag-life-history-300x150.jpg");
        sehwag.setCountry("India");
        sehwag.setAboutPlayer("Virender Sehwag, one of the most destructive batsman & former Indian team captain, the one who always take start of his game with a four or a six. He is an aggressive opening batsman & part time spinner & only Indian player to be awarded with wisden cricketer award. Virender Sehwag often called as Viru ");
        playerList.add(sehwag);
    }
    }

    public void filter(String filter){
        List<Player> filteredPlayerList = new ArrayList<>();
        for(Player player:playerList){
            if(player.getPlayerName().equalsIgnoreCase(filter)){
                filteredPlayerList.add(player);

            }
        }
        if(filteredPlayerList!=null && filteredPlayerList.size()>0) {
            playerList.clear();
            playerList.addAll(filteredPlayerList);
            playerAdapter.notifyDataSetChanged();
        }
    }


    public void onLoadMore() {
        //if (playerList.size() <= 5) {
            playerList.add(null);
            playerAdapter.notifyItemInserted(playerList.size() - 1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playerList.remove(playerList.size() - 1);
                    playerAdapter.notifyItemRemoved(playerList.size());

                    //Generating more data
                    int index = playerList.size();
                    int end = index + 5;
                    loadData(index,end);
                    playerAdapter.notifyDataSetChanged();
                    setLoaded();
                }
            }, 5000);
      /*  } else {
            Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
        }*/
    }
    public void onButtonClick(String email){
        User user = new User();
        user.setName("Rashi");
        user.setEmail("rashineam@fdf.com");
        activityMainBinding.setUser(user);
    }
}
