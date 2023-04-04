//
//  CellViewModel.swift
//  iosApp
//
//  Created by Gerald Kim on 4/4/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

final class CellViewModel: Identifiable, ObservableObject {
    let model: CellModel
    private let manager: GithubManager
    let isFaved: Bool
    
    init(model: CellModel, manager: GithubManager) {
        self.manager = manager
        self.model = model
        self.isFaved = manager.isFavorite(id: model.id)
    }

    func fave() {
        manager.saveFavorite(id: model.id)
    }
    
    func unFave() {
        manager.unFavorite(id: model.id)
    }
}
