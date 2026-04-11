package com.apple.inc.user.excetions;


public interface IBaseError<T> {

    String getErrorCode();

    String getErrorMessage();

    String getUserMessage();

    T getMetadata();

    boolean displayMsg();

    void setMetaData(T metaData);
}
