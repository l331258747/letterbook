/*
 * Copyright © Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.csxs.core.utils.permission;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;

import com.csxs.core.R;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * Created by Zhenjie Yan on 2018/1/1.
 */
public final class RuntimeRationale implements Rationale<List<String>> {
    Context  mContext;
    public RuntimeRationale(Context context){
        this.mContext=context;
    }

    @Override
    public void showRationale(Context context, List<String> permissions, final RequestExecutor executor) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_rationale,
            TextUtils.join("\n", permissionNames));

//        AlertDialog dialog= new AlertDialog.Builder(mContext).setCancelable(false)
//            .setTitle(R.string.title_dialog)
//            .setMessage(message)
//            .setPositiveButton(R.string.resume, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    executor.execute();
//                }
//            })
//            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    executor.cancel();
//                }
//            }).create();
//
//
//        dialog .show();
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
//        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }
}