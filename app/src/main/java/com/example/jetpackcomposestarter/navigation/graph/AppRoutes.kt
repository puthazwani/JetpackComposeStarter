package com.example.jetpackcomposestarter.navigation.graph


object AppRoutes {

    // Unauthenticated
    object Auth {
        const val Root = "auth"
        const val Login = "auth/login"
        const val ForgotPassword = "auth/forgot-password"
        const val Activation = "auth/activation"
    }

    // Authenticated
    object Main {
        const val Root = "authenticated"
        const val Home = "authenticated/home"
    }

    object Travel {
        const val Root = "authenticated/travel"

        object Authorization {
            const val Root = "authenticated/travel/authorization"
            const val MyTaf = "authenticated/travel/authorization/my"
            const val PendingTaf = "authenticated/travel/authorization/pending"
            const val AllTaf = "authenticated/travel/authorization/all"
            const val TafFormNew = "authenticated/travel/authorization/form/new"
            const val TafFormWithId = "authenticated/travel/authorization/form/{id}"
        }
    }
}

