package com.sample.instantsonar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Navigator {

    public static void artist(Context context) {
        startForUrl(context, "https://instantsonar.com/artist");
    }

    public static void track(Context context, long trackId) {
        startForUrl(context, "https://instantsonar.com/track/" + trackId);
    }

    private static void startForUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        context.startActivity(intent);
    }
}
