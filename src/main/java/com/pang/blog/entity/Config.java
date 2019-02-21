package com.pang.blog.entity;

public class Config {
    private String blogKey;

    private String blogValue;

    private String blogNote;

    public String getBlogKey() {
        return blogKey;
    }

    public void setBlogKey(String blogKey) {
        this.blogKey = blogKey == null ? null : blogKey.trim();
    }

    public String getBlogValue() {
        return blogValue;
    }

    public void setBlogValue(String blogValue) {
        this.blogValue = blogValue == null ? null : blogValue.trim();
    }

    public String getBlogNote() {
        return blogNote;
    }

    public void setBlogNote(String blogNote) {
        this.blogNote = blogNote == null ? null : blogNote.trim();
    }
}