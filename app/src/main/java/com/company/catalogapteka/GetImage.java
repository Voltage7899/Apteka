package com.company.catalogapteka;

import android.content.Intent;
import android.net.Uri;

public class GetImage {

    public Intent getContent(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return intent;
    }

}
