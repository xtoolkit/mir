package io.github.xtoolkit.mir.level.framework.db.ds

import io.github.xtoolkit.mir.level.core.data.datasource.LevelDS
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.framework.db.dao.LevelDao
import io.github.xtoolkit.mir.level.framework.db.entity.converter.toDomain
import io.github.xtoolkit.mir.level.framework.db.entity.converter.toEntity

class LevelLocalDS(private val levelDao: LevelDao) : LevelDS {
    private suspend fun getX(id: Int) = levelDao.getById(id)!!.toDomain()

    override suspend fun add(level: Level) = try {
        val levelId = levelDao.add(level.toEntity())
        Result.success(getX(levelId.toInt()))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun get(level: Level) = Result.success(getX(level.id))

    override suspend fun getAll() = Result.success(
        levelDao.getAll().map { it.toDomain() }
    )
}