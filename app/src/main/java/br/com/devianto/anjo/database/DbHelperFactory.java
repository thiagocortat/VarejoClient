package br.com.devianto.anjo.database;

import android.content.Context;

public final class DbHelperFactory
{

    private static DbHelper instance;
    public DbHelperFactory(){}

	public static DbHelper getDbHelper(Context context)
    {
        if (instance == null)
            instance = new DbHelper(context);

        return instance;
    }
}
