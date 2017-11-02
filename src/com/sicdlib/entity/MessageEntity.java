package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "message", schema = "socialmind", catalog = "")
public class MessageEntity {
    private String messageId;
    private String messageTitle;
    private String messageContent;
    private String messageTime;
    private String adminId;

    @Id
    @Column(name = "messageID")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "messageTitle")
    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    @Basic
    @Column(name = "messageContent")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "messageTime")
    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Basic
    @Column(name = "adminID")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
        if (messageTitle != null ? !messageTitle.equals(that.messageTitle) : that.messageTitle != null) return false;
        if (messageContent != null ? !messageContent.equals(that.messageContent) : that.messageContent != null)
            return false;
        if (messageTime != null ? !messageTime.equals(that.messageTime) : that.messageTime != null) return false;
        if (adminId != null ? !adminId.equals(that.adminId) : that.adminId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId != null ? messageId.hashCode() : 0;
        result = 31 * result + (messageTitle != null ? messageTitle.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        return result;
    }
}
