//
//  CellView.swift
//  iosApp
//
//  Created by Gerald Kim on 30/3/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GitCell: View {
    let model: CellModel
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: model.avatarUrl)!)
            Spacer()
            VStack {
                Text(model.name)
                Text(model.updatedAt)
            }
        }
    }
}

struct CellView_Previews: PreviewProvider {
    static var previews: some View {
        let repo = shared.MockModel.shared.repo
        let model = CellModel.convert(from: repo)
        GitCell(model: model)
    }
}
