package com.boundinteractive.kmmsample.data.model

import com.boundinteractive.kmmsample.DateTimeHelper.toFormattedDate
import kotlinx.datetime.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    @SerialName("")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("forks_count")
    val forkCount: Int,
    @SerialName("description")
    val description: String,
    @SerialName("watchers")
    val watcherCount: Int,
    @SerialName("created_at")
    private val _createdAt: String,
    @SerialName("updated_at")
    private val _updatedAt: String,
    @SerialName("owner")
    val owner: Owner,
) {
    val avatarUrl = owner.avatarUrl
    private fun createdAt() = _createdAt.toFormattedDate(TimeZone.currentSystemDefault())
    fun updatedAt() = _updatedAt.toFormattedDate(TimeZone.currentSystemDefault())

    val accessibilityString = "A repo called $name that has $forkCount forks and " +
            "$watcherCount watchers that was last updated in ${updatedAt()}"
}

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String
)