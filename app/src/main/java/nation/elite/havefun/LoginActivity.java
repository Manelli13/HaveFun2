package nation.elite.havefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "login";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private EditText mLogin;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = findViewById(R.id.editText_login);
        mPassword = findViewById(R.id.editText_pwd);
    }

    public void onClickLog(View v){

        Intent mapIntent = new Intent(this, MapsActivity.class);
        sendAndRequestResponse("http://172.20.10.6:52886/Service1.svc/LoginToHavFun");
        startActivity(mapIntent);

    }

    private void sendAndRequestResponse(String url) {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Login", "issou@jvc.com" );
            jsonBody.put("Password", "chankla");

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO: handle success
                    Log.i(TAG, "issou");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.i(TAG, "error volley : "+error.getMessage());

                    //TODO: handle failure
                }
            });
            Volley.newRequestQueue(this).add(stringRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
