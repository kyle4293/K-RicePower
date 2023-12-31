package com.example.familyalbum.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.familyalbum.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.joinBtn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val name = binding.editTextName.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextPasswordCheck.text.toString()

            signUpWithEmail(email, password, confirmPassword, name)
        }
    }

    private fun signUpWithEmail(email: String, password: String, confirmPassword: String, name: String) {
        // 비밀번호 확인

        if (password != confirmPassword) {
            // 비밀번호가 일치하지 않는 경우
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase를 사용하여 회원가입
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    // 회원가입 성공

                    val user = FirebaseAuth.getInstance().currentUser

                    // 사용자 이름 저장
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    Log.e(TAG, user?.displayName.toString())
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {

                                // 사용자 이름 저장 성공
                                val db = FirebaseFirestore.getInstance()
                                val userDocRef = db.collection("users").document(user!!.uid)
                                val userData = hashMapOf(
                                    "email" to email,
                                    "name" to name,
                                    "profileImageUrl" to "https://firebasestorage.googleapis.com/v0/b/k-ricepower.appspot.com/o/profile_images%2FoK7HlM5ZXmhwqUUQZ1y8Y2va9m93.jpg?alt=media&token=37c2f7b4-2809-433b-b89a-19b47c54e93f" // Initialize with empty string
                                )
                                userDocRef.set(userData)
                                    .addOnCompleteListener { firestoreTask ->
                                        if (firestoreTask.isSuccessful) {

                                            navigateToNextScreen()
                                        } else {
                                            Log.e(TAG, "Firestore user data save failed", firestoreTask.exception)
                                            // Handle Firestore user data save failure
                                        }
                                    }
                            } else {
                                // 사용자 이름 저장 실패
                                Toast.makeText(this, "사용자 이름 저장 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    // 회원가입 실패
                    Toast.makeText(this, "회원가입 실패: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }

            }
    }


    private fun navigateToNextScreen() {
        // 다음 화면으로 전환하는 코드를 작성합니다.

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티를 종료하여 뒤로가기 버튼을 눌렀을 때 로그인 화면으로 돌아가지 않도록 합니다.
    }

}