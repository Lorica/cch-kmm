//
//  Mock+.swift
//  iosApp
//
//  Created by Gerald Kim on 12/4/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

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
    
    static var mock: CellModel {
        return CellModel.convert(from: MockModel.shared.repo)
    }
}
