package sg.edu.rp.c347.demodatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvId, tvDesc, tvDate;


    public TaskAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);

        tasks = objects;

        // store context object as we need to use it later
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //"inflate" row.xml as layout for view object
        View rowView = inflater.inflate(R.layout.task_row, parent, false);


        tvId = rowView.findViewById(R.id.textViewID);
        tvDate = rowView.findViewById(R.id.textViewDate);
        tvDesc = rowView.findViewById(R.id.textViewDesc);

        //Based on class
        Task currPosition = tasks.get(position);
        tvId.setText(Integer.toString(currPosition.getId()));
        tvDesc.setText(currPosition.getDescription());
        tvDate.setText(currPosition.getDate());
        return rowView;
    }
}
