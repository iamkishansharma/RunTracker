package com.heycode.runtracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM runtracker_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM runtracker_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM runtracker_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT * FROM runtracker_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM runtracker_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>


    //All Total functions
    @Query("SELECT SUM(timeInMillis) FROM runtracker_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM runtracker_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM runtracker_table")
    fun getTotalAvgSpeed(): LiveData<Float>

    @Query("SELECT SUM(distanceInMeters) FROM runtracker_table")
    fun getTotalDistance(): LiveData<Int>
}