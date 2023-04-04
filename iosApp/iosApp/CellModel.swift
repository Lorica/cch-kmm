//
//  CellModel.swift
//  iosApp
//
//  Created by Gerald Kim on 30/3/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct CellModel: Identifiable {
    let id: Int
    let name: String
    let forkCount: Int
    let description: String?
    let watcherCount: Int
    let avatarUrl: String
    let updatedAt: String
    let a11yString: String
}
