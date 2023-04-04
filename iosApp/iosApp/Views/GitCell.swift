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
        let model = CellModel(id: 1, name: "test", forkCount: 1, description: nil, watcherCount: 2, avatarUrl: "https://boundinteractive.com/wp-content/uploads/2022/06/04-Innovation-Futures-Lifeblood-1536x1069.jpg", updatedAt: "test", a11yString: "Test")
        let manager = GithubManager(favorites: MockFavouritesUseCase(),
                                    githubFetcher: MockFetchGithubReposUseCase())
        let viewModel = CellViewModel(model: model, manager: manager)
        GitCell(viewModel: viewModel)
    }
}
