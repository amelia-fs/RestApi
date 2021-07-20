package ac.id.pei.rpl.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {
    val btnlogin: Button = findViewById(R.id.btnlogin)
    val et_username: EditText = findViewById(R.id.editusername)
    val et_pass: EditText = findViewById(R.id.editpassword)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initAction()
    }
    fun initAction(){
        btnlogin.setOnClickListener {
            login()
        }
    }
    fun login(){
        val request = UserRequest()
        request.username = et_username.text.toString().trim()
        request.password = et_pass.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserAPI::class.java)
        retro.user(request).enqueue(object : Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error",t.message)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                MenuUtama()
            }

        })
    }
}