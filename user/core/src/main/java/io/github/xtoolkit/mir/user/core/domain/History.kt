package io.github.xtoolkit.mir.user.core.domain

data class History(
    val id: Int,
    val userId: Int,
    val playgroundId: Int,
    val found: List<String>,
    val done: Boolean = false
)
