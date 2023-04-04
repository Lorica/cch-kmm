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
        let result = try await githubUseCase.invoke()
        if let models = ResultHelper.extractBasicResultSuccess(result) {
            return models.map { model in CellModel.convert(from: model as! Repo) }
        } else if let error = ResultHelper.extractBasicResultError(result) {
            print("Error: \(error.description())")
            throw error.toAppError()
        }
        return nil
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

enum AppError: Error, Hashable, CustomStringConvertible {
    case client
    case server
    case generic
    
    var description: String {
        switch self {
        case .client:
            return "There was an error in the client"
        case .server:
            return "There was an error in the server"
        case .generic:
            return "There was a generic error"
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
    
    init() {
        let githubRepository = GithubRepositoryImpl(keyPantry: KeyPantry())
        super.init(githubRepository: githubRepository)
    }
    
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
    
    init() {
        let githubRepository = GithubRepositoryImpl(keyPantry: KeyPantry())
        super.init(githubRepository: githubRepository)
    }
    
    override func invoke() async throws -> BasicResult<NSArray> {
        isCalled = true
        return BasicResultSuccess(value: [MockModel.shared.repo])
    }
}
