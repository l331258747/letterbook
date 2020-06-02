package com.csxs.core.utils.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.csxs.core.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;

import java.util.List;


/**
 * andpermission 权限框架
 * 简单二次封装一下
 */
public class PermissionUtils {

    private Context mContext;
    private @PermissionDef

    String[] permissions;
    private RequestPermissionResult requestPermissionResult;

    public PermissionUtils() {
    }

    private static final int REQUEST_CODE_SETTING = 1;

    /**
     * 请求权限
     */


    public PermissionUtils with(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public PermissionUtils permission(@PermissionDef String... permissions) {
        this.permissions = permissions;
        return this;
    }


    public PermissionUtils result(RequestPermissionResult requestPermissionResult) {
        this.requestPermissionResult = requestPermissionResult;
        return this;
    }


    public void request() {

//        if (hasPermissions(mContext, permissions)) {
//            if (requestPermissionResult != null) {
//                requestPermissionResult.HasPermissionResult();
//            }
//            return;
//        }
        AndPermission.with(mContext)
                .runtime()
                .permission(permissions)
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                        executor.execute();
                    }
                })
                .onGranted(GrantedAction)
                .onDenied(DeniedAction)
                .start();
    }


    public void requestPermission() {
        if (hasPermissions(mContext, permissions)) {
            if (requestPermissionResult != null) {
                requestPermissionResult.HasPermissionResult();
            }
            return;
        }
        AndPermission.with(mContext)
                .runtime()
                .permission(permissions)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale((context, permissions, executor) -> {
                    // 此处可以选择显示提示弹窗
                    executor.execute();
                })
                // 用户给权限了
                .onGranted(permissions -> Toast.makeText(mContext, "用户给权限了", Toast.LENGTH_SHORT).show())
                // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(permissions -> {
                    // 判断用户是不是不再显示权限弹窗了，若不再显示的话进入权限设置页
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        // 打开权限设置页
//                        AndPermission.permissionSetting(MainActivity.this).execute();
                        setPermission(mContext);
                        return;
                    }
                    Toast.makeText(mContext, "用户拒绝权限", Toast.LENGTH_SHORT).show();
                })
                .start();
    }


    /**
     * 检查有没有请求的权限
     * @param mContext
     * @param permissions
     * @return
     */
    public boolean hasPermissions(Context mContext, @PermissionDef String[] permissions) {
        return AndPermission.hasPermissions(mContext, permissions);
    }

    private Action GrantedAction = new Action<List<String>>() {
        @Override
        public void onAction(List<String> permissions) {
            //toast(R.string.successfully);
            if (requestPermissionResult != null) {
                requestPermissionResult.PermissionResultSuccess();
            }
        }
    };

    private Action DeniedAction = new Action<List<String>>() {
        @Override
        public void onAction(@NonNull List<String> permissions) {
            //toast(R.string.failure);
            if (requestPermissionResult != null) {
                requestPermissionResult.PermissionResultDenied();
            }
            if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                showSettingDialog(mContext, permissions);
            }
        }
    };

    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed,
                TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context).setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission(context);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }


    private void setPermission(Context context) {
        AndPermission.with(context).runtime().setting().start(REQUEST_CODE_SETTING);
    }


    public RequestPermissionResult getRequestPermissionResult() {
        return requestPermissionResult;
    }

    public void setRequestPermissionResult(RequestPermissionResult requestPermissionResult) {
        this.requestPermissionResult = requestPermissionResult;
    }

    public interface RequestPermissionResult {
        //请求权限成功
        void PermissionResultSuccess();

        //请求权限失败
        void PermissionResultDenied();

        //已经有请求权限
        void HasPermissionResult();
    }
}
