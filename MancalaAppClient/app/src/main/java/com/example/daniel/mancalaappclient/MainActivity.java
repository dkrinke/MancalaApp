package com.example.daniel.mancalaappclient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Socket socket;
    public static Activity activity;

    private static final int SERVERPORT = 6000;
    private static final String SERVER_IP = "192.168.1.39";
//    private static final String SERVER_IP = "192.168.1.46";

    Handler updateConversationHandler;

    static boolean connected = false;
    TextView connectingMessage;
    RelativeLayout connectedLayout;

    private boolean didPlayerMove = false;
    private int playerChose = -1;

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;

    private Button store1;
    private Button store2;

    static public TextView playerTurn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        socket = null;

        connectingMessage = (TextView) findViewById(R.id.connecting);
        connectedLayout = (RelativeLayout) findViewById(R.id.connected);

        if(connected) {
            connectingMessage.setVisibility(View.INVISIBLE);
            connectedLayout.setVisibility(View.VISIBLE);
        }
        else {
            connectingMessage.setVisibility(View.VISIBLE);
            connectedLayout.setVisibility(View.INVISIBLE);
        }

        playerTurn = (TextView) findViewById(R.id.playerTurn);

        store1 = (Button) findViewById(R.id.store1);
        store2 = (Button) findViewById(R.id.store2);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        b10 = (Button) findViewById(R.id.button10);
        b11 = (Button) findViewById(R.id.button11);
        b12 = (Button) findViewById(R.id.button12);

        b1.setOnClickListener(myOnlyhandler);
        b2.setOnClickListener(myOnlyhandler);
        b3.setOnClickListener(myOnlyhandler);
        b4.setOnClickListener(myOnlyhandler);
        b5.setOnClickListener(myOnlyhandler);
        b6.setOnClickListener(myOnlyhandler);
        b7.setOnClickListener(myOnlyhandler);
        b8.setOnClickListener(myOnlyhandler);
        b9.setOnClickListener(myOnlyhandler);
        b10.setOnClickListener(myOnlyhandler);
        b11.setOnClickListener(myOnlyhandler);
        b12.setOnClickListener(myOnlyhandler);

        updateConversationHandler = new Handler();

        new Thread(new ClientThread()).start();
    }

    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    playerChose = 6;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                    playerChose = 7;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button3:
                    playerChose = 8;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 3 Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button4:
                    playerChose = 9;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 4 Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button5:
                    playerChose = 10;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 5 Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button6:
                    playerChose = 11;
                    didPlayerMove = true;
//                    Toast.makeText(MainActivity.this, "Button 6 Clicked", Toast.LENGTH_SHORT).show();
                    break;
//                case R.id.button7:
//                    Toast.makeText(MainActivity.this, "Button 7 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.button8:
//                    Toast.makeText(MainActivity.this, "Button 8 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.button9:
//                    Toast.makeText(MainActivity.this, "Button 9 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.button10:
//                    Toast.makeText(MainActivity.this, "Button 10 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.button11:
//                    Toast.makeText(MainActivity.this, "Button 11 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.button12:
//                    Toast.makeText(MainActivity.this, "Button 12 Clicked", Toast.LENGTH_SHORT).show();
//                    break;
            }
//            updateBoard();
//            Toast.makeText(MainActivity.this, board.getPlayerTurn() + "'s turn", Toast.LENGTH_SHORT).show();
        }
    };

    private void updateBoard() {
//        if(!board.isGameOver())
//            MainActivity.this.playerTurn.setText(board.getPlayerTurn() + "'s turn");
//
//        MainActivity.this.store2.setText(""+board.getHouses().get(0).getStones());
//        MainActivity.this.store1.setText(""+board.getHouses().get(1).getStones());
//
//        MainActivity.this.b1.setText(""+board.getStores().get(0).getStones());
//        MainActivity.this.b2.setText(""+board.getStores().get(1).getStones());
//        MainActivity.this.b3.setText(""+board.getStores().get(2).getStones());
//        MainActivity.this.b4.setText(""+board.getStores().get(3).getStones());
//        MainActivity.this.b5.setText(""+board.getStores().get(4).getStones());
//        MainActivity.this.b6.setText(""+board.getStores().get(5).getStones());
//        MainActivity.this.b7.setText(""+board.getStores().get(6).getStones());
//        MainActivity.this.b8.setText(""+board.getStores().get(7).getStones());
//        MainActivity.this.b9.setText(""+board.getStores().get(8).getStones());
//        MainActivity.this.b10.setText(""+board.getStores().get(9).getStones());
//        MainActivity.this.b11.setText(""+board.getStores().get(10).getStones());
//        MainActivity.this.b12.setText(""+board.getStores().get(11).getStones());
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {
            BufferedReader input = null;
            updateUIThread update = null;
            PrintWriter out = null;

            try {

                try {
                    if(socket == null) {
                        InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                        socket = new Socket(serverAddr, SERVERPORT);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(out == null)
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                if(!connected) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("RequestType", "Connect");
                        out.println(jsonObject.toString());
                    }catch (JSONException je){}
                }

                if(input == null)
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while(true)
                {
                    try {
                        String read = input.readLine();

                        JSONObject jsonObject = new JSONObject(read);

                        if(!connected && jsonObject.get("RequestType").toString().equals("ConnectionApproved")) {
                            connected = true;
                            JSONObject jsonObjectResponse = new JSONObject();
                            jsonObjectResponse.put("RequestType", "YourMove");
                            out.println(jsonObjectResponse.toString());
                        }

                        if(!connected && jsonObject.get("RequestType").toString().equals("YourMove")) {
                            connected = true;
                            JSONObject jsonObjectResponse = new JSONObject();
                            jsonObjectResponse.put("RequestType", "YourMove");
                            out.println(jsonObjectResponse.toString());
                        }

                        if (update == null)
                            update = new updateUIThread(read);

                        updateConversationHandler.post(update);
                    }catch (JSONException je){}
                }
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    class updateUIThread implements Runnable {
        private String msg;

        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            connected = true;
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Toast.makeText(MainActivity.this, "Client Says: "+ msg + "\n", Toast.LENGTH_SHORT).show();
        }
    }

    public void exit(View view)

    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        connected = false;
        Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
