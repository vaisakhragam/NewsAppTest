package com.example.newsapp.data.local.db.dao

import androidx.room.*
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.data.local.db.entities.SourceEntity

@Dao
interface NewsDAO {


    //-----------Source--------------

    @Query("SELECT * FROM source_entity")
    suspend fun getSource(): List<SourceEntity>

    @Query("SELECT count(*) FROM source_entity")
    suspend fun getSourceCount():Integer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSource(entity: SourceEntity)

    @Delete
    suspend fun delete(entity: SourceEntity)

    //-----------All News--------------

    @Query("SELECT * FROM allNews_entity")
    suspend fun getAllNews(): List<AllNewsEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(entity: AllNewsEntity)

    @Query("SELECT count(*) FROM allNews_entity")
    suspend fun getAllNewsCount():Integer

    @Delete
    suspend fun delete(entity: AllNewsEntity)

    @Query("SELECT * FROM allNews_entity where title = :title")
    suspend fun getNewsDetail(title:String ): AllNewsEntity
}