package com.csxs.letterbook.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.csxs.core.BaseConstants;
import com.csxs.core.net.RetryWithDelay;
import com.csxs.core.net.exception.HttpResponseFunc;
import com.csxs.core.net.result.Result;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.core.net.result.ResultObserver;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.MainActivity;
import com.csxs.letterbook.R;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.WxAuthTokenE;
import com.csxs.letterbook.entity.WxLoginE;
import com.csxs.letterbook.entity.WxUserInfoE;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.login.activity.BindPhoneActivity;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler, LifecycleOwner, HasAndroidInjector {
    private static String TAG = "MicroMsg.WXEntryActivity";


    @Inject
    LetterApiService apiService;

    @Inject
    Gson gson;

    @Inject
    MmkvUtlis mmkvUtlis;


    private LifecycleRegistry mLifecycleRegistry;

    private IWXAPI api;
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        api = WXAPIFactory.createWXAPI(this, BaseConstants.WX_APPID, false);

        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
//
//		Toast.makeText(this, getString(result) + ", type=" + resp.getType(), Toast.LENGTH_SHORT).show();
//
//
//		if (resp.getType() == ConstantsAPI.COMMAND_SUBSCRIBE_MESSAGE) {
//			SubscribeMessage.Resp subscribeMsgResp = (SubscribeMessage.Resp) resp;
//			String text = String.format("openid=%s\ntemplate_id=%s\nscene=%d\naction=%s\nreserved=%s",
//					subscribeMsgResp.openId, subscribeMsgResp.templateID, subscribeMsgResp.scene, subscribeMsgResp.action, subscribeMsgResp.reserved);
//
//			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//		}
//
//        if (resp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
//            WXLaunchMiniProgram.Resp launchMiniProgramResp = (WXLaunchMiniProgram.Resp) resp;
//            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s",
//                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
//        if (resp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_VIEW) {
//            WXOpenBusinessView.Resp launchMiniProgramResp = (WXOpenBusinessView.Resp) resp;
//            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s\nbusinessType=%s",
//                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr,launchMiniProgramResp.businessType);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
//        if (resp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_WEBVIEW) {
//            WXOpenBusinessWebview.Resp response = (WXOpenBusinessWebview.Resp) resp;
//            String text = String.format("businessType=%d\nresultInfo=%s\nret=%d",response.businessType,response.resultInfo,response.errCode);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
        //获取token
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            SendAuth.Resp authResp = (SendAuth.Resp) resp;
            final String code = authResp.code;
            if (apiService != null) {

            }
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", BaseConstants.WX_APPID, BaseConstants.WX_APPSECRET, code);
            apiService.getWxToken(url).onErrorResumeNext(new HttpResponseFunc<String>())
                    .flatMap(new Function<String, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(String s) throws Exception {
                            WxAuthTokenE wxAuthTokenE = gson.fromJson(s, WxAuthTokenE.class);
                            Log.e("wxAuthTokenE", wxAuthTokenE.toString());
                            if (wxAuthTokenE.getErrcode() == 0) {
                                String userinfo = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", wxAuthTokenE.getAccess_token(), wxAuthTokenE.getOpenid());
                                return apiService.getWxUserInfo(userinfo);
                            }
                            return Observable.just(s);
                        }
                    }).flatMap(new Function<String, ObservableSource<Result<WxLoginE>>>() {
                @Override
                public ObservableSource<Result<WxLoginE>> apply(String s) throws Exception {
                    WxUserInfoE wxUserInfoE = gson.fromJson(s, WxUserInfoE.class);
                    if (wxUserInfoE.getErrcode() == 0) {
                        return apiService.wxlogin(wxUserInfoE.getUnionid(), wxUserInfoE.getOpenid(), wxUserInfoE.getNickname(), wxUserInfoE.getHeadimgurl(),wxUserInfoE.getSex());
                    }

                    Result<WxLoginE> result1 = new Result<WxLoginE>();
                    WxLoginE wxLoginE = new WxLoginE();
                    wxLoginE.setErrorCode(1);
                    result1.setResults(wxLoginE);
                    return Observable.just(result1);
                }
            })
                    .map(new Function<Result<WxLoginE>, WxLoginE>() {
                @Override
                public WxLoginE apply(Result<WxLoginE> wxLoginEResult) throws Exception {
                    if(wxLoginEResult!=null&&wxLoginEResult.getCode()==200){
                        if (wxLoginEResult.getResults() != null) {
                            return wxLoginEResult.getResults();
                        }
                    }

                    WxLoginE wxLoginE = new WxLoginE();
                    wxLoginE.setErrorCode(wxLoginEResult.getCode());
                    return wxLoginE;

                }
            }) .retryWhen(new RetryWithDelay())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)))
                    .subscribe(new ResultObserver<WxLoginE>() {
                        @Override
                        public void handlerResult(WxLoginE wxLoginE) {
                            if (wxLoginE.getErrorCode() == 0) {
                                mmkvUtlis.putUserInfo(GlobalConstants.LOGIN_WAY_WEIXIN, wxLoginE.getToken(), wxLoginE.getPhoneNumber(), wxLoginE.getHeadimgurl(), wxLoginE.getNickname(), wxLoginE.getSex(), wxLoginE.getUserId());
                                if (wxLoginE.getBinding() == 1) {
                                    HomeActivity.start(WXEntryActivity.this, HomeActivity.class, null);
                                    finish();
                                } else {
                                    Intent intent = new Intent();
                                    intent.putExtra("type", GlobalConstants.LOGIN_WAY_WEIXIN);
                                    BindPhoneActivity.start(WXEntryActivity.this, BindPhoneActivity.class, intent);
                                }
                            }
                        }

                        @Override
                        public void handlerError(int code, String msg) {

                        }
                    });

        }
//        finish();


    }

    private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
    }

    private void goToShowMsg(ShowMessageFromWX.Req showReq) {
//		WXMediaMessage wxMsg = showReq.message;
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//
//		StringBuffer msg = new StringBuffer();
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//
//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}