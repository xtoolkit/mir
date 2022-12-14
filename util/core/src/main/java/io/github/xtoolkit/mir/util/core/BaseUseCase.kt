package io.github.xtoolkit.mir.util.core

abstract class BaseUseCase<in IN, OUT, REPO>(protected val repo: REPO) {
    suspend operator fun invoke(parameter: IN): Result<OUT> = try {
        execute(parameter)
    } catch (e: Exception) {
        Result.failure(e)
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameter: IN): Result<OUT>
}