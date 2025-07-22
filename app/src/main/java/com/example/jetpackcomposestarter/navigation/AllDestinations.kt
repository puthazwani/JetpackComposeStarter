//package com.example.jetpackcomposestarter.navigation
//
///**
// * This file defines all route identifiers used for navigation in the app.
// * In Jetpack Compose Navigation, a route is a unique string that identifies a screen or destination.
// *
// * Routes help build the navigation graph and allow navigating between screens using NavController.
// */
//
//sealed class NavigationRoutes {
//
//    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
//        object NavigationRoute : Unauthenticated("unauthenticated")
//        object Login : Unauthenticated("login")
//        object ForgotPassword : Unauthenticated("forgotPassword")
//        object Activation : Unauthenticated("activation")
//    }
//
//    sealed class Authenticated(val route: String) : NavigationRoutes() {
//        object NavigationRoute : Authenticated("authenticated")
//        object Home : Authenticated("Home")
//
//        // Travel Main Module
//        sealed class Travel(route: String) : Authenticated(route) {
//            object NavigationRoute : Travel("Travel")
//
//            // Travel Submodules
//            sealed class TravelAuthorization(route: String) : Travel(route) {
//                object NavigationRoute : TravelAuthorization("Travel Authorization")
//
//                // Travel Authorization sub-tabs
//                object MyTAF : TravelAuthorization("My TAF")
//                object PendingTAF : TravelAuthorization("Pending TAF")
//                object AllTAF : TravelAuthorization("All TAF")
//            }
//
//            // Travel Advance Submodule
////            sealed class TravelAdvance(route: String) : Travel(route) {
////                object NavigationRoute : TravelAdvance("Travel Advance")
////
////                object MyRTAF : TravelAdvance("My RTAF")
////                object PendingRTFA: TravelAdvance("My Pending RTAF")
////                object AllRTAF : TravelAdvance("All RTAF")
////            }
//
//        }
//    }
//
//}

