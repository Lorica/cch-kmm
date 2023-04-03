//
//  GithubManager.swift
//  iosApp
//
//  Created by Gerald Kim on 30/3/2023.
//  Copyright © 2023 Bound. All rights reserved.
//

import Foundation
import shared

struct GithubManager {
    private let favUseCase: FavouritesUseCase
    private let githubUseCase: FetchGithubReposUseCase
    
    init(favorites: FavouritesUseCase, githubFetcher: FetchGithubReposUseCase) {
        favUseCase = favorites
        githubUseCase = githubFetcher
    }
    
    func loadData() async throws -> [CellModel]? {
        let models = try await githubUseCase.invoke()
        return models.map { model in CellModel.convert(from: model) }
    }
    
    func saveFavorite(id: Int) {
        favUseCase.add(id: Int32(id))
    }
    
    func unFavorite(id: Int) {
        favUseCase.remove(id: Int32(id))
    }
    
    func isFavorite(id: Int) -> Bool {
        let allFaves = favUseCase.fetch()
        return allFaves.contains { kotlinId in
            KotlinInt(int: Int32(id)) == kotlinId
        }
    }
}

extension CellModel {
    static func convert(from model: Repo) -> CellModel {
        return CellModel(id: Int(model.id),
                         name: model.name,
                         forkCount: Int(model.forkCount),
                         description: model.description_,
                         watcherCount: Int(model.watcherCount),
                         avatarUrl: model.avatarUrl,
                         updatedAt: model.updatedAt(),
                         a11yString: model.accessibilityString)
    }
}

class MockFavouritesUseCase: FavouritesUseCase {
    var mockArray: [KotlinInt] = []
    
    override func fetch() -> [KotlinInt] {
        return mockArray
    }
    
    override func add(id: Int32) {
        mockArray.append(KotlinInt(int: id))
    }
    
    override func remove(id: Int32) {
        mockArray.removeAll { i in i == KotlinInt(int: id) }
    }
    
    override func removeAll() {
        mockArray.removeAll()
    }
}

class MockFetchGithubReposUseCase: FetchGithubReposUseCase {
    var isCalled: Bool = false
    
    override func invoke() async throws -> [Repo] {
        isCalled = true
        return [Repo(id: 1, name: "test", forkCount: 2, description: "test", watcherCount: 0, _createdAt: "testDate", _updatedAt: "testDate", owner: Owner(avatarUrl: "testUrl"))]
    }
}
