package com.example.inventarioapp_pdm.navigation

sealed class Routes(val route: String) {

    data object Splash : Routes("splash")

    data object Onboarding : Routes("onboarding")

    data object Login : Routes("login")

    data object Dashboard : Routes("dashboard")

    data object Inventory : Routes("inventory")

    data object ProductDetail : Routes("product_detail")

    data object NewProduct : Routes("new_product")

    data object Scanner : Routes("scanner")

    data object Dispatch : Routes("dispatch")

    data object Notifications : Routes("notifications")

    data object Profile : Routes("profile")
}