package com.example.musicplayerapp
/*
STEPS:
1.) Add Dependencies in build.gradle.kts(Module:app).
2.) Create DataClass file by copying the JSON code and then using JSON to Data Class
    File converter.
3.)Create an API Interface.
4.)Try to fetch and manage all data in main activity using retrofit builder and enqueue method.
5.)Populate data in view the way you want(UI/UX design)(Using RecyclerView).
   i.)Create ADAPTER.(ADAPTER acts as a middleman between the ui/ux of the app and the DB.)
   (It connects user interfaces with data sources)
   ii.)Link the Recycler View with ADAPTER.
 */
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.ui.theme.MusicPlayerAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {

    //Create recyclerView and attach it with the adapter:
    lateinit var myRecyclerView: RecyclerView;
    lateinit var myAdapter: MyAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        //Initialise myRecyclerView
        myRecyclerView = findViewById(R.id.recyclerView);

        //Creating the retrofit builder:
        val retrofitBuilder = Retrofit.Builder().baseUrl(
            "https://deezerdevs-deezer.p.rapidapi.com/"
        ).addConverterFactory(GsonConverterFactory.create()).build().create(
            ApiInterface::class.java
        );

        //Fetching and storing retrofit data:
        val retrofitData = retrofitBuilder.getData("arijit singh");

        //Performing Some operations on the fetched data:
        retrofitData.enqueue(object : Callback<MyMusicData?> {
            override fun onResponse(
                call: Call<MyMusicData?>,
                response: Response<MyMusicData?>
            ) {
                //If API call is success then this method will be executed:
                val dataList = response.body()?.data!!;
//                val textViews = findViewById<TextView>(R.id.helloText);
//                println(dataList.toString());
//                textViews.text = dataList.toString();

                //Initialise myAdapter:
                myAdapter = MyAdapter(this@MainActivity, dataList);

                //Connect RecyclerView and Adapter:
                myRecyclerView.adapter = myAdapter;

                //Set layout manager:
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("TAG : onResponse", "onResponse: "+response.body());
            }

            override fun onFailure(call: Call<MyMusicData?>, t: Throwable) {
                //If API call is a failure then this method will be executed:
                Log.d("TAG : onFailure", "onFailure: " + t.message)
            }
        })
    }
}
