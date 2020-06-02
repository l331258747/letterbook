package com.csxs.viewlib.dynamictext.model;


import com.csxs.viewlib.dynamictext.enumtext.StatusType;


public interface ExpandableStatusFix {
    void setStatus(StatusType status);

    StatusType getStatus();
}
