package com.john.emovie.infraestructure.dblocal.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.john.emovie.infraestructure.dblocal.entitys.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE idCategory = :idCategory")
    fun getAllMovies(idCategory: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<MovieEntity>)
}