package com.example.databaseapp.dataModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {Student.class,Enrolment.class,Course.class},
        version = 1
)
@TypeConverters(DataTypeConverter.class)


public abstract class AppSampleDatabase extends RoomDatabase {
    private static AppSampleDatabase database = null;

    public abstract StudentDao studentDao();
    public abstract CourseDao courseDao();
    public abstract EnrolmentDao enrolmentDao();

    public static AppSampleDatabase createDatabaseInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppSampleDatabase.class,
                    "sample database"
                    ).allowMainThreadQueries().build();
        }
        return database;
    }
}
