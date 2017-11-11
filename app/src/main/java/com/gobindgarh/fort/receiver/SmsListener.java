package com.gobindgarh.fort.receiver;

public interface SmsListener {
    void messageReceived (String messageText, int message_type);
}
