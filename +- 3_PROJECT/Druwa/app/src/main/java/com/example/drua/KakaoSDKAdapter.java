package com.example.drua;

import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;

public class KakaoSDKAdapter extends KakaoAdapter {
    private static volatile GlobalApplication instance = null;
    // 로그인 시 사용 될, Session의 옵션 설정을 위한 인터페이스 입니다.
//    @Override
//    public ISessionConfig getSessionConfig() {
//        return new ISessionConfig() {
//            // 로그인 시에 인증 타입을 지정합니다.
//            @Override
//            public AuthType[] getAuthTypes() {
//                // Auth Type
//                // KAKAO_TALK  : 카카오톡 로그인 타입
//                // KAKAO_STORY : 카카오스토리 로그인 타입
//                // KAKAO_ACCOUNT : 웹뷰 다이얼로그를 통한 계정연결 타입
//                // KAKAO_TALK_EXCLUDE_NATIVE_LOGIN : 카카오톡 로그인 타입과 함께 계정생성을 위한 버튼을 함께 제공
//                // KAKAO_LOGIN_ALL : 모든 로그인 방식을 제공
//                return new AuthType[] {AuthType.KAKAO_ACCOUNT};
//                //return new AuthType[] {AuthType.KAKAO_TALK};
//
//            }
//            // 로그인 웹뷰에서 pause와 resume시에 타이머를 설정하여, CPU의 소모를 절약 할 지의 여부를 지정합니다.
//            // true로 지정할 경우, 로그인 웹뷰의 onPuase()와 onResume()에 타이머를 설정해야 합니다.
//            @Override
//            public boolean isUsingWebviewTimer() {
//                return false;
//            }
//            // 로그인 시 토큰을 저장할 때의 암호화 여부를 지정합니다.
//            @Override
//            public boolean isSecureMode() {
//                return false;
//            }
//            // 일반 사용자가 아닌 Kakao와 제휴 된 앱에서 사용되는 값입니다.
//            // 값을 지정하지 않을 경우, ApprovalType.INDIVIDUAL 값으로 사용됩니다.
//            @Override
//            public ApprovalType getApprovalType() {
//                return ApprovalType.INDIVIDUAL;
//            }
//            // 로그인 웹뷰에서 email 입력 폼의 데이터를 저장할 지 여부를 지정합니다.
//            @Override
//            public boolean isSaveFormData() {
//                return true;
//            }
//        };
//    }
//    // Application이 가지고 있는 정보를 얻기 위한 인터페이스 입니다.
//    @Override
//    public IApplicationConfig getApplicationConfig() {
//        return new IApplicationConfig() {
//            @Override
//            public Context getApplicationContext() {
//                return GlobalApplication.getGlobalApplicationContext();
//            }
//        };
//    }

    public ISessionConfig getSessionConfig() {

        return new ISessionConfig() {
            @Override
            public AuthType[] getAuthTypes() {
                //return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                return new AuthType[] {AuthType.KAKAO_ACCOUNT};
                //KAKAO_ACCOUNT : 웹뷰 다이얼로그를 통한 계정연결 타입
                //로그인을 어떤 방식으로 할지 지정
                //KAKAO_LOGIN_ALL: 모든 로그인방식을 사용하고 싶을때 지정.
            }

            @Override
            public boolean isUsingWebviewTimer() {
                return false;
                // SDK 로그인시 사용되는 WebView에서 pause와 resume시에 Timer를 설정하여 CPU소모를 절약한다.
                // true 를 리턴할경우 webview로그인을 사용하는 화면서 모든 webview에 onPause와 onResume 시에 Timer를 설정해 주어야 한다.
                // 지정하지 않을 시 false로 설정된다.
            }

            @Override
            public boolean isSecureMode() {
                return false;
                // 로그인시 access token과 refresh token을 저장할 때의 암호화 여부를 결정한다.
            }

            @Override
            public ApprovalType getApprovalType() {
                return ApprovalType.INDIVIDUAL;
                // 일반 사용자가 아닌 Kakao와 제휴된 앱에서만 사용되는 값으로, 값을 채워주지 않을경우 ApprovalType.INDIVIDUAL 값을 사용하게 된다.
            }

            @Override
            public boolean isSaveFormData() {
                return true;
                // Kakao SDK 에서 사용되는 WebView에서 email 입력폼의 데이터를 저장할지 여부를 결정한다.
                // true일 경우, 다음번에 다시 로그인 시 email 폼을 누르면 이전에 입력했던 이메일이 나타난다.
            }
        };
    }

    @Override
    public IApplicationConfig getApplicationConfig() {
        return new IApplicationConfig() {
            @Override
            public Context getApplicationContext() {
                return GlobalApplication.getGlobalApplicationContext();
            }
        };
    }

}