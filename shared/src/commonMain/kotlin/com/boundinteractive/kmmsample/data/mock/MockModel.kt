package com.boundinteractive.kmmsample.data.mock

import com.boundinteractive.kmmsample.data.model.Owner
import com.boundinteractive.kmmsample.data.model.Repo

object MockModel {
    fun repo(): Repo {
        return Repo(id = 1,
            name = "Test",
            forkCount = 3,
            description = "test description",
            watcherCount = 1,
            _createdAt = "11-05-2013 02:31",
            _updatedAt = "13-05-2013 02:31",
            owner = Owner(avatarUrl = "https://boundinteractive.com")
        )
    }
}