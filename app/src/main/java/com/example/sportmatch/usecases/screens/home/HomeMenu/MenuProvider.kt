package com.example.sportmatch.usecases.screens.home.HomeMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.SportsSoccer

object MenuProvider {
    val menu =
        MenuItem(
            startIcon = Icons.Default.NotificationsActive,
            title = "Notificaciones",
            endIcon = Icons.Default.ArrowForwardIos,
        )

    val menuList = listOf<MenuItem>(
        menu,
        MenuItem(
            startIcon = Icons.Default.NotificationsActive,
            title = "Notificaciones",
            endIcon = Icons.Default.ArrowForwardIos,
        ),
        MenuItem(
            startIcon = Icons.Default.Notifications,
            title = "Mis altertas",
            endIcon = Icons.Default.ArrowForwardIos,
        ),
        MenuItem(
            startIcon = Icons.Default.SportsSoccer,
            title = "Deportes",
            endIcon = Icons.Default.ArrowForwardIos,
        ),
        MenuItem(
            startIcon = Icons.Default.Sports,
            title = "Mis Compromisos",
            endIcon = Icons.Default.ArrowForwardIos,
        ),
        MenuItem(
            startIcon = Icons.Default.People,
            title = "Equipos",
            endIcon = Icons.Default.ArrowForwardIos,
        ),
        MenuItem(
            startIcon = Icons.Default.Logout,
            title = "Cerrar sesiòn",
            endIcon = Icons.Default.ArrowForwardIos,
        )
    )
}
