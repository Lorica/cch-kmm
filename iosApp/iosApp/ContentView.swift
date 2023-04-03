import SwiftUI
import shared

struct ContentView: View {
    private let githubRepo = GithubRepositoryImpl(keyPantry: KeyPantry())
    @StateObject private var viewModel: ContentViewModel
    
    init() {
        // Would be ideal to have these as part of DI framework
        let favUseCase = FavouritesUseCase(githubRepository: githubRepo)
        let githubUseCase = FetchGithubReposUseCase(githubRepository: githubRepo)
        let manager = GithubManager(favorites: favUseCase,
                                    githubFetcher: githubUseCase)
        self._viewModel = StateObject(wrappedValue: ContentViewModel(manager: manager))
    }
    
	var body: some View {
        switch(viewModel.viewState) {
        case .loading:
            ProgressView()
                .task {
                    await viewModel.loadData()
                }
        case .success(let models):
            contentView(cellModels: models)
        case .empty:
            Color.white
        case .failure:
            Color.red
        }
	}
    
    @ViewBuilder
    private func contentView(cellModels: [CellModel]) -> some View {
        List {
            ForEach(cellModels) { model in
                GitCell(model: model)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
