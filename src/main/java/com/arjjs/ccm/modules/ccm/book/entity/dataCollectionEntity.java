package com.arjjs.ccm.modules.ccm.book.entity;

import java.util.List;

public class dataCollectionEntity {
    private String id;
    private String name;
    private String photo;
    private List<CcmDatabaseBook> bookData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<CcmDatabaseBook> getBookData() {
        return bookData;
    }

    public void setBookData(List<CcmDatabaseBook> bookData) {
        this.bookData = bookData;
    }
}
