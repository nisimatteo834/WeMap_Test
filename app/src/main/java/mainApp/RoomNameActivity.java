package mainApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.*;



import android.widget.AdapterView.OnItemClickListener;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



/**
 * Created by Stefano on 07/12/2017.
 */

public class RoomNameActivity extends AppCompatActivity {


    final ArrayList<String> IRooms = new ArrayList<>();
    final ArrayList<String> MRooms = new ArrayList<>();
    final ArrayList<String> NRooms = new ArrayList<>();
    final ArrayList<String> TRooms = new ArrayList<>();
    final ArrayList<String> ISMBRooms = new ArrayList<>();

    final ArrayList<String> ICorridor = new ArrayList<>();
    final ArrayList<String> MCorridor = new ArrayList<>();
    final ArrayList<String> NCorridor = new ArrayList<>();
    final ArrayList<String> TCorridor = new ArrayList<>();
    final ArrayList<String> ISMBCorridor = new ArrayList<>();

    final ArrayList<String> IRoomsIndex = new ArrayList<>();
    final ArrayList<String> MRoomsIndex = new ArrayList<>();
    final ArrayList<String> NRoomsIndex = new ArrayList<>();
    final ArrayList<String> TRoomsIndex = new ArrayList<>();
    final ArrayList<String> ISMBRoomsIndex = new ArrayList<>();

