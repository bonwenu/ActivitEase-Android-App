package com.example.activitease;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
interface MyDao {
    @Insert
    void addInterest(Interest i);

    @Query("select * from interests")
    List<Interest> getInterests();

    @Query("select * from interests where interestName = :interestName")
    Interest loadInterestByName(String interestName);

    @Update
    void updateInterest(Interest i);

    @Delete
    void deleteInterest(Interest i);

    @Query("DELETE FROM interests where interestName = :interestName")
    void deleteByInterestName(String interestName);

    @Query("SELECT COUNT(interestName) FROM interests")
    int getInterestCt();

}
