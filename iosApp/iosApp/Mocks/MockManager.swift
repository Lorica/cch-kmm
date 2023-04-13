//
//  MockManager.swift
//  iosApp
//
//  Created by Gerald Kim on 12/4/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class MockAppManager: AppManagerType {
    var returnModels: [CellModel]?
    var calledFav: Bool = false
    
    func loadData() async throws -> [CellModel]? {
        if let model = returnModels { return model }
        else { throw AppError.client }
    }
    
    func saveFavorite(id: Int) { calledFav = true }
    func unFavorite(id: Int) { calledFav = true }
    
    func isFavorite(id: Int) -> Bool { return true }
}
