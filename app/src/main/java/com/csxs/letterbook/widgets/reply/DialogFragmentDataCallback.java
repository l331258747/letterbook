package com.csxs.letterbook.widgets.reply;



public interface DialogFragmentDataCallback {

    String getCommentText();

    String getHintText();

    void setCommentText(String commentTextTemp);

    void sendContentText(String content);


}
