package com.example.project_1.Item;

import android.net.Uri;

public class ImageItem {
    public Uri imageUri;
    public String imageUrl;

    public ImageItem(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUri) {
        this.imageUrl = imageUri.toString();
    }
}
