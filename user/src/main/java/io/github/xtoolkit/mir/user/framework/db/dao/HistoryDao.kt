package io.github.xtoolkit.mir.user.framework.db.dao

import androidx.room.*
import io.github.xtoolkit.mir.user.framework.db.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(historyEntity: HistoryEntity): Long

    @Query("select * from histories where id = :id")
    suspend fun getHistoryById(id: Int): HistoryEntity?

    @Query("select * from histories where userId = :userId")
    suspend fun getByUserId(userId: Int): List<HistoryEntity>

    @Query("select * from histories where userId = :userId and playgroundId = :playgroundId")
    suspend fun findByUserPlayground(userId: Int, playgroundId: Int): HistoryEntity?

    @Update
    suspend fun update(historyEntity: HistoryEntity)
}