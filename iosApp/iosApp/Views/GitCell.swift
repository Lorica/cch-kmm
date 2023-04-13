//
//  CellView.swift
//  iosApp
//
//  Created by Gerald Kim on 30/3/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct GitCell: View {
    let viewModel: CellViewModel
    @State private var isFaved: Bool
    
    init(viewModel: CellViewModel) {
        self.viewModel = viewModel
        _isFaved = State(initialValue: viewModel.isFaved)
    }
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: viewModel.model.avatarUrl)!) { image in
                image.resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(maxWidth: 40, maxHeight: 40)
            } placeholder: {
                ProgressView()
            }
            .clipShape(Circle())
            .padding(.trailing, 16)
            VStack(alignment: .leading) {
                Text(viewModel.model.name)
                    .font(.title3)
                Text(viewModel.model.updatedAt)
                    .font(.caption)
                HStack {
                    Image(systemName: "eye")
                    Text(String(viewModel.model.watcherCount))
                        .padding(.trailing, 4)
                    Image(systemName: "tuningfork")
                    Text(String(viewModel.model.forkCount))
                }
                .font(.caption2)
            }
            Spacer()
            Toggle(isOn: $isFaved) {
                Image(systemName: isFaved ? "heart.fill" : "heart")
                    .tint(Color.red)
            }
            .toggleStyle(FaveToggleStyle())
            .onChange(of: isFaved) { isOn in
                guard isFaved == isOn else { return }
                if isOn { viewModel.fave() }
                else { viewModel.unFave() }
            }

        }
        .padding(.horizontal, 16)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(viewModel.model.a11yString)
    }
}

private struct FaveToggleStyle: ToggleStyle {
    func makeBody(configuration: Self.Configuration) -> some View {
        Button(action: {
            configuration.isOn.toggle()
        }, label: {
            configuration.label
                .padding(8)
                .contentShape(Circle())
        })
        .background(configuration.isOn ? Color.blue : Color.white)
    }
}

struct CellView_Previews: PreviewProvider {
    static var previews: some View {
        let manager = MockAppManager()
        let viewModel = CellViewModel(model: CellModel.mock,
                                      manager: manager)
        GitCell(viewModel: viewModel)
    }
}
