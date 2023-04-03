//
//  KMM+.swift
//  iosApp
//
//  Created by Gerald Kim on 3/4/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct ResultHelper {
    static func extractBasicResultError<T>(_ result: BasicResult<T>) -> ErrorType? {
        if let error = result as? BasicResult<KotlinNothing> {
            if let resultError = error as? BasicResultError {
                return resultError.error
            }
        }
        return nil
    }
    
    static func extractBasicResultSuccess<T>(_ result: BasicResult<T>) -> T? {
        if let resultSuccess = result as? BasicResultSuccess {
            return resultSuccess.value
        }
        return nil
    }
}

extension ErrorType {
    func toAppError() -> AppError {
        switch self {
        case .client: return .client
        case .server: return .server
        default: return .generic
        }
    }
}
