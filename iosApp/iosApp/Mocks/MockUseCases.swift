//
//  MockUseCases.swift
//  iosApp
//
//  Created by Gerald Kim on 12/4/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

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
