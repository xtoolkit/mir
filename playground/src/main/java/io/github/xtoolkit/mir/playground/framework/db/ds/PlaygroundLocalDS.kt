package io.github.xtoolkit.mir.playground.framework.db.ds

import io.github.xtoolkit.mir.playground.core.data.datasource.PlaygroundDS
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.framework.db.dao.PlaygroundDao
import io.github.xtoolkit.mir.playground.framework.db.entity.converter.toDomain
import io.github.xtoolkit.mir.playground.framework.db.entity.converter.toEntity

class PlaygroundLocalDS(private val playgroundDao: PlaygroundDao) : PlaygroundDS {
    override suspend fun add(playground: Playground) = try {
        val playgroundId = playgroundDao.add(playground.toEntity())
        getById(playgroundId.toInt())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getById(id: Int) = Result.success(playgroundDao.getById(id)!!.toDomain())
}