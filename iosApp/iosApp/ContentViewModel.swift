//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Gerald Kim on 29/3/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

@MainActor
final class ContentViewModel: ObservableObject {
    @Published var viewState: ViewState<[CellViewModel]> = .loading
    private let manager: GithubManager
    
    init(manager: GithubManager) {
        self.manager = manager
    }
    
    func loadData() async {
        do {
            if let cellModels = try await manager.loadData() {
                viewState = .success(result: cellModels.map { CellViewModel(model: $0, manager: manager) })
            } else {
                viewState = .empty
            }
        } catch {
            viewState = .failure
        }
    }
}

enum ViewState<T> {
    case loading
    case success(result: T)
    case empty
    case failure
}
