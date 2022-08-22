package io.github.xtoolkit.mir.level.framework.db.ds

import io.github.xtoolkit.mir.level.core.data.datasource.LevelPlaygroundDS
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.framework.db.dao.LevelPlaygroundDao
import io.github.xtoolkit.mir.level.framework.db.entity.LevelPlaygroundEntity
import io.github.xtoolkit.mir.level.framework.db.entity.converter.toDomain
import io.github.xtoolkit.mir.playground.core.domain.Playground

class LevelPlaygroundLocalDS(private val levelPlaygroundDao: LevelPlaygroundDao) :
    LevelPlaygroundDS {
    private suspend fun get(id: Int) = levelPlaygroundDao.get(id)!!.toDomain()

    override suspend fun add(level: Level, playground: Playground) = try {
        val levelPlaygroundId = levelPlaygroundDao.add(
            LevelPlaygroundEntity(
                id = 0,
                levelId = level.id,
                playgroundId = playground.id
            )
        )

        Result.success(get(levelPlaygroundId.toInt()))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getLevelPlayground(level: Level) = Result.success(
        levelPlaygroundDao.getLevelPlayground(level.id).map { it.toDomain() }
    )
}