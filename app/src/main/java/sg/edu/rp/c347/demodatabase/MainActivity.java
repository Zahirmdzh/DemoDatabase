package sg.edu.rp.c347.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvItems;
    ArrayList<Task> alTask;
    ArrayAdapter aa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTasks);
        tvResults = findViewById(R.id.textViewResults);

        lvItems = findViewById(R.id.lvItems);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                db.getWritableDatabase();

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();


            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // Create the DBHelper object, passing in the
                        // activity's Context
                        DBHelper db = new DBHelper(MainActivity.this);

                        // Insert a task
                        ArrayList<Task> data = db.getTasks();
                        db.close();

                        String txt = "";
                        alTask = new ArrayList<>();

                        for (int i = 0; i < data.size(); i++) {

                            alTask.add(data.get(i));

                            Log.d("Database Content", i +". "+data.get(i));
                            txt += i + ". " + data.get(i) + "\n";
                        }
//                        tvResults.setText(txt);


                         aa = new TaskAdapter(MainActivity.this,R.layout.task_row,alTask);
                         lvItems.setAdapter(aa);

                    }
        });


    }
}
