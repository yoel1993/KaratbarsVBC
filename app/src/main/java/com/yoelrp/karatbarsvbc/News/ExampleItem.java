package com.yoelrp.karatbarsvbc.News;

/**
 * Created by YoelRP on 08/01/2019.
 */

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private String mLikes;

    public ExampleItem(String imageUrl, String creator, String likes)
    {
        mImageUrl= imageUrl;
        mCreator=creator;
        mLikes=likes;


    }

    public String getmImageUrl()
    {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl)
    {
        this.mImageUrl = mImageUrl;
    }

    public String getmCreator()
    {
        return mCreator;
    }

    public void setmCreator(String mCreator)
    {
        this.mCreator = mCreator;
    }

    public String getmLikes()
    {
        return mLikes;
    }

    public void setmLikes(String mLikes)
    {
        this.mLikes = mLikes;
    }
}
