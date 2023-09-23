package com.d204.algo.ui.oauth

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.d204.algo.ApplicationClass
import com.d204.algo.MainActivity
import com.d204.algo.data.repository.UserRepository
import com.d204.algo.databinding.DialogSolvedacBinding
import com.d204.algo.ui.extension.showDialog
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "KaKaoApi"
class KaKaoApi(private val act: AppCompatActivity, private val userRepository: UserRepository) {
    private val espHelper = ApplicationClass.preferencesHelper
    private val solvedConnected = false
    private fun skipLogin() {
        if (AuthApiClient.instance.hasToken()) {
            try {
                val tkn = AuthApiClient.instance.tokenManagerProvider.manager.getToken()!!
                CoroutineScope(Dispatchers.IO).launch {
                    loadUser(tkn)
                }
            } catch (e: Error) {
                Toast.makeText(act, "카카오 토큰 발급 중 오류 발생", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setLoginBtn(loginBtn: ImageButton) {
        // 자동 로그인
        skipLogin()

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Toast.makeText(act, "카카오 계정으로 로그인 실패", Toast.LENGTH_SHORT).show()
            }
            if (token != null) {
                // 액티비티 이동
                val intent = Intent(act, MainActivity::class.java)
                act.startActivity(intent)
                act.finish()
            }
        }

        loginBtn.setOnClickListener {
            // kakao 실행 가능 여부
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(act)) {
                // 가능하다면 카카오톡으로 로그인하기
                UserApiClient.instance.loginWithKakaoTalk(act) { token, error ->
                    if (error != null) {
                        Toast.makeText(act, "카카오톡으로 로그인 실패", Toast.LENGTH_SHORT).show()
                        Log.e("test", "카카오톡으로 로그인 실패 " + error.message)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                    } else if (token != null) {
                        // 서버에 카카오 토큰을 넘겨주는 과정
                        CoroutineScope(Dispatchers.IO).launch {
                            loadUser(token)
                        }

                        Toast.makeText(act, "카카오톡으로 로그인 성공", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "카카오톡 로그인: $token")
                        Log.d("test", "카카오톡으로 로그인 성공" + token.accessToken)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(act, callback = callback)
            }
        }
    }

    private suspend fun loadUser(kakaoToken: OAuthToken) {
        userRepository.getUser(kakaoToken.accessToken, kakaoToken.refreshToken).collect {
            Log.d(TAG, "loadUser: $it")
            espHelper.prefAccessToken = kakaoToken.accessToken
            espHelper.prefRefreshToken = kakaoToken.refreshToken
            espHelper.prefUserId = it.id
            espHelper.prefUserNickname = it.kakaoNickname
            espHelper.prefUserEmail = it.kakaoId
            espHelper.prefUserProfile = it.profileImage
            espHelper.prefUserTier = it.preTier
            // solved ac 연동이 됐다면(DB에 ID가 있다면) 다음 화면으로
            if(solvedConnected) toMainActivity(kakaoToken)
            else connectSolvedAcDialog(kakaoToken)
        }
    }

    private fun toMainActivity(token: OAuthToken) {
        val intent = Intent(act, MainActivity::class.java)
        intent.putExtra("kakaoToken", token)
        act.startActivity(intent)
        act.finish()
    }

    fun connectSolvedAcDialog(kakaoToken: OAuthToken) {
        val dialogBinding = DialogSolvedacBinding.inflate(act.layoutInflater)
        val code = generateRandomSixDigitValue()
        dialogBinding.solvedAcConnectCode.text = code

        val alertDialog = AlertDialog.Builder(act)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.solvedAcConnectBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val isSuccess = checkValueWithServer(code)
                if (isSuccess) {
                    Toast.makeText(act, "연동이 성공했습니다.", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                    toMainActivity(kakaoToken)
                } else {
                    Toast.makeText(act, "연동이 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        alertDialog.show()
    }

    private suspend fun checkValueWithServer(value: String): Boolean {
        // 서버에 solved ac api를 넘겨서 bio에 해당 번호가 입력돼있는지 확인하는 코드를 작성한다.
        val solvedCode = userRepository.getSolvedCode()
        return value == solvedCode
    }

    private fun generateRandomSixDigitValue(): String {
        return (100000..999999).random().toString()
    }
}
