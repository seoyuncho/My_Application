package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private ImageView btn_google_login, btn_kakao_login;
    private static final String TAG = "LoginActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_google_login = findViewById((R.id.google_login));
        btn_kakao_login = findViewById(R.id.kakao_login);


        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();


                Boolean isEmptyText = TextUtils.isEmpty(userID) || TextUtils.isEmpty(userPass);

                if(isEmptyText) { //editText가 비어있다면
                    Toast.makeText(getBaseContext(), "Please fill in the remaining blanks", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String receivedUserID = response.getString("userID");
                                String receivedUserPassword = response.getString("userPassword");
                                String receivedUserName = response.getString("userName");
                                String receivedUserAge = response.getString("userAge");

                                Toast.makeText(getApplicationContext(),"Login success!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, PlayerActivity.class);
                                intent.putExtra("userID", receivedUserID);
                                intent.putExtra("userPassword", receivedUserPassword);
                                intent.putExtra("userName", receivedUserName);
                                intent.putExtra("userAge", receivedUserAge);
                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());
                        Toast.makeText(getApplicationContext(),"Log in faile: server error.",Toast.LENGTH_SHORT).show();
                    }
                };

                // 서버로 Volley를 이용해서 요청을 함.
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });


        // 구글 로그인 세팅
        setResultSignUp();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
//                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                resultLauncher.launch(signInIntent);
            }
        });


        // 카카오톡 로그인 세팅
        Function2<OAuthToken,Throwable,Unit> callback =new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            // 콜백 메서드 ,
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Log.e(TAG,"CallBack Method");
                //oAuthToken != null 이라면 로그인 성공
                if(oAuthToken!=null){
                    // 토큰이 전달된다면 로그인이 성공한 것이고 토큰이 전달되지 않으면 로그인 실패한다.
                    handleKakaoLogin();

                }else {
                    //로그인 실패
                    Log.e(TAG, "invoke: login fail" );
                    Toast.makeText(getApplicationContext(),"Kakao login authentication failed",Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        };

        btn_kakao_login.setOnClickListener(new View.OnClickListener() {
            //KakaoLogin 창을 띄운다.
            @Override
            public void onClick(View view) {
                // 해당 기기에 카카오톡이 설치되어 있는 확인
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }else{
                    // 카카오톡이 설치되어 있지 않다면
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });
    }

    private void setResultSignUp() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // If the result is received normally, execute the conditional statement
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());

                        // Function to output to the log using the account information to be implemented below.
                        handleGoogleLogIn(task);
                    }
                }
            }
        );
    }

    private void handleGoogleLogIn(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String accountId = account != null ? account.getId() : "";

            Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        boolean success = response.getBoolean("success");
                        if (success) { // 확인에 성공한 경우
                            String receivedUserID = response.getString("userID");
                            String receivedUserPassword = response.getString("userPassword");
                            String receivedUserName = response.getString("userName");
                            String receivedUserAge = response.getString("userAge");

                            Toast.makeText(getApplicationContext(),"Login success!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, PlayerActivity.class);
                            intent.putExtra("userID", receivedUserID);
                            intent.putExtra("userPassword", receivedUserPassword);
                            intent.putExtra("userName", receivedUserName);
                            intent.putExtra("userAge", receivedUserAge);
                            startActivity(intent);
                        } else { // 로그인에 실패한 경우
                            Toast.makeText(getApplicationContext(),"No such account",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            intent.putExtra("userID", accountId);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error.Response", error.toString());
                    Toast.makeText(getApplicationContext(),"Log in failed: server error",Toast.LENGTH_SHORT).show();
                }
            };

            // 서버로 Volley를 이용해서 요청을 함.
            UserRequest userRequest = new UserRequest(accountId, responseListener, errorListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(userRequest);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failed", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void handleKakaoLogin() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                if (user != null) {

                    String accountId = user.getId().toString();

                    Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean success = response.getBoolean("success");
                                if (success) { // 확인에 성공한 경우
                                    String receivedUserID = response.getString("userID");
                                    String receivedUserPassword = response.getString("userPassword");
                                    String receivedUserName = response.getString("userName");
                                    String receivedUserAge = response.getString("userAge");

                                    Toast.makeText(getApplicationContext(),"Login success!",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, PlayerActivity.class);
                                    intent.putExtra("userID", receivedUserID);
                                    intent.putExtra("userPassword", receivedUserPassword);
                                    intent.putExtra("userName", receivedUserName);
                                    intent.putExtra("userAge", receivedUserAge);
                                    startActivity(intent);
                                } else { // 로그인에 실패한 경우
                                    Toast.makeText(getApplicationContext(),"No such account",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                    intent.putExtra("userID", accountId);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error.Response", error.toString());
                            Toast.makeText(getApplicationContext(),"Log in failed: server error",Toast.LENGTH_SHORT).show();
                        }
                    };

                    // 서버로 Volley를 이용해서 요청을 함.
                    UserRequest userRequest = new UserRequest(accountId, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(userRequest);

                } else {
                    Toast.makeText(getApplicationContext(),"Unexpected Error",Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }
}