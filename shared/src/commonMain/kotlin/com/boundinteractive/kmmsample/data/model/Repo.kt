package com.boundinteractive.kmmsample.data.model

import com.boundinteractive.kmmsample.DateTimeHelper.asFormattedDate
import kotlinx.datetime.toInstant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    val id: Int,
    val name: String,
    @SerialName("forks_count")
    val forkCount: Int,
    @SerialName("description")
    val description: String? = null,
    @SerialName("watchers")
    val watcherCount: Int,
    @SerialName("created_at")
    private val _createdAt: String,
    @SerialName("updated_at")
    private val _updatedAt: String,
    val owner: Owner,
) {
    val avatarUrl = owner.avatarUrl
    private fun createdAt() = _createdAt.toInstant().asFormattedDate()
    fun updatedAt() = _updatedAt.toInstant().asFormattedDate()

    val accessibilityString = "A repo called $name that has $forkCount forks and " +
            "$watcherCount watchers that was last updated in ${updatedAt()}"
}

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String
)