    final ArrayList<String> ICorridorIndex = new ArrayList<>();
    final ArrayList<String> MCorridorIndex = new ArrayList<>();
    final ArrayList<String> NCorridorIndex = new ArrayList<>();
    final ArrayList<String> TCorridorIndex = new ArrayList<>();
    final ArrayList<String> ISMBCorridorIndex = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {

            String rooms_string = this.loadJSONFromAsset(getApplicationContext());
            try {
                JSONObject rooms = new JSONObject(rooms_string);
                String[] buildings = new String[]{"I","T","M","N","ismb"};
                for (String s : buildings )
                {
                    rooms.get(s);
                    switch (s){
                        case "I":{
                            JSONArray roomsBuilding = (JSONArray) rooms.get(s);
                            for (int i =0; i<roomsBuilding.length(); i++){
                                JSONObject obj = roomsBuilding.getJSONObject(i);
                                if (obj.get("name").toString().matches(".*corr.*"))
                                {
                                    ICorridor.add(obj.get("name").toString());
                                    ICorridorIndex.add(obj.get("_id").toString());
                                }


                                else if (obj.get("name").toString().matches(".*[0-9]{1,2}[i]"))
                                {
                                    IRooms.add(obj.get("name").toString());
                                    IRoomsIndex.add(obj.get("_id").toString());
                                }
                                else
                                    ICorridor.add(obj.get("name").toString());
                                    ICorridorIndex.add(obj.get("_id").toString());

                            }

                             break;
                        }

                        case "ismb":{
                            JSONArray roomsBuilding = (JSONArray) rooms.get(s);
                            for (int i =0; i<roomsBuilding.length(); i++){
                                JSONObject obj = roomsBuilding.getJSONObject(i);
                                if (obj.get("name").toString().matches(".*\\d+.*"))
                                    ISMBRooms.add(obj.get("name").toString());
                                else
                                //if (obj.get("name").toString().contains("corr"))
                                    ISMBCorridor.add(obj.get("name").toString());
                            }

                            break;
                        }

                        case "N":{
                            JSONArray roomsBuilding = (JSONArray) rooms.get(s);
                            for (int i =0; i<roomsBuilding.length(); i++){
                                JSONObject obj = roomsBuilding.getJSONObject(i);
                                //checks if it's a room
                                if (obj.get("name").toString().matches(".*corr.*")){
                                    NCorridor.add(obj.get("name").toString());
                                    NCorridorIndex.add(obj.get("_id").toString());
                                }


                                else if (obj.get("name").toString().matches(".*[0-9]{1,2}[n]")){
                                    NRooms.add(obj.get("name").toString());
                                    NRoomsIndex.add(obj.get("_id").toString());

                                }
                                else
                                    //if (obj.get("name").toString().contains("corr"))
                                {
                                    NCorridor.add(obj.get("name").toString());
                                    NCorridorIndex.add(obj.get("_id").toString());

                                }
                            }

                            break;
                        }
                        case "T":{
                            JSONArray roomsBuilding = (JSONArray) rooms.get(s);
                            for (int i =0; i<roomsBuilding.length(); i++){
                                JSONObject obj = roomsBuilding.getJSONObject(i);
                                if (obj.get("name").toString().matches(".*corr.*"))
                                {
                                    TCorridor.add(obj.get("name").toString());
                                    TCorridorIndex.add(obj.get("_id").toString());

                                }

                                else if (obj.get("name").toString().matches(".*[0-9]{1,2}[t]"))
                                {
                                    TRooms.add(obj.get("name").toString());
                                    TRoomsIndex.add(obj.get("_id").toString());
                                }
                                else
                                //if (obj.get("name").toString().contains("corr"))
                                {
                                    TCorridor.add(obj.get("name").toString());
                                    TCorridorIndex.add(obj.get("_id").toString());

                                }
                            }

                            break;
                        }
                        case "M":{
                            JSONArray roomsBuilding = (JSONArray) rooms.get(s);
                            for (int i =0; i<roomsBuilding.length(); i++){
                                JSONObject obj = roomsBuilding.getJSONObject(i);
                                if (obj.get("name").toString().matches(".*corr.*"))
                                {
                                    MCorridor.add(obj.get("name").toString());
                                    MCorridorIndex.add(obj.get("_id").toString());

                                }

                                else if (obj.get("name").toString().matches(".*[0-9]{1,2}[M]"))
                                {
                                    MRooms.add(obj.get("name").toString());
                                    MRoomsIndex.add(obj.get("_id").toString());

                                }
                                else
                                    //if (obj.get("name").toString().contains("corr"))
                                {
                                    MCorridor.add(obj.get("name").toString());
                                    MCorridorIndex.add(obj.get("_id").toString());

                                }
                            }

                            break;
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        // Get the view from new_activity.xml
        setContentView(R.layout.second_screen);


        final ArrayList<String> roomName;
        ArrayList<String> corridorName;
        final ArrayList<String> roomNameIndex;
        final ArrayList<String> corridorNameIndex;


        if (InstitutionActivity.getChoices()[2].equals("Rooms I")) {
            roomName =  IRooms;
            corridorName = ICorridor;
            corridorNameIndex = ICorridorIndex;
            roomNameIndex = IRoomsIndex;
        } else if (InstitutionActivity.getChoices()[2].equals("Rooms M")){
            roomName = MRooms;
            corridorName = MCorridor;
            corridorNameIndex = MCorridorIndex;
            roomNameIndex = MRoomsIndex;

        } else if (InstitutionActivity.getChoices()[2].equals("Rooms N")) {
            roomName = NRooms;
            corridorName = NCorridor;
            corridorNameIndex = NCorridorIndex;
            roomNameIndex = NRoomsIndex;
        } else if (InstitutionActivity.getChoices()[2].equals("Rooms T")) {
            roomName = TRooms;
            corridorName = TCorridor;
            corridorNameIndex = TCorridorIndex;
            roomNameIndex = TRoomsIndex;

        }

        else {roomName = null; corridorName = null; roomNameIndex =null; corridorNameIndex=null;}

        final ArrayList<String> toShow;

        if (InOutCorrActivity.getInCorr().equals("Inside a room")) {
            toShow = roomName;
        } else {
            toShow = corridorName;
        }
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.row, toShow);
        ListView listView5 = (ListView) findViewById(R.id.newlistview);
        listView5.setAdapter(adapter5);


        // layout section
        TextView myTitle = (TextView) findViewById(R.id.textView);
        myTitle.setText("Now click where you are!" );
        ViewGroup.LayoutParams params = listView5.getLayoutParams();
        //params.height= 110;

        // Initialize a new GradientDrawable
        GradientDrawable gd = new GradientDrawable();

        // Specify the shape of drawable
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set the fill color of drawable
        gd.setColor(Color.DKGRAY); // make the background transparent

        // Create a 2 pixels width red colored border for drawable
        gd.setStroke(7, Color.CYAN); // border width and color

        // Make the border rounded
        gd.setCornerRadius(40.0f); // border corner radius

        // Finally, apply the GradientDrawable as TextView background
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            listView5.setBackground(gd);



        listView5.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id)
            {
                InstitutionActivity.setChoice(toShow.get(pos),4);
                String[] toPrint = InstitutionActivity.getChoices();


                if (InstitutionActivity.getChoices()[3].equals("Inside a room")){

                    InstitutionActivity.setChoice(roomNameIndex.get(pos),6);
                    MainActivity.parameters.put("allrooms_id", roomNameIndex.get(pos));

                    Intent myIntent = new Intent(RoomNameActivity.this,
                            RoomGridActivity.class);
                    startActivity(myIntent);

                }

                else {
                    InstitutionActivity.setChoice(corridorNameIndex.get(pos),6);
                    MainActivity.parameters.put("allrooms_id", corridorNameIndex.get(pos));

                    Intent myIntent = new Intent(RoomNameActivity.this,
                            SaveActivity.class);
                    startActivity(myIntent);

                    String message = toPrint[0] + "\n" + toPrint[1] + "\n" + toPrint[2] + "\n" + toPrint[3] + "\n" + toPrint[4];
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("rooms",
                            "raw", getPackageName()));

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